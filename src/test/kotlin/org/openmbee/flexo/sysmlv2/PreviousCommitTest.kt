package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Commit lineage: previousCommit links each commit to its parent, except
 * that flexo's auto-created (hidden) root commit is never referenced.
 */
class PreviousCommitTest : ProjectAny() {
    fun change(elementId: String, name: String) = """
        {
            "change": [
                {
                    "@type": "DataVersion",
                    "identity": {"@id": "$elementId"},
                    "payload": {"@type": "PartDefinition", "name": "$name"}
                }
            ]
        }
    """.trimIndent()

    init {
        "single GET resolves previousCommit; first commit has none" {
            testApplication {
                val first = commitChanges(demoProjectId, change("55555555-5555-4555-8555-000000000001", "one")).atId()
                val second = commitChanges(demoProjectId, change("55555555-5555-4555-8555-000000000002", "two")).atId()

                val firstBody = getCommit(demoProjectId, first).bodyAsJsonObject()
                (firstBody["previousCommit"] ?: JsonNull) shouldBe JsonNull

                val secondBody = getCommit(demoProjectId, second).bodyAsJsonObject()
                secondBody.nestedAtId("previousCommit") shouldBe first
            }
        }

        "commit list carries the same lineage" {
            testApplication {
                val first = commitChanges(demoProjectId, change("55555555-5555-4555-8555-000000000003", "one")).atId()
                val second = commitChanges(demoProjectId, change("55555555-5555-4555-8555-000000000004", "two")).atId()

                val response = getCommits(demoProjectId)
                response shouldHaveStatus HttpStatusCode.OK
                val byId = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                    .associateBy { it.jsonObject["@id"]!!.jsonPrimitive.content }

                (byId[first]!!.jsonObject["previousCommit"] ?: JsonNull) shouldBe JsonNull
                byId[second]!!.nestedAtId("previousCommit") shouldBe first
            }
        }
    }
}
