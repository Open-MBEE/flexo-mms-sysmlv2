package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.ktor.http.*
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Failures respond with the spec's Error object, not plain text, so
 * official clients can parse them.
 */
class ErrorShapeTest : ProjectAny() {
    init {
        "a 400 carries a parseable Error object" {
            testApplication {
                val response = httpPost("/projects/$demoProjectId/queries") {
                    setJsonBody("""{ "@type": "Query" }""")
                }
                response shouldHaveStatus HttpStatusCode.BadRequest
                val error = response.bodyAsJsonObject()
                error["@type"]!!.jsonPrimitive.content shouldBe "Error"
                error["description"]!!.jsonPrimitive.content.shouldNotBeBlank()
            }
        }

        "a 501 stub carries a parseable Error object" {
            testApplication {
                val response = httpGet(
                    "/projects/$demoProjectId/commits/00000000-dead-4000-8000-000000000000/diff?baseCommitId=00000000-dead-4000-8000-000000000001")
                response shouldHaveStatus HttpStatusCode.NotImplemented
                response.bodyAsJsonObject()["@type"]!!.jsonPrimitive.content shouldBe "Error"
            }
        }
    }
}
