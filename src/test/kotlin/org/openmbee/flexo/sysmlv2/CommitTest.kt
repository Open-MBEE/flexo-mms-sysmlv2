package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

class CommitTest : ProjectAny() {
    init {
        val seedChange = """
            {
                "change": [
                    {
                        "@type": "DataVersion",
                        "identity": {"@id":"bb1d79c2-1306-5b35-a807-93e46fc3431c"},
                        "payload": {
                            "@type": "PartDefinition",
                            "name": "Test Part"
                        }
                    }
                ]
            }
        """.trimIndent()

        "POST /projects/{id}/commits - returns a Commit with @id, @type, owningProject" {
            testApplication {
                val response = commitChanges(demoProjectId, seedChange)
                response shouldHaveStatus HttpStatusCode.OK
                val commit = response.bodyAsJsonObject()
                commit["@type"]!!.jsonPrimitive.content shouldBe "Commit"
                commit.nestedAtId("owningProject") shouldBe demoProjectId
                // server should mint a UUID @id
                response.atIdAsUuid()
            }
        }

        "GET /projects/{id}/commits - list contains the just-created commit (newest first)" {
            testApplication {
                val firstCommitId = commitChanges(demoProjectId, seedChange).atIdAsUuid()
                val secondCommitId = commitChanges(
                    demoProjectId,
                    seedChange.replace("Test Part", "Renamed Part")
                ).atIdAsUuid()

                val list = getCommits(demoProjectId)
                list shouldHaveStatus HttpStatusCode.OK
                val items = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                val ids = items.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldContain firstCommitId.toString()
                ids shouldContain secondCommitId.toString()
                // the API sorts commits descending by `created` timestamp, so
                // the most recent commit should appear first
                ids.first() shouldBe secondCommitId.toString()
            }
        }

        "GET /projects/{id}/commits/{commitId} - returns the same commit object" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChange).atIdAsUuid()

                val response = getCommit(demoProjectId, commitId)
                response shouldHaveStatus HttpStatusCode.OK
                val commit = response.bodyAsJsonObject()
                commit["@id"]!!.jsonPrimitive.content shouldBe commitId.toString()
                commit["@type"]!!.jsonPrimitive.content shouldBe "Commit"
                commit.nestedAtId("owningProject") shouldBe demoProjectId
            }
        }

        "POST commits with PartsTreeRedefinition payload returns OK" {
            testApplication {
                val payload = javaClass.classLoader.getResource("PartsTreeRedefinition.json")?.readText()
                requireNotNull(payload) { "PartsTreeRedefinition.json fixture not found on classpath" }
                val response = commitChanges(demoProjectId, payload)
                response shouldHaveStatus HttpStatusCode.OK
                response.bodyAsJsonObject()["@type"]!!.jsonPrimitive.content shouldBe "Commit"
            }
        }
    }
}
