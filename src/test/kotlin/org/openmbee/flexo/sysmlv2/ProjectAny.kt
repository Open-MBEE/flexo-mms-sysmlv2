package org.openmbee.flexo.sysmlv2

import io.kotest.core.test.TestCase
import org.openmbee.flexo.sysmlv2.util.*
import java.util.*

/**
 * Base class for project-scoped tests. A demo project is created before
 * each test so subclasses can immediately exercise project-scoped APIs
 * (commits, elements, branches, tags, queries, etc.).
 */
open class ProjectAny : CommonSpec() {
    val demoProjectId: UUID = UUID.fromString("92de867a-4eb5-4e9d-83d2-acf0a8166564")
    val demoProjectName: String = "Test Project"
    val demoProjectDescription: String = "A test project"

    override suspend fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        testApplication {
            putProject(demoProjectId, demoProjectName, demoProjectDescription)
        }
    }
}
