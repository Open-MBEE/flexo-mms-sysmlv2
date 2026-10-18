package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldNotContain
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.ApplicationTestBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * An element is stored with one rdf:type, its concrete metaclass; a `@type`
 * constraint on a general metaclass matches through the metaclass hierarchy
 * (`MetaclassHierarchy`, read from the bundled vocabulary graph).
 */
class TypeHierarchyQueryTest : ProjectAny() {
    init {
        val partUsageId = "6a1b2c3d-0000-4000-8000-000000000001"
        val partDefinitionId = "6a1b2c3d-0000-4000-8000-000000000002"

        val seedChanges = """
            {
                "change": [
                    {
                        "@type": "DataVersion",
                        "identity": {"@id": "$partUsageId"},
                        "payload": {"@type": "PartUsage", "name": "wheel"}
                    },
                    {
                        "@type": "DataVersion",
                        "identity": {"@id": "$partDefinitionId"},
                        "payload": {"@type": "PartDefinition", "name": "Wheel"}
                    }
                ]
            }
        """.trimIndent()

        suspend fun ApplicationTestBuilder.idsOfType(vararg types: String, operator: String = "="): List<String> {
            val values = types.joinToString(", ") { "\"$it\"" }
            val response = httpPost("/projects/$demoProjectId/query-results") {
                setJsonBody(
                    """{ "@type": "Query", "where": {"@type": "PrimitiveConstraint", "operator": "$operator", "property": "@type", "value": [$values]} }"""
                )
            }
            response shouldHaveStatus HttpStatusCode.OK
            return Json.parseToJsonElement(response.bodyAsText()).jsonArray
                .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
        }

        "POST query-results - @type = Feature finds a PartUsage" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = idsOfType("Feature")
                ids shouldContain partUsageId
                ids shouldNotContain partDefinitionId
            }
        }

        "POST query-results - @type = Usage finds a PartUsage" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = idsOfType("Usage")
                ids shouldContain partUsageId
                ids shouldNotContain partDefinitionId
            }
        }

        "POST query-results - @type = Element finds a PartUsage and a PartDefinition" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = idsOfType("Element")
                ids shouldContain partUsageId
                ids shouldContain partDefinitionId
            }
        }

        "POST query-results - @type = Definition does not find a PartUsage" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = idsOfType("Definition")
                ids shouldContain partDefinitionId
                ids shouldNotContain partUsageId
            }
        }

        "POST query-results - @type = PartUsage still matches the concrete metaclass exactly" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                idsOfType("PartUsage") shouldContainExactlyInAnyOrder listOf(partUsageId)
            }
        }

        "POST query-results - @type with several values is the union of their specializations" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                idsOfType("Usage", "Definition") shouldContainExactlyInAnyOrder listOf(partUsageId, partDefinitionId)
            }
        }

        "POST query-results - @type in [...] expands each member to its specializations" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                idsOfType("Feature", operator = "in") shouldContainExactlyInAnyOrder listOf(partUsageId)
                idsOfType("Usage", "Definition", operator = "in") shouldContainExactlyInAnyOrder listOf(partUsageId, partDefinitionId)
                idsOfType("Definition", operator = "in") shouldContainExactlyInAnyOrder listOf(partDefinitionId)
            }
        }
    }
}
