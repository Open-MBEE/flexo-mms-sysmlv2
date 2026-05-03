package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

class TagTest : ProjectAny() {
    init {
        val seedChange = """
            {
                "change": [
                    {
                        "@type": "DataVersion",
                        "identity": {"@id":"bb1d79c2-1306-5b35-a807-93e46fc3431c"},
                        "payload": {
                            "@type": "PartDefinition",
                            "name": "Seed Part"
                        }
                    }
                ]
            }
        """.trimIndent()

        "GET /projects/{id}/tags - is empty for a fresh project" {
            testApplication {
                val list = getTags(demoProjectId)
                list shouldHaveStatus HttpStatusCode.OK
                val items = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                items.size shouldBe 0
            }
        }

        "POST /projects/{id}/tags - creates a tag pointing at a commit" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChange).atId()

                val response = createTag(demoProjectId, commitId, "v1.0.0")
                response shouldHaveStatus HttpStatusCode.OK
                val tag = response.bodyAsJsonObject()
                tag["@type"]!!.jsonPrimitive.content shouldBe "Tag"
                tag["name"]!!.jsonPrimitive.content shouldBe "v1.0.0"
                tag.nestedAtId("owningProject") shouldBe demoProjectId
                tag.nestedAtId("taggedCommit") shouldBe commitId
                // a server-assigned id is expected on the response
                response.atId()
            }
        }

        "GET /projects/{id}/tags/{tagId} - returns the matching tag" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChange).atId()
                val tagId = createTag(demoProjectId, commitId, "v1.1.0").atId()

                val response = getTag(demoProjectId, tagId)
                response shouldHaveStatus HttpStatusCode.OK
                val tag = response.bodyAsJsonObject()
                tag["@id"]!!.jsonPrimitive.content shouldBe tagId
                tag["name"]!!.jsonPrimitive.content shouldBe "v1.1.0"
                tag.nestedAtId("taggedCommit") shouldBe commitId
            }
        }

        "GET /projects/{id}/tags - lists the just-created tag" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChange).atId()
                val tagId = createTag(demoProjectId, commitId, "v2.0.0").atId()

                val list = getTags(demoProjectId)
                list shouldHaveStatus HttpStatusCode.OK
                val items = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                val ids = items.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldContain tagId
                val match = items.first { it.jsonObject["@id"]!!.jsonPrimitive.content == tagId }
                match.jsonObject["name"]!!.jsonPrimitive.content shouldBe "v2.0.0"
            }
        }

        "DELETE /projects/{id}/tags/{tagId} - removes the tag from the list" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChange).atId()
                val tagId = createTag(demoProjectId, commitId, "to-delete").atId()
                deleteTag(demoProjectId, tagId) shouldHaveStatus HttpStatusCode.OK

                val list = getTags(demoProjectId)
                val ids = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldNotContain tagId
            }
        }
    }
}
