package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Cursor pagination on the collection list endpoints (branches here as
 * the representative; all go through the same respondPage helper).
 */
class CollectionPaginationTest : ProjectAny() {
    val elementChange = """
        {
            "change": [
                {
                    "@type": "DataVersion",
                    "identity": {"@id": "99999999-9999-4999-8999-999999999999"},
                    "payload": {"@type": "PartDefinition", "name": "anything"}
                }
            ]
        }
    """.trimIndent()

    init {
        "branch list pages by id with a next link" {
            testApplication {
                val commitId = commitChanges(demoProjectId, elementChange).atId()
                val defaultBranchId = getProject(demoProjectId).bodyAsJsonObject()
                    .nestedAtId("defaultBranch")
                // the list contains the default branch plus the three created here
                val created = ((1..3).map { n ->
                    createBranch(demoProjectId, commitId, "branch-$n").atId()
                } + defaultBranchId).sorted()

                val first = httpGet("/projects/$demoProjectId/branches?page%5Bsize%5D=2")
                first shouldHaveStatus HttpStatusCode.OK
                val firstIds = Json.parseToJsonElement(first.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                firstIds.size shouldBe 2
                first.headers[HttpHeaders.Link].shouldNotBeNull()

                val second = httpGet(
                    "/projects/$demoProjectId/branches?page%5Bsize%5D=2&page%5Bafter%5D=${firstIds.last()}")
                val secondIds = Json.parseToJsonElement(second.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                secondIds.size shouldBe 2
                second.headers[HttpHeaders.Link].shouldBeNull()

                (firstIds + secondIds) shouldContainExactly created
            }
        }

        "unpaged branch list is unchanged" {
            testApplication {
                val commitId = commitChanges(demoProjectId, elementChange).atId()
                createBranch(demoProjectId, commitId, "solo").atId()

                val response = getBranches(demoProjectId)
                response shouldHaveStatus HttpStatusCode.OK
                // default branch + the one created here
                Json.parseToJsonElement(response.bodyAsText()).jsonArray.size shouldBe 2
                response.headers[HttpHeaders.Link].shouldBeNull()
            }
        }
    }
}
