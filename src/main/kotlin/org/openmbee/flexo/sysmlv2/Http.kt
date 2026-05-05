package org.openmbee.flexo.sysmlv2

import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


suspend fun RoutingContext.notImplemented() {
    call.respondText("Not implemented", status= HttpStatusCode.NotImplemented)
}
