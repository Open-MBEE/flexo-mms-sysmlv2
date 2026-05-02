package org.openmbee.flexo.sysmlv2.util

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import java.util.*

/**
 * CRUD helpers for the sysmlv2 service API. These mirror layer1's `Helper.kt`
 * pattern: each helper performs a single API call, asserts the expected
 * status code on success, and returns the raw response for further inspection.
 */

suspend fun ApplicationTestBuilder.createProject(
    projectId: UUID = UUID.randomUUID(),
    name: String = "Test Project",
    description: String = "Test project description"
): HttpResponse {
    val response = httpPost("/projects") {
        setJsonBody(
            """
            {
                "@type": "Project",
                "@id": "$projectId",
                "name": "$name",
                "description": "$description"
            }
            """.trimIndent()
        )
    }
    response shouldHaveStatus HttpStatusCode.OK
    return response
}

suspend fun ApplicationTestBuilder.putProject(
    projectId: UUID,
    name: String = "Test Project",
    description: String = "Test project description"
): HttpResponse {
    val response = httpPut("/projects/$projectId") {
        setJsonBody(
            """
            {
                "@type": "Project",
                "@id": "$projectId",
                "name": "$name",
                "description": "$description"
            }
            """.trimIndent()
        )
    }
    response shouldHaveStatus HttpStatusCode.OK
    return response
}

suspend fun ApplicationTestBuilder.getProject(projectId: UUID): HttpResponse =
    httpGet("/projects/$projectId")

suspend fun ApplicationTestBuilder.getProjects(): HttpResponse =
    httpGet("/projects")

suspend fun ApplicationTestBuilder.deleteProject(projectId: UUID): HttpResponse =
    httpDelete("/projects/$projectId")

suspend fun ApplicationTestBuilder.commitChanges(
    projectId: UUID,
    changeJson: String
): HttpResponse {
    return httpPost("/projects/$projectId/commits") {
        setJsonBody(changeJson)
    }
}

suspend fun ApplicationTestBuilder.getCommits(projectId: UUID): HttpResponse =
    httpGet("/projects/$projectId/commits")

suspend fun ApplicationTestBuilder.getCommit(
    projectId: UUID,
    commitId: UUID
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId")

suspend fun ApplicationTestBuilder.getChanges(
    projectId: UUID,
    commitId: UUID
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/changes")

suspend fun ApplicationTestBuilder.getElements(
    projectId: UUID,
    commitId: UUID
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/elements")

suspend fun ApplicationTestBuilder.getElement(
    projectId: UUID,
    commitId: UUID,
    elementId: UUID
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/elements/$elementId")

suspend fun ApplicationTestBuilder.getRoots(
    projectId: UUID,
    commitId: UUID
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/roots")

suspend fun ApplicationTestBuilder.getBranches(projectId: UUID): HttpResponse =
    httpGet("/projects/$projectId/branches")

suspend fun ApplicationTestBuilder.getBranch(
    projectId: UUID,
    branchId: UUID
): HttpResponse = httpGet("/projects/$projectId/branches/$branchId")

suspend fun ApplicationTestBuilder.deleteBranch(
    projectId: UUID,
    branchId: UUID
): HttpResponse = httpDelete("/projects/$projectId/branches/$branchId")

suspend fun ApplicationTestBuilder.getTags(projectId: UUID): HttpResponse =
    httpGet("/projects/$projectId/tags")

suspend fun ApplicationTestBuilder.getQueries(projectId: UUID): HttpResponse =
    httpGet("/projects/$projectId/queries")

suspend fun ApplicationTestBuilder.deleteQuery(
    projectId: UUID,
    queryId: UUID
): HttpResponse = httpDelete("/projects/$projectId/queries/$queryId")
