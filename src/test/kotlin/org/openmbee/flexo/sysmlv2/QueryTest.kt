package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

class QueryTest : ProjectAny() {
    init {
        "GET /projects/{id}/queries - list queries" {
            testApplication {
                getQueries(demoProjectId).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
