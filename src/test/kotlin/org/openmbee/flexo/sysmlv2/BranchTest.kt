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
import java.util.*

class BranchTest : ProjectAny() {
    init {
        // POSTing a commit to a freshly created project gives us a real commit
        // ID that we can use as the head for new branches/tags. The default
        // branch ID comes from the project response.
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

        "GET /projects/{id}/branches - lists the project's default branch" {
            testApplication {
                val defaultBranchId = getProject(demoProjectId).bodyAsJsonObject()
                    .nestedAtId("defaultBranch")

                val list = getBranches(demoProjectId)
                list shouldHaveStatus HttpStatusCode.OK
                val branches = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                val ids = branches.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                // the auto-created flexo "master" branch is filtered out, so
                // the only entry should be the project's default branch
                ids shouldContain defaultBranchId.toString()
                val match = branches.first {
                    it.jsonObject["@id"]!!.jsonPrimitive.content == defaultBranchId.toString()
                }
                match.jsonObject["@type"]!!.jsonPrimitive.content shouldBe "Branch"
                match.jsonObject.nestedAtId("owningProject") shouldBe demoProjectId
            }
        }

        "GET /projects/{id}/branches/{branchId} - returns the branch with matching @id" {
            testApplication {
                val defaultBranchId = getProject(demoProjectId).bodyAsJsonObject()
                    .nestedAtId("defaultBranch")

                val response = getBranch(demoProjectId, defaultBranchId)
                response shouldHaveStatus HttpStatusCode.OK
                val branch = response.bodyAsJsonObject()
                branch["@id"]!!.jsonPrimitive.content shouldBe defaultBranchId.toString()
                branch["@type"]!!.jsonPrimitive.content shouldBe "Branch"
                branch.nestedAtId("owningProject") shouldBe demoProjectId
                // every branch references a head commit
                branch["head"]!!.jsonObject["@id"]!!.jsonPrimitive.content
            }
        }

        "POST /projects/{id}/branches - creates a new branch from a head commit" {
            testApplication {
                // need a real commit ID to point the new branch at
                val commitId = commitChanges(demoProjectId, seedChange).atIdAsUuid()

                val branchId = UUID.randomUUID()
                val created = createBranch(demoProjectId, commitId, "feature-x", branchId)
                created shouldHaveStatus HttpStatusCode.OK
                val parsed = created.bodyAsJsonObject()
                parsed["@id"]!!.jsonPrimitive.content shouldBe branchId.toString()
                parsed["@type"]!!.jsonPrimitive.content shouldBe "Branch"
                parsed["name"]!!.jsonPrimitive.content shouldBe "feature-x"
                parsed.nestedAtId("owningProject") shouldBe demoProjectId

                // and a follow-up GET confirms it persisted
                val fetched = getBranch(demoProjectId, branchId).bodyAsJsonObject()
                fetched["@id"]!!.jsonPrimitive.content shouldBe branchId.toString()
                fetched["name"]!!.jsonPrimitive.content shouldBe "feature-x"
            }
        }

        "DELETE /projects/{id}/branches/{branchId} - removes the branch from the list" {
            testApplication {
                val commitId = commitChanges(demoProjectId, seedChange).atIdAsUuid()
                val branchId = UUID.randomUUID()
                createBranch(demoProjectId, commitId, "to-delete", branchId)
                deleteBranch(demoProjectId, branchId) shouldHaveStatus HttpStatusCode.OK

                // soft-deleted branches are filtered out of GET
                val list = getBranches(demoProjectId)
                val ids = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldNotContain branchId.toString()
            }
        }
    }
}
