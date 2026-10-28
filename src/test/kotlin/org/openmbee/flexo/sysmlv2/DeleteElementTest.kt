package org.openmbee.flexo.sysmlv2

import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.string.shouldContain as stringShouldContain
import io.kotest.matchers.string.shouldNotContain as stringShouldNotContain
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Deleting an element (null payload) must not leave dangling references
 * in other elements — neither single-valued refs nor array annotations.
 */
class DeleteElementTest : ProjectAny() {
    val targetId = "7b1f3c4d-0000-4000-8000-000000000001"
    val keepId = "7b1f3c4d-0000-4000-8000-000000000002"
    val singleRefId = "7b1f3c4d-0000-4000-8000-000000000003"
    val arrayRefId = "7b1f3c4d-0000-4000-8000-000000000004"

    init {
        "deleting an element removes it and all references to it" {
            testApplication {
                commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$targetId"},
                                "payload": {"@type": "Feature", "name": "target"}
                            },
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$keepId"},
                                "payload": {"@type": "Feature", "name": "keep"}
                            },
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$singleRefId"},
                                "payload": {"@type": "PartUsage", "name": "singleRef", "owner": {"@id": "$targetId"}}
                            },
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$arrayRefId"},
                                "payload": {
                                    "@type": "FlowUsage",
                                    "name": "arrayRef",
                                    "featureChain": [{"@id": "$targetId"}, {"@id": "$keepId"}]
                                }
                            }
                        ]
                    }
                    """.trimIndent()
                ).atId()

                val deleteCommitId = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$targetId"},
                                "payload": null
                            }
                        ]
                    }
                    """.trimIndent()
                ).atId()

                val body = getElements(demoProjectId, deleteCommitId).bodyAsText()
                val elements = Json.parseToJsonElement(body).jsonArray
                val ids = elements.map { it.jsonObject["@id"]!!.jsonPrimitive.content }

                // the deleted element is gone, everything else survives
                ids shouldNotContain targetId
                ids shouldContain singleRefId
                ids shouldContain arrayRefId

                // no element still references the deleted id anywhere
                body stringShouldNotContain targetId

                // the array reference to a surviving element is intact
                val arrayRef = elements.first {
                    it.jsonObject["@id"]!!.jsonPrimitive.content == arrayRefId
                }.toString()
                arrayRef stringShouldContain keepId
            }
        }
    }
}
