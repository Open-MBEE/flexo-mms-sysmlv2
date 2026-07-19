package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * GET .../commits/{id}/changes reports the elements a commit created,
 * modified, or deleted (previously a 501 stub).
 */
class ChangesTest : ProjectAny() {
    val keepId = "aaaaaaa1-0000-4000-8000-000000000001"
    val editId = "aaaaaaa1-0000-4000-8000-000000000002"
    val dropId = "aaaaaaa1-0000-4000-8000-000000000003"

    val firstChange = """
        {
            "change": [
                {"@type": "DataVersion", "identity": {"@id": "$keepId"}, "payload": {"@type": "PartDefinition", "name": "keep"}},
                {"@type": "DataVersion", "identity": {"@id": "$editId"}, "payload": {"@type": "PartDefinition", "name": "before"}},
                {"@type": "DataVersion", "identity": {"@id": "$dropId"}, "payload": {"@type": "PartDefinition", "name": "drop"}}
            ]
        }
    """.trimIndent()

    val secondChange = """
        {
            "change": [
                {"@type": "DataVersion", "identity": {"@id": "$editId"}, "payload": {"@type": "PartDefinition", "name": "after"}},
                {"@type": "DataVersion", "identity": {"@id": "$dropId"}, "payload": null}
            ]
        }
    """.trimIndent()

    init {
        "the first commit reports every created element" {
            testApplication {
                val commitId = commitChanges(demoProjectId, firstChange).atId()

                val response = getChanges(demoProjectId, commitId)
                response shouldHaveStatus HttpStatusCode.OK
                val changes = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                val identities = changes.map { it.jsonObject.nestedAtId("identity") }
                identities shouldContainExactlyInAnyOrder listOf(keepId, editId, dropId)
                changes.forEach {
                    it.jsonObject["@type"]!!.jsonPrimitive.content shouldBe "DataVersion"
                }
            }
        }

        "a later commit reports modification and deletion, not untouched elements" {
            testApplication {
                commitChanges(demoProjectId, firstChange).atId()
                val second = commitChanges(demoProjectId, secondChange).atId()

                val changes = Json.parseToJsonElement(
                    getChanges(demoProjectId, second).bodyAsText()).jsonArray
                val byIdentity = changes.associate {
                    it.jsonObject.nestedAtId("identity") to it.jsonObject["payload"]!!
                }
                byIdentity.keys shouldContainExactlyInAnyOrder setOf(editId, dropId)
                // modified element carries its new payload
                byIdentity[editId]!!.jsonObject["name"]!!.jsonPrimitive.content shouldBe "after"
                // deleted element carries a null payload
                byIdentity[dropId] shouldBe JsonNull
            }
        }

        "a single change is addressable by its id" {
            testApplication {
                val commitId = commitChanges(demoProjectId, firstChange).atId()

                val changes = Json.parseToJsonElement(
                    getChanges(demoProjectId, commitId).bodyAsText()).jsonArray
                val changeId = changes.first().jsonObject["@id"]!!.jsonPrimitive.content

                val response = httpGet(
                    "/projects/$demoProjectId/commits/$commitId/changes/$changeId")
                response shouldHaveStatus HttpStatusCode.OK
                response.bodyAsJsonObject()["@id"]!!.jsonPrimitive.content shouldBe changeId
            }
        }

        "changes for a nonexistent commit is a 404" {
            testApplication {
                getChanges(demoProjectId, "00000000-dead-4000-8000-000000000000")
                    .shouldHaveStatus(HttpStatusCode.NotFound)
            }
        }
    }
}
