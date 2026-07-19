package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.ApplicationTestBuilder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Execution tests for POST /projects/{id}/query-results and
 * GET /projects/{id}/queries/{queryId}/results — i.e. the constraint→SPARQL
 * translation in `toSparql`/`runQuery`, which query CRUD tests never touch.
 */
class QueryResultsTest : ProjectAny() {
    init {
        val alphaId = "5f7e1c1a-0000-4000-8000-000000000001"
        val betaId = "5f7e1c1a-0000-4000-8000-000000000002"
        val gammaId = "5f7e1c1a-0000-4000-8000-000000000003"
        val docId = "5f7e1c1a-0000-4000-8000-000000000004"

        // two named PartDefinitions, one named PartUsage, and one
        // Documentation element with no name at all
        val seedChanges = """
            {
                "change": [
                    {
                        "@type": "DataVersion",
                        "identity": {"@id": "$alphaId"},
                        "payload": {"@type": "PartDefinition", "name": "Alpha"}
                    },
                    {
                        "@type": "DataVersion",
                        "identity": {"@id": "$betaId"},
                        "payload": {"@type": "PartDefinition", "name": "Beta"}
                    },
                    {
                        "@type": "DataVersion",
                        "identity": {"@id": "$gammaId"},
                        "payload": {"@type": "PartUsage", "name": "Gamma"}
                    },
                    {
                        "@type": "DataVersion",
                        "identity": {"@id": "$docId"},
                        "payload": {"@type": "Documentation", "body": "docs"}
                    }
                ]
            }
        """.trimIndent()

        suspend fun ApplicationTestBuilder.queryResultIds(whereJson: String): List<String> {
            val response = httpPost("/projects/$demoProjectId/query-results") {
                setJsonBody("""{ "@type": "Query", "where": $whereJson }""")
            }
            response shouldHaveStatus HttpStatusCode.OK
            return Json.parseToJsonElement(response.bodyAsText()).jsonArray
                .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
        }

        "POST query-results - '=' with a single-element array matches by name" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """{"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": ["Alpha"]}"""
                )
                ids shouldContainExactlyInAnyOrder listOf(alphaId)
            }
        }

        "POST query-results - '=' with a multi-element array is set membership" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """{"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": ["Alpha", "Beta"]}"""
                )
                ids shouldContainExactlyInAnyOrder listOf(alphaId, betaId)
            }
        }

        "POST query-results - 'in' behaves like multi-value '='" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """{"@type": "PrimitiveConstraint", "operator": "in", "property": "name", "value": ["Beta", "Gamma"]}"""
                )
                ids shouldContainExactlyInAnyOrder listOf(betaId, gammaId)
            }
        }

        "POST query-results - '=' on @type filters by metaclass" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """{"@type": "PrimitiveConstraint", "operator": "=", "property": "@type", "value": ["PartUsage"]}"""
                )
                ids shouldContainExactlyInAnyOrder listOf(gammaId)
            }
        }

        "POST query-results - '=' null matches elements lacking the property" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """{"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": null}"""
                )
                ids shouldContainExactlyInAnyOrder listOf(docId)
            }
        }

        "POST query-results - 'or' composite over different properties matches both sides" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """
                    {
                        "@type": "CompositeConstraint",
                        "operator": "or",
                        "constraint": [
                            {"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": ["Alpha"]},
                            {"@type": "PrimitiveConstraint", "operator": "=", "property": "body", "value": ["docs"]}
                        ]
                    }
                    """.trimIndent()
                )
                ids shouldContainExactlyInAnyOrder listOf(alphaId, docId)
            }
        }

        "POST query-results - 'and' composite intersects constraints" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val ids = queryResultIds(
                    """
                    {
                        "@type": "CompositeConstraint",
                        "operator": "and",
                        "constraint": [
                            {"@type": "PrimitiveConstraint", "operator": "=", "property": "@type", "value": ["PartDefinition"]},
                            {"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": ["Beta"]}
                        ]
                    }
                    """.trimIndent()
                )
                ids shouldContainExactlyInAnyOrder listOf(betaId)
            }
        }

        "GET queries/{queryId}/results - runs a saved query" {
            testApplication {
                commitChanges(demoProjectId, seedChanges).atId()
                val queryId = createQuery(demoProjectId, "name", "Gamma").atId()

                val response = httpGet("/projects/$demoProjectId/queries/$queryId/results")
                response shouldHaveStatus HttpStatusCode.OK
                val ids = Json.parseToJsonElement(response.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldContainExactlyInAnyOrder listOf(gammaId)
            }
        }

        "POST queries - missing select is a 400, not a server error" {
            testApplication {
                val response = httpPost("/projects/$demoProjectId/queries") {
                    setJsonBody(
                        """
                        {
                            "@type": "Query",
                            "where": {"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": ["x"]}
                        }
                        """.trimIndent()
                    )
                }
                response shouldHaveStatus HttpStatusCode.BadRequest
            }
        }
    }
}
