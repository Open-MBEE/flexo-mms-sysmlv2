package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.ktor.client.shouldHaveStatus
import io.ktor.http.*
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Soft-deleted resources must be invisible through single-resource GETs,
 * not just filtered from list responses; and POST must not silently
 * overwrite an existing resource.
 */
class SoftDeleteTest : ProjectAny() {
    val elementChange = """
        {
            "change": [
                {
                    "@type": "DataVersion",
                    "identity": {"@id": "22222222-2222-4222-8222-222222222222"},
                    "payload": {"@type": "PartDefinition", "name": "anything"}
                }
            ]
        }
    """.trimIndent()

    init {
        "GET a soft-deleted project by id is a 404" {
            testApplication {
                deleteProject(demoProjectId) shouldHaveStatus HttpStatusCode.OK
                getProject(demoProjectId) shouldHaveStatus HttpStatusCode.NotFound
            }
        }

        "GET a soft-deleted branch by id is a 404" {
            testApplication {
                val commitId = commitChanges(demoProjectId, elementChange).atId()
                val branchId = createBranch(demoProjectId, commitId, "doomed").atId()

                deleteBranch(demoProjectId, branchId) shouldHaveStatus HttpStatusCode.OK
                getBranch(demoProjectId, branchId) shouldHaveStatus HttpStatusCode.NotFound
            }
        }

        "GET a soft-deleted tag by id is a 404" {
            testApplication {
                val commitId = commitChanges(demoProjectId, elementChange).atId()
                val tagId = createTag(demoProjectId, commitId, "v-doomed").atId()

                deleteTag(demoProjectId, tagId) shouldHaveStatus HttpStatusCode.OK
                getTag(demoProjectId, tagId) shouldHaveStatus HttpStatusCode.NotFound
            }
        }

        "POST /projects with an existing @id is a 409, not a silent overwrite" {
            testApplication {
                val response = httpPost("/projects") {
                    setJsonBody(
                        """
                        {
                            "@type": "Project",
                            "@id": "$demoProjectId",
                            "name": "usurper"
                        }
                        """.trimIndent()
                    )
                }
                response shouldHaveStatus HttpStatusCode.Conflict
            }
        }
    }
}
