package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*
import java.util.*

class ElementTest : ProjectAny() {
    init {
        val rootElementId = "bb1d79c2-1306-5b35-a807-93e46fc3431c"
        val singleElementChange = """
            {
                "change": [
                    {
                        "@type": "DataVersion",
                        "identity": {"@id":"$rootElementId"},
                        "payload": {
                            "@type": "PartDefinition",
                            "name": "Spacecraft System"
                        }
                    }
                ]
            }
        """.trimIndent()

        "GET /projects/{id}/commits/{commitId}/elements - includes the committed element" {
            testApplication {
                val commitId = commitChanges(demoProjectId, singleElementChange).atIdAsUuid()

                val response = getElements(demoProjectId, commitId)
                response shouldHaveStatus HttpStatusCode.OK
                val elements = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                elements.size shouldNotBe 0
                val ids = elements.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldContain rootElementId
                val element = elements.first { it.jsonObject["@id"]!!.jsonPrimitive.content == rootElementId }
                element.jsonObject["name"]!!.jsonPrimitive.content shouldBe "Spacecraft System"
            }
        }

        "GET /projects/{id}/commits/{commitId}/elements/{elementId} - returns the single element" {
            testApplication {
                val commitId = commitChanges(demoProjectId, singleElementChange).atIdAsUuid()

                val response = getElement(demoProjectId, commitId, UUID.fromString(rootElementId))
                response shouldHaveStatus HttpStatusCode.OK
                val element = response.bodyAsJsonObject()
                element["@id"]!!.jsonPrimitive.content shouldBe rootElementId
                element["name"]!!.jsonPrimitive.content shouldBe "Spacecraft System"
            }
        }

        "GET /projects/{id}/commits/{commitId}/roots - returns elements without an owner" {
            testApplication {
                val commitId = commitChanges(demoProjectId, singleElementChange).atIdAsUuid()

                val response = getRoots(demoProjectId, commitId)
                response shouldHaveStatus HttpStatusCode.OK
                val roots = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                val ids = roots.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                // single committed PartDefinition has no owner, so it is a root
                ids shouldContain rootElementId
            }
        }

        "GET elements after PartsTreeRedefinition payload load returns multiple elements" {
            testApplication {
                val payload = javaClass.classLoader.getResource("PartsTreeRedefinition.json")?.readText()
                requireNotNull(payload) { "PartsTreeRedefinition.json fixture not found on classpath" }
                val commitId = commitChanges(demoProjectId, payload).atIdAsUuid()

                val response = getElements(demoProjectId, commitId)
                response shouldHaveStatus HttpStatusCode.OK
                val elements = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                check(elements.size > 1) { "expected PartsTreeRedefinition to commit multiple elements, got ${elements.size}" }
            }
        }
    }
}
