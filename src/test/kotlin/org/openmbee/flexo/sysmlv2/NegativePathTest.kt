package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Requests that reference missing resources or carry malformed payloads
 * must produce client-error status codes, not 500s or fabricated 200s.
 */
class NegativePathTest : ProjectAny() {
    val unknownId = "00000000-dead-4000-8000-000000000000"

    init {
        "GET nonexistent element is a 404, not a fabricated 200" {
            testApplication {
                val commitId = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "11111111-1111-4111-8111-111111111111"},
                                "payload": {"@type": "PartDefinition", "name": "exists"}
                            }
                        ]
                    }
                    """.trimIndent()
                ).atId()

                getElement(demoProjectId, commitId, unknownId) shouldHaveStatus HttpStatusCode.NotFound
            }
        }

        "POST commit to nonexistent project is a 404, not a 500" {
            testApplication {
                val response = commitChanges(
                    unknownId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "11111111-1111-4111-8111-111111111111"},
                                "payload": {"@type": "PartDefinition", "name": "orphan"}
                            }
                        ]
                    }
                    """.trimIndent()
                )
                response shouldHaveStatus HttpStatusCode.NotFound
            }
        }

        "POST query-results to nonexistent project is a 404, not a 500" {
            testApplication {
                val response = httpPost("/projects/$unknownId/query-results") {
                    setJsonBody(
                        """
                        {
                            "@type": "Query",
                            "where": {"@type": "PrimitiveConstraint", "operator": "=", "property": "name", "value": ["x"]}
                        }
                        """.trimIndent()
                    )
                }
                response shouldHaveStatus HttpStatusCode.NotFound
            }
        }

        "commit payload with extra keys next to an @id reference is a 400" {
            testApplication {
                val response = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "11111111-1111-4111-8111-111111111111"},
                                "payload": {
                                    "@type": "PartUsage",
                                    "owner": {"@id": "$unknownId", "bogus": true}
                                }
                            }
                        ]
                    }
                    """.trimIndent()
                )
                response shouldHaveStatus HttpStatusCode.BadRequest
            }
        }
    }
}
