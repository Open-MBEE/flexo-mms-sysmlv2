package org.openmbee.flexo.sysmlv2

import io.ktor.http.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


suspend fun RoutingContext.notImplemented() {
    call.respondText("Not implemented", status= HttpStatusCode.NotImplemented)
}

// page[size] applied whenever a cursor is given without one (as in the
// SysML v2 API reference implementation)
const val DEFAULT_PAGE_SIZE = 100

fun resolvePageSize(pageSize: Int?): Int {
    if (pageSize != null && pageSize < 1) {
        throw BadRequestException("page[size] must be a positive integer")
    }
    return pageSize ?: DEFAULT_PAGE_SIZE
}

/**
 * Add the Link header for a page: rel="next" resumes after [lastId] and
 * rel="prev" pages back from [firstId]; both carry the effective page[size]
 * and, when the request bounded a window, the window's opposite cursor.
 */
fun RoutingContext.appendPageLinks(
    size: Int, lastId: String?, firstId: String?,
    windowAfter: String? = null, windowBefore: String? = null
) {
    val path = call.request.path()
    val links = listOfNotNull(
        lastId?.let {
            val bound = windowBefore?.let { b -> "&page%5Bbefore%5D=$b" } ?: ""
            "<$path?page%5Bsize%5D=$size&page%5Bafter%5D=$it$bound>; rel=\"next\""
        },
        firstId?.let {
            val bound = windowAfter?.let { a -> "&page%5Bafter%5D=$a" } ?: ""
            "<$path?page%5Bsize%5D=$size&page%5Bbefore%5D=$it$bound>; rel=\"prev\""
        }
    )
    if (links.isNotEmpty()) call.response.headers.append(HttpHeaders.Link, links.joinToString(", "))
}

/**
 * Respond with a cursor page of [items] ordered by id: page[after] resumes
 * after a cursor, page[before] alone yields the page[size] items just
 * before a cursor (both bound the window when given together), and Link
 * rel="next"/"prev" headers point at the neighbouring pages. With no paging
 * param set, responds with the unpaged historical list untouched.
 */
suspend inline fun <reified T : Any> RoutingContext.respondPage(
    items: List<T>,
    pageSize: Int?,
    pageAfter: String?,
    pageBefore: String?,
    crossinline idOf: (T) -> String
) {
    if (pageSize == null && pageAfter == null && pageBefore == null) {
        return call.respond(items)
    }
    val size = resolvePageSize(pageSize)
    val sorted = items.sortedBy(idOf)
    val window = sorted.filter { (pageAfter == null || idOf(it) > pageAfter) && (pageBefore == null || idOf(it) < pageBefore) }
    val page = if (pageAfter == null && pageBefore != null) window.takeLast(size) else window.take(size)
    if (page.isNotEmpty()) {
        val first = idOf(page.first())
        val last = idOf(page.last())
        // with both cursors the neighbouring pages stay inside the window
        val bounded = pageAfter != null && pageBefore != null
        val candidates = if (bounded) window else sorted
        appendPageLinks(size,
            last.takeIf { candidates.any { idOf(it) > last } },
            first.takeIf { candidates.any { idOf(it) < first } },
            if (bounded) pageAfter else null,
            if (bounded) pageBefore else null)
    }
    call.respond(page)
}
