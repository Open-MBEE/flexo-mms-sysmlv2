package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.maps.shouldContainKey
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
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

        "every \$ref in a datatype response resolves within that response" {
            testApplication {
                fun refsOf(el: JsonElement): List<String> = when (el) {
                    is JsonObject -> el.flatMap { (k, v) ->
                        if (k == "\$ref" && v is JsonPrimitive) listOf(v.content) else refsOf(v)
                    }
                    is JsonArray -> el.flatMap { refsOf(it) }
                    else -> emptyList()
                }
                fun JsonObject.shouldResolveAllRefs() {
                    val defs = this["\$defs"]!!.jsonObject
                    val refs = refsOf(this)
                    refs.shouldNotBeEmpty()
                    refs.forEach { ref ->
                        ref shouldStartWith "#/\$defs/"
                        defs shouldContainKey ref.removePrefix("#/\$defs/")
                    }
                }
                httpGet("/meta/datatypes/PartDefinition").bodyAsJsonObject().shouldResolveAllRefs()
                httpGet("/meta/datatypes").bodyAsJsonObject().shouldResolveAllRefs()
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
