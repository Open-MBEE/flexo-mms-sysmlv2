package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

class TagTest : ProjectAny() {
    init {
        "GET /projects/{id}/tags - list tags" {
            testApplication {
                getTags(demoProjectId).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
