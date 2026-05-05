package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.infrastructure.generateId
import org.openmbee.flexo.sysmlv2.util.*

class ProjectTest : CommonSpec() {
    init {
        "POST /projects - create a new project returns the created Project" {
            testApplication {
                val projectId = generateId()
                val response = httpPost("/projects") {
                    setJsonBody(
                        """
                        {
                            "@type": "Project",
                            "@id": "$projectId",
                            "name": "New Project",
                            "description": "desc"
                        }
                        """.trimIndent()
                    )
                }
                response shouldHaveStatus HttpStatusCode.OK
                // shouldContainJsonKey uses JSON-path syntax where '@' is reserved,
                // so parse the body and assert on the resulting object map instead.
                val parsed = response.bodyAsJsonObject()
                parsed shouldContainKey "@id"
                parsed shouldContainKey "@type"
                parsed shouldContainKey "name"
                parsed shouldContainKey "description"
                parsed shouldContainKey "defaultBranch"
                parsed["@id"]!!.jsonPrimitive.content shouldBe projectId
                parsed["@type"]!!.jsonPrimitive.content shouldBe "Project"
                parsed["name"]!!.jsonPrimitive.content shouldBe "New Project"
                parsed["description"]!!.jsonPrimitive.content shouldBe "desc"
                // server-assigned default branch id must be present (nestedAtId
                // throws if the response shape is wrong)
                parsed.nestedAtId("defaultBranch")
            }
        }

        "PUT /projects/{id} - update an existing project returns updated values" {
            testApplication {
                // PUT requires the project to already exist (the handler does a
                // GET first to backfill missing fields), so create it via POST.
                val projectId = generateId()
                createProject(projectId, "Original Name", "original")

                val updated = putProject(projectId, "Updated Name", "updated")
                val parsed = updated.bodyAsJsonObject()
                parsed["@id"]!!.jsonPrimitive.content shouldBe projectId
                parsed["name"]!!.jsonPrimitive.content shouldBe "Updated Name"
                parsed["description"]!!.jsonPrimitive.content shouldBe "updated"

                // and a follow-up GET confirms the change is persisted
                val fetched = getProject(projectId).bodyAsJsonObject()
                fetched["name"]!!.jsonPrimitive.content shouldBe "Updated Name"
                fetched["description"]!!.jsonPrimitive.content shouldBe "updated"
            }
        }

        "GET /projects - list includes the just-created project" {
            testApplication {
                val createdId = generateId()
                createProject(createdId, "Listed Project", "listed-desc")

                val list = getProjects()
                list shouldHaveStatus HttpStatusCode.OK
                val items = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                val ids = items.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldContain createdId
                val match = items.first { it.jsonObject["@id"]!!.jsonPrimitive.content == createdId }
                match.jsonObject["name"]!!.jsonPrimitive.content shouldBe "Listed Project"
                match.jsonObject["@type"]!!.jsonPrimitive.content shouldBe "Project"
            }
        }

        "GET /projects/{id} - returns matching @id, name, defaultBranch" {
            testApplication {
                val id = generateId()
                val createResponse = createProject(id, "Get Test", "get-desc")
                val createdDefaultBranch = createResponse.bodyAsJsonObject().nestedAtId("defaultBranch")

                val parsed = getProject(id).bodyAsJsonObject()
                parsed["@id"]!!.jsonPrimitive.content shouldBe id
                parsed["name"]!!.jsonPrimitive.content shouldBe "Get Test"
                parsed["description"]!!.jsonPrimitive.content shouldBe "get-desc"
                // GET should report the same default branch as POST returned
                parsed.nestedAtId("defaultBranch") shouldBe createdDefaultBranch
            }
        }

        "DELETE /projects/{id} - soft-deleted projects do not appear in list" {
            testApplication {
                val id = generateId()
                createProject(id, "Delete Test", "del-desc")
                deleteProject(id) shouldHaveStatus HttpStatusCode.OK

                // soft-deleted projects are filtered out of GET /projects
                val list = getProjects()
                val ids = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldNotContain id
            }
        }

        "GET /projects/{id} - returns 404 for unknown project" {
            testApplication {
                val unknown = generateId()
                getProject(unknown) shouldHaveStatus HttpStatusCode.NotFound
            }
        }
    }
}
