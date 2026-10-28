package org.openmbee.flexo.sysmlv2

import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


suspend fun RoutingContext.notImplemented() {
    call.respondText("Not implemented", status= HttpStatusCode.NotImplemented)
}

/**
 * Respond with a cursor page of [items]: ordered by id, resuming after
 * page[after], bounded by page[size], with a Link rel="next" header when
 * more items remain. With neither param set, responds with the unpaged
 * historical list untouched.
 */
suspend inline fun <reified T : Any> RoutingContext.respondPage(
    items: List<T>,
    pageSize: Int?,
    pageAfter: String?,
    crossinline idOf: (T) -> String
) {
    if (pageSize == null && pageAfter == null) {
        return call.respond(items)
    }
    if (pageSize != null && pageSize < 1) {
        throw BadRequestException("page[size] must be a positive integer")
    }
    val remaining = items.sortedBy(idOf).filter { pageAfter == null || idOf(it) > pageAfter }
    val page = if (pageSize == null) remaining else remaining.take(pageSize)
    if (pageSize != null && remaining.size > pageSize) {
        val nextUrl = "${call.request.path()}?page%5Bsize%5D=$pageSize&page%5Bafter%5D=${idOf(page.last())}"
        call.response.headers.append(HttpHeaders.Link, "<$nextUrl>; rel=\"next\"")
    }
    call.respond(page)
}
