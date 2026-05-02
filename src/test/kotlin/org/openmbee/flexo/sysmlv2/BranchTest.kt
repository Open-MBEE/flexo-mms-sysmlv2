package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.client.statement.*
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

class BranchTest : ProjectAny() {
    init {
        "GET /projects/{id}/branches - list branches" {
            testApplication {
                getBranches(demoProjectId).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
