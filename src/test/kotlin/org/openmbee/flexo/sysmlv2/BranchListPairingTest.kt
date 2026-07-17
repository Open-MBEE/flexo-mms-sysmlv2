package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.maps.shouldContainAll
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Regression surface for issue #20: the branch list endpoint must keep
 * each branch's name associated with its own @id (reported shuffled on
 * GraphDB deployments — the wildcard-CONSTRUCT bug lives upstream in
 * layer1, but this pairing must hold against any conformant store).
 */
class BranchListPairingTest : ProjectAny() {
    val elementChange = """
        {
            "change": [
                {
                    "@type": "DataVersion",
                    "identity": {"@id": "44444444-4444-4444-8444-444444444444"},
                    "payload": {"@type": "PartDefinition", "name": "anything"}
                }
            ]
        }
    """.trimIndent()

    init {
        "GET branches pairs each name with its own @id" {
            testApplication {
                val commitId = commitChanges(demoProjectId, elementChange).atId()
                val idAlpha = createBranch(demoProjectId, commitId, "alpha").atId()
                val idBravo = createBranch(demoProjectId, commitId, "bravo").atId()
                val idCharlie = createBranch(demoProjectId, commitId, "charlie").atId()

                val response = getBranches(demoProjectId)
                response shouldHaveStatus HttpStatusCode.OK
                val byId = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                    .associate {
                        it.jsonObject["@id"]!!.jsonPrimitive.content to
                                it.jsonObject["name"]!!.jsonPrimitive.content
                    }
                byId shouldContainAll mapOf(
                    idAlpha to "alpha",
                    idBravo to "bravo",
                    idCharlie to "charlie",
                )
            }
        }
    }
}
