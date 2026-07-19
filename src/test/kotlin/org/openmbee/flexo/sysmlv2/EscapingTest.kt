package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.shouldBe
import io.ktor.http.*
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Client-supplied strings are interpolated into SPARQL updates: literal
 * contents must be escaped (including carriage returns, which previously
 * produced an illegal SPARQL string and failed the whole commit), and
 * element ids must be rejected before reaching an IRI position.
 */
class EscapingTest : ProjectAny() {
    val elementId = "33333333-3333-4333-8333-333333333333"

    init {
        "element name with control and quote characters round-trips" {
            testApplication {
                val commitId = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$elementId"},
                                "payload": {"@type": "PartDefinition", "name": "line1\r\nline2 \"quoted\" back\\slash"}
                            }
                        ]
                    }
                    """.trimIndent()
                ).atId()

                val element = getElement(demoProjectId, commitId, elementId).bodyAsJsonObject()
                element["name"]!!.jsonPrimitive.content shouldBe "line1\r\nline2 \"quoted\" back\\slash"
            }
        }

        "commit with an invalid element @id is a 400" {
            testApplication {
                val response = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "../not/a/valid#id"},
                                "payload": {"@type": "PartDefinition", "name": "evil"}
                            }
                        ]
                    }
                    """.trimIndent()
                )
                response shouldHaveStatus HttpStatusCode.BadRequest
            }
        }

        "commit with an invalid referenced @id is a 400" {
            testApplication {
                val response = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$elementId"},
                                "payload": {"@type": "PartUsage", "owner": {"@id": "urn:evil> . <x"}}
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
