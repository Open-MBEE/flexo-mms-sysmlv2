package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
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
        "GET elements after commit" {
            testApplication {
                val changeJson = """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id":"bb1d79c2-1306-5b35-a807-93e46fc3431c"},
                                "payload": {
                                    "@type": "PartDefinition",
                                    "name": "Spacecraft System"
                                }
                            }
                        ]
                    }
                """.trimIndent()
                val commitResponse = commitChanges(demoProjectId, changeJson)
                commitResponse shouldHaveStatus HttpStatusCode.OK

                // commit response is expected to include an `@id` for the
                // newly-minted commit; pull it out and use it to query elements
                val body = commitResponse.bodyAsText()
                val commitId = Json.parseToJsonElement(body)
                    .jsonObject["@id"]?.jsonPrimitive?.content
                    ?: error("commit response missing @id: $body")

                getElements(demoProjectId, UUID.fromString(commitId)).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                    val elements = Json.parseToJsonElement(bodyAsText()).jsonArray
                    check(elements.isNotEmpty()) { "expected at least one element in commit $commitId" }
                }
            }
        }

        "GET roots returns root elements after commit" {
            testApplication {
                val changeJson = """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id":"bb1d79c2-1306-5b35-a807-93e46fc3431d"},
                                "payload": {
                                    "@type": "PartDefinition",
                                    "name": "Root Part"
                                }
                            }
                        ]
                    }
                """.trimIndent()
                val commitResponse = commitChanges(demoProjectId, changeJson)
                commitResponse shouldHaveStatus HttpStatusCode.OK
                val commitId = Json.parseToJsonElement(commitResponse.bodyAsText())
                    .jsonObject["@id"]?.jsonPrimitive?.content
                    ?: error("commit response missing @id")

                getRoots(demoProjectId, UUID.fromString(commitId)).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
