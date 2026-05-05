package org.openmbee.flexo.sysmlv2.util

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.infrastructure.generateId

/**
 * Parse a response body as a JSON object. Convenience for the common
 * `Json.parseToJsonElement(bodyAsText()).jsonObject` chain.
 */
suspend fun HttpResponse.bodyAsJsonObject(): kotlinx.serialization.json.JsonObject =
    Json.parseToJsonElement(bodyAsText()).jsonObject

/**
 * Extract the `@id` field from a JSON response body.
 * Throws if the response body is not a JSON object or has no `@id` field.
 */
suspend fun HttpResponse.atId(): String {
    val body = bodyAsText()
    return Json.parseToJsonElement(body).jsonObject["@id"]?.jsonPrimitive?.content
        ?: error("response body missing '@id' field: $body")
}

/**
 * Look up the `@id` of a nested object field — e.g. a `Project` response has
 * `defaultBranch: { "@id": "..." }`, and `getDefaultBranchId(...)` returns
 * that nested ID directly.
 */
fun JsonElement.nestedAtId(field: String): String {
    val nested = jsonObject[field]?.jsonObject
        ?: error("response body missing nested object field '$field': $this")
    return nested["@id"]?.jsonPrimitive?.content
        ?: error("nested field '$field' missing '@id': $nested")
}

/**
 * CRUD helpers for the sysmlv2 service API. These mirror layer1's `Helper.kt`
 * pattern: each helper performs a single API call, asserts the expected
 * status code on success, and returns the raw response for further inspection.
 */

suspend fun ApplicationTestBuilder.createProject(
    projectId: String = generateId(),
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
    projectId: String,
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

suspend fun ApplicationTestBuilder.getProject(projectId: String): HttpResponse =
    httpGet("/projects/$projectId")

suspend fun ApplicationTestBuilder.getProjects(): HttpResponse =
    httpGet("/projects")

suspend fun ApplicationTestBuilder.deleteProject(projectId: String): HttpResponse =
    httpDelete("/projects/$projectId")

suspend fun ApplicationTestBuilder.commitChanges(
    projectId: String,
    changeJson: String
): HttpResponse {
    return httpPost("/projects/$projectId/commits") {
        setJsonBody(changeJson)
    }
}

suspend fun ApplicationTestBuilder.getCommits(projectId: String): HttpResponse =
    httpGet("/projects/$projectId/commits")

suspend fun ApplicationTestBuilder.getCommit(
    projectId: String,
    commitId: String
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId")

suspend fun ApplicationTestBuilder.getChanges(
    projectId: String,
    commitId: String
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/changes")

suspend fun ApplicationTestBuilder.getElements(
    projectId: String,
    commitId: String
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/elements")

suspend fun ApplicationTestBuilder.getElement(
    projectId: String,
    commitId: String,
    elementId: String
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/elements/$elementId")

suspend fun ApplicationTestBuilder.getRoots(
    projectId: String,
    commitId: String
): HttpResponse = httpGet("/projects/$projectId/commits/$commitId/roots")

suspend fun ApplicationTestBuilder.getBranches(projectId: String): HttpResponse =
    httpGet("/projects/$projectId/branches")

suspend fun ApplicationTestBuilder.getBranch(
    projectId: String,
    branchId: String
): HttpResponse = httpGet("/projects/$projectId/branches/$branchId")

suspend fun ApplicationTestBuilder.deleteBranch(
    projectId: String,
    branchId: String
): HttpResponse = httpDelete("/projects/$projectId/branches/$branchId")

suspend fun ApplicationTestBuilder.createBranch(
    projectId: String,
    headCommitId: String,
    name: String = "feature-branch",
    branchId: String? = null
): HttpResponse {
    val idClause = branchId?.let { """"@id": "$it",""" } ?: ""
    return httpPost("/projects/$projectId/branches") {
        setJsonBody(
            """
            {
                "@type": "Branch",
                $idClause
                "name": "$name",
                "head": { "@id": "$headCommitId" }
            }
            """.trimIndent()
        )
    }
}

suspend fun ApplicationTestBuilder.getTags(projectId: String): HttpResponse =
    httpGet("/projects/$projectId/tags")

suspend fun ApplicationTestBuilder.getTag(
    projectId: String,
    tagId: String
): HttpResponse = httpGet("/projects/$projectId/tags/$tagId")

suspend fun ApplicationTestBuilder.deleteTag(
    projectId: String,
    tagId: String
): HttpResponse = httpDelete("/projects/$projectId/tags/$tagId")

suspend fun ApplicationTestBuilder.createTag(
    projectId: String,
    taggedCommitId: String,
    name: String = "v1.0"
): HttpResponse {
    return httpPost("/projects/$projectId/tags") {
        setJsonBody(
            """
            {
                "@type": "Tag",
                "name": "$name",
                "taggedCommit": { "@id": "$taggedCommitId" }
            }
            """.trimIndent()
        )
    }
}

suspend fun ApplicationTestBuilder.getQueries(projectId: String): HttpResponse =
    httpGet("/projects/$projectId/queries")

suspend fun ApplicationTestBuilder.getQuery(
    projectId: String,
    queryId: String
): HttpResponse = httpGet("/projects/$projectId/queries/$queryId")

suspend fun ApplicationTestBuilder.deleteQuery(
    projectId: String,
    queryId: String
): HttpResponse = httpDelete("/projects/$projectId/queries/$queryId")

/**
 * POST a query with a single PrimitiveConstraint where-clause matching
 * elements whose `name` equals [propertyValue]. The application uses
 * `classDiscriminator = "@type"` (see AppMain.kt), so polymorphic
 * sealed-class members must use `"@type"` rather than the default `"type"`.
 */
suspend fun ApplicationTestBuilder.createQuery(
    projectId: String,
    propertyName: String = "name",
    propertyValue: String = "Test Part"
): HttpResponse {
    return httpPost("/projects/$projectId/queries") {
        setJsonBody(
            """
            {
                "@type": "Query",
                "select": ["@id"],
                "where": {
                    "@type": "PrimitiveConstraint",
                    "operator": "=",
                    "property": "$propertyName",
                    "value": ["$propertyValue"]
                }
            }
            """.trimIndent()
        )
    }
}
