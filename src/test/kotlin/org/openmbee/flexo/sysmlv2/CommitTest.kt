package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.client.statement.*
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

class CommitTest : ProjectAny() {
    init {
        "POST /projects/{id}/commits - commit changes" {
            testApplication {
                val changeJson = """
                    {
                        "change": [
                            {
                                "@type": "DataVersion",
                                "identity": {"@id":"bb1d79c2-1306-5b35-a807-93e46fc3431c"},
                                "payload": {
                                    "@type": "PartDefinition",
                                    "name": "Test Part"
                                }
                            }
                        ]
                    }
                """.trimIndent()
                commitChanges(demoProjectId, changeJson).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }

        "GET /projects/{id}/commits - list commits" {
            testApplication {
                getCommits(demoProjectId).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }

        "POST commits with PartsTreeRedefinition payload" {
            testApplication {
                val payload = javaClass.classLoader.getResource("PartsTreeRedefinition.json")?.readText()
                requireNotNull(payload) { "PartsTreeRedefinition.json fixture not found on classpath" }
                commitChanges(demoProjectId, payload).apply {
                    this shouldHaveStatus HttpStatusCode.OK
                }
            }
        }
    }
}
