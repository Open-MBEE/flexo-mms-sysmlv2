package org.openmbee.flexo.sysmlv2.util

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*

/**
 * Send an HTTP request through the test client, automatically attaching the
 * Authorization: Bearer <token> header acquired from the login service.
 */
suspend fun ApplicationTestBuilder.httpRequest(
    method: HttpMethod,
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse {
    val token = Auth.getToken()
    return client.request {
        this.method = method
        this.url(uri)
        header(HttpHeaders.Authorization, "Bearer $token")
        setup()
    }
}

suspend fun ApplicationTestBuilder.httpGet(
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse = httpRequest(HttpMethod.Get, uri, setup)

suspend fun ApplicationTestBuilder.httpPost(
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse = httpRequest(HttpMethod.Post, uri, setup)

suspend fun ApplicationTestBuilder.httpPut(
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse = httpRequest(HttpMethod.Put, uri, setup)

suspend fun ApplicationTestBuilder.httpDelete(
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse = httpRequest(HttpMethod.Delete, uri, setup)

suspend fun ApplicationTestBuilder.httpPatch(
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse = httpRequest(HttpMethod.Patch, uri, setup)

suspend fun ApplicationTestBuilder.httpHead(
    uri: String,
    setup: HttpRequestBuilder.() -> Unit = {}
): HttpResponse = httpRequest(HttpMethod.Head, uri, setup)

fun HttpRequestBuilder.setJsonBody(body: String) {
    contentType(ContentType.Application.Json)
    setBody(body)
}

fun HttpRequestBuilder.setTurtleBody(body: String) {
    header(HttpHeaders.ContentType, "text/turtle")
    setBody(body)
}
