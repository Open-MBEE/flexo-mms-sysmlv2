package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.*
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Failure responses from layer1 are forwarded verbatim — including
 * response headers such as WWW-Authenticate, which clients need to
 * renegotiate credentials.
 */
class ForwardHeadersTest : CommonSpec() {
    init {
        "forwarded 401 carries the upstream WWW-Authenticate challenge" {
            testApplication {
                val response = httpGet("/projects") {
                    headers.remove(HttpHeaders.Authorization)
                    header(HttpHeaders.Authorization, "Bearer garbage")
                }
                response shouldHaveStatus HttpStatusCode.Unauthorized
                val challenge = response.headers[HttpHeaders.WWWAuthenticate]
                challenge.shouldNotBeNull()
                challenge shouldContain "Bearer"
            }
        }
    }
}
