package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldBe
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

class QueryTest : ProjectAny() {
    init {
        "GET /projects/{id}/queries - is empty for a fresh project" {
            testApplication {
                val list = getQueries(demoProjectId)
                list shouldHaveStatus HttpStatusCode.OK
                val items = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                items.size shouldBe 0
            }
        }

        "POST /projects/{id}/queries - creates a query and returns the stored shape" {
            testApplication {
                val response = createQuery(demoProjectId, "name", "Test Part")
                response shouldHaveStatus HttpStatusCode.OK
                val query = response.bodyAsJsonObject()
                query["@type"]!!.jsonPrimitive.content shouldBe "Query"
                query.nestedAtId("owningProject") shouldBe demoProjectId
                // the where-clause should round-trip with the same constraint
                val where = query["where"]!!.jsonObject
                where["@type"]!!.jsonPrimitive.content shouldBe "PrimitiveConstraint"
                where["operator"]!!.jsonPrimitive.content shouldBe "="
                where["property"]!!.jsonPrimitive.content shouldBe "name"
                where["value"]!!.jsonArray[0].jsonPrimitive.content shouldBe "Test Part"
                // server should mint an @id
                response.atIdAsUuid()
            }
        }

        "GET /projects/{id}/queries/{queryId} - returns the matching query" {
            testApplication {
                val queryId = createQuery(demoProjectId, "name", "Test Part").atIdAsUuid()

                val response = getQuery(demoProjectId, queryId)
                response shouldHaveStatus HttpStatusCode.OK
                val query = response.bodyAsJsonObject()
                query["@id"]!!.jsonPrimitive.content shouldBe queryId.toString()
                query["where"]!!.jsonObject["property"]!!.jsonPrimitive.content shouldBe "name"
            }
        }

        "GET /projects/{id}/queries - lists the just-created query" {
            testApplication {
                val queryId = createQuery(demoProjectId, "name", "Listed Part").atIdAsUuid()

                val list = getQueries(demoProjectId)
                list shouldHaveStatus HttpStatusCode.OK
                val items = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                val ids = items.map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldContain queryId.toString()
            }
        }

        "PUT /projects/{id}/queries/{queryId} - updates an existing query" {
            testApplication {
                val queryId = createQuery(demoProjectId, "name", "before").atIdAsUuid()

                // PUT with a new where-clause
                val response = httpPut("/projects/$demoProjectId/queries/$queryId") {
                    setJsonBody(
                        """
                        {
                            "@type": "Query",
                            "select": ["@id"],
                            "where": {
                                "@type": "PrimitiveConstraint",
                                "operator": "=",
                                "property": "name",
                                "value": ["after"]
                            }
                        }
                        """.trimIndent()
                    )
                }
                response shouldHaveStatus HttpStatusCode.OK

                val fetched = getQuery(demoProjectId, queryId).bodyAsJsonObject()
                fetched["where"]!!.jsonObject["value"]!!.jsonArray[0]
                    .jsonPrimitive.content shouldBe "after"
            }
        }

        "DELETE /projects/{id}/queries/{queryId} - removes the query from the list" {
            testApplication {
                val queryId = createQuery(demoProjectId, "name", "to-delete").atIdAsUuid()
                deleteQuery(demoProjectId, queryId) shouldHaveStatus HttpStatusCode.OK

                val list = getQueries(demoProjectId)
                val ids = Json.parseToJsonElement(list.bodyAsText()).jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                ids shouldNotContain queryId.toString()
            }
        }
    }
}
