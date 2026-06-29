package org.openmbee.flexo.sysmlv2

import io.kotest.core.test.TestCase
import org.openmbee.flexo.sysmlv2.util.*

/**
 * Base class for project-scoped tests. After [CommonSpec.beforeEach]
 * resets the triplestore and recreates the sysmlv2 org, this class
 * additionally creates a demo project so subclasses can immediately
 * exercise project-scoped APIs (commits, elements, branches, tags,
 * queries, etc.).
 */
open class ProjectAny : CommonSpec() {
    val demoProjectId: String = "92de867a-4eb5-4e9d-83d2-acf0a8166564"
    val demoProjectName: String = "Test Project"
    val demoProjectDescription: String = "A test project"

    override suspend fun beforeEach(testCase: TestCase) {
        super.beforeEach(testCase)
        // POST /projects (not PUT) — the sysmlv2 PUT handler does a GET on
        // the existing repo first and skips creating the default branch
        // (post=false in createOrUpdateProject). Since CommonSpec.beforeEach
        // wipes the triplestore before each test, the fixed demo ID will
        // never conflict, so POST with an explicit @id is safe and gives us
        // a fully-initialised project (repo + default branch + scratches).
        testApplication {
            createProject(demoProjectId, demoProjectName, demoProjectDescription)
        }
    }
}
