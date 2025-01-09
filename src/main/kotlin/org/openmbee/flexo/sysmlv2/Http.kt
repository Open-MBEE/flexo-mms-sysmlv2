package org.openmbee.flexo.sysmlv2

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*


suspend fun PipelineContext<*, ApplicationCall>.notImplemented() {
    call.respondText("Not implemented", status= HttpStatusCode.NotImplemented)
}
