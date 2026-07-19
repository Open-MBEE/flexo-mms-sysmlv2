package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Cursor pagination on GET .../elements: page[size] bounds each response,
 * page[after] resumes after a cursor, and a Link rel="next" header is
 * present exactly when more elements remain.
 */
class ElementPaginationTest : ProjectAny() {
    // ids chosen so lexicographic order is the numeric order
    val elementIds = (1..5).map { "88888888-8888-4888-8888-00000000000$it" }

    val seedChanges = """
        {
            "change": [
                ${elementIds.joinToString(",\n") {
                    """{"@type": "DataVersion", "identity": {"@id": "$it"}, "payload": {"@type": "PartDefinition", "name": "part-$it"}}"""
                }}
            ]
        }
    """.trimIndent()

    init {
        "walking pages of 2 visits every element exactly once in order" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChanges).atId()

                val seen = mutableListOf<String>()
                var after: String? = null
                var pages = 0
                while (true) {
                    val cursor = after?.let { "&page%5Bafter%5D=$it" } ?: ""
                    val response = httpGet(
                        "/projects/$demoProjectId/commits/$commitId/elements?page%5Bsize%5D=2$cursor")
                    response shouldHaveStatus HttpStatusCode.OK
                    val ids = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                        .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                    seen.addAll(ids)
                    pages++

                    val link = response.headers[HttpHeaders.Link]
                    if (link == null) break
                    link shouldContain "rel=\"next\""
                    after = ids.last()
                }

                pages shouldBe 3
                seen shouldContainExactly elementIds
            }
        }

        "a page larger than the model has no next link" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChanges).atId()

                val response = httpGet(
                    "/projects/$demoProjectId/commits/$commitId/elements?page%5Bsize%5D=50")
                response shouldHaveStatus HttpStatusCode.OK
                Json.parseToJsonElement(response.bodyAsText()).jsonArray.size shouldBe 5
                response.headers[HttpHeaders.Link].shouldBeNull()
            }
        }

        "the first page advertises a next link" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChanges).atId()

                val response = httpGet(
                    "/projects/$demoProjectId/commits/$commitId/elements?page%5Bsize%5D=2")
                response.headers[HttpHeaders.Link].shouldNotBeNull()
            }
        }

        "page[size] of zero is a 400" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChanges).atId()

                httpGet("/projects/$demoProjectId/commits/$commitId/elements?page%5Bsize%5D=0")
                    .shouldHaveStatus(HttpStatusCode.BadRequest)
            }
        }
    }
}
