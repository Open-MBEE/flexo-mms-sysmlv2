package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.shouldBe
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Round-trip coverage for array-valued element properties (e.g.
 * featureChain) — the regression surface of issue #19, where multi-valued
 * properties disappeared from /elements responses.
 */
class ElementArrayTest : ProjectAny() {
    val chainAId = "6a0e2b3c-0000-4000-8000-00000000000a"
    val chainBId = "6a0e2b3c-0000-4000-8000-00000000000b"
    val flowId = "6a0e2b3c-0000-4000-8000-00000000000f"

    init {
        "array-of-ref property committed via the API round-trips in order" {
            testApplication {
                val commitId = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$chainAId"},
                                "payload": {"@type": "Feature", "name": "chainA"}
                            },
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$chainBId"},
                                "payload": {"@type": "Feature", "name": "chainB"}
                            },
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$flowId"},
                                "payload": {
                                    "@type": "FlowUsage",
                                    "name": "flow1",
                                    "featureChain": [{"@id": "$chainBId"}, {"@id": "$chainAId"}]
                                }
                            }
                        ]
                    }
                    """.trimIndent()
                ).atId()

                val element = getElement(demoProjectId, commitId, flowId).bodyAsJsonObject()
                val chain = element["featureChain"]!!.jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                // the JSON annotation preserves the original array order
                chain shouldBe listOf(chainBId, chainAId)
            }
        }

        "multi-valued property loaded as raw RDF still appears in /elements" {
            testApplication {
                // write element triples directly to layer1's graph endpoint —
                // no json: annotation triples, as if the model had been
                // ingested by another tool rather than through this API
                val config = testEnv()
                val layer1 = "${config.property("flexo.protocol").getString()}://" +
                        "${config.property("flexo.host").getString()}:" +
                        config.property("flexo.port").getString()
                val org = config.property("flexo.org").getString()
                val defaultBranchId = getProject(demoProjectId).bodyAsJsonObject()
                    .nestedAtId("defaultBranch")
                HttpClient().use { client ->
                    val response = client.put(
                        "$layer1/orgs/$org/repos/$demoProjectId/branches/$defaultBranchId/graph") {
                        header(HttpHeaders.Authorization, authorization())
                        header(HttpHeaders.ContentType, "text/turtle")
                        setBody(
                            """
                            @prefix sysml: <https://www.omg.org/spec/SysML#> .
                            <urn:sysmlv2:element:$flowId> a sysml:FlowUsage ;
                                sysml:name "externalFlow" ;
                                sysml:featureChain <urn:sysmlv2:element:$chainAId>, <urn:sysmlv2:element:$chainBId> .
                            <urn:sysmlv2:element:$chainAId> a sysml:Feature ; sysml:name "a" .
                            <urn:sysmlv2:element:$chainBId> a sysml:Feature ; sysml:name "b" .
                            """.trimIndent()
                        )
                    }
                    require(response.status.isSuccess()) {
                        "failed to load raw RDF into layer1: ${response.status} ${response.bodyAsText()}"
                    }
                }

                // the load created a commit; find it through the API
                val commits = Json.parseToJsonElement(
                    getCommits(demoProjectId).bodyAsText()).jsonArray
                val commitId = commits.first().jsonObject["@id"]!!.jsonPrimitive.content

                val element = getElement(demoProjectId, commitId, flowId).bodyAsJsonObject()
                // annotation-less multi-valued properties must not be dropped;
                // order is best-effort since RDF triples are unordered
                val chain = element["featureChain"]!!.jsonArray
                    .map { it.jsonObject["@id"]!!.jsonPrimitive.content }
                chain shouldContainExactlyInAnyOrder listOf(chainAId, chainBId)
            }
        }

        "commit change whose identity and payload @id disagree is a 400" {
            testApplication {
                val response = commitChanges(
                    demoProjectId,
                    """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id": "$chainAId"},
                                "payload": {"@type": "Feature", "@id": "$chainBId", "name": "mismatch"}
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
