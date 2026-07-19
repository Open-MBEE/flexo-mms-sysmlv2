package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.shouldBe
import io.ktor.http.*
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * /meta/datatypes serves the official spec's schema definitions
 * (previously a hardcoded openapi-generator example payload).
 */
class MetaTest : CommonSpec() {
    init {
        "GET /meta/datatypes lists the spec schemas under \$defs" {
            testApplication {
                val response = httpGet("/meta/datatypes")
                response shouldHaveStatus HttpStatusCode.OK
                val body = response.bodyAsJsonObject()
                body["\$schema"]!!.jsonPrimitive.content shouldBe
                        "https://json-schema.org/draft/2020-12/schema"
                val defs = body["\$defs"]!!.jsonObject
                defs shouldContainKey "Project"
                defs shouldContainKey "PartDefinition"
            }
        }

        "GET /meta/datatypes/{id} returns the named schema" {
            testApplication {
                val response = httpGet("/meta/datatypes/PartDefinition")
                response shouldHaveStatus HttpStatusCode.OK
                val body = response.bodyAsJsonObject()
                body["title"]!!.jsonPrimitive.content shouldBe "PartDefinition"
            }
        }

        "GET an unknown datatype is a 404" {
            testApplication {
                httpGet("/meta/datatypes/NotARealDatatype")
                    .shouldHaveStatus(HttpStatusCode.NotFound)
            }
        }
    }
}
