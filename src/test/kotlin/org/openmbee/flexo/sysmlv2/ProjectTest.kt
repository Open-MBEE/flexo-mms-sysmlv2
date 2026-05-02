package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.json.shouldContainJsonKey
import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.client.statement.*
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*
import java.util.*

class ProjectTest : CommonSpec() {
    init {
        "POST /projects - create a new project" {
            testApplication {
                val projectId = UUID.randomUUID()
                httpPost("/projects") {
                    setJsonBody(
                        """{"@type":"Project","@id":"$projectId","name":"New Project","description":"desc"}"""
                    )
                }.apply {
                    this shouldHaveStatus HttpStatusCode.OK
                    val body = bodyAsText()
                    body.shouldContainJsonKey("@id")
                    body.shouldContainJsonKey("name")
                }
            }
        }

        "PUT /projects/{id} - create a project with a specific id" {
            testApplication {
                val projectId = UUID.randomUUID()
                putProject(projectId, "Specific Id Project", "via PUT").apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }

        "GET /projects - list all projects" {
            testApplication {
                createProject(name = "Listed Project")
                getProjects().apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }

        "GET /projects/{id} - get project by ID" {
            testApplication {
                val id = UUID.randomUUID()
                putProject(id, "Get Test")
                getProject(id).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                    bodyAsText().shouldContainJsonKey("@id")
                }
            }
        }

        "DELETE /projects/{id} - soft delete project" {
            testApplication {
                val id = UUID.randomUUID()
                putProject(id, "Delete Test")
                deleteProject(id).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
