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

        "PUT /projects/{id} - update an existing project" {
            testApplication {
                // PUT requires the project to already exist (the handler
                // does a GET to backfill missing fields), so create it
                // via POST first.
                val projectId = UUID.randomUUID()
                createProject(projectId, "Original Name", "original")
                putProject(projectId, "Updated Name", "updated").apply {
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
                createProject(id, "Get Test")
                getProject(id).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                    bodyAsText().shouldContainJsonKey("@id")
                }
            }
        }

        "DELETE /projects/{id} - soft delete project" {
            testApplication {
                val id = UUID.randomUUID()
                createProject(id, "Delete Test")
                deleteProject(id).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
