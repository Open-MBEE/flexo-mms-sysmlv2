package org.openmbee.flexo.sysmlv2.util

import io.kotest.core.spec.Spec
import io.kotest.core.spec.style.StringSpec
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*

/**
 * Base spec for all sysmlv2 tests. Tests assume the docker-compose stack
 * (quad-store, layer1, sysmlv2 service, login service) is already running.
 *
 * The `beforeSpec` hook ensures the `sysmlv2` org exists on layer1 so that
 * the sysmlv2 service can stand up project repositories underneath it.
 */
open class CommonSpec : StringSpec() {
    val layer1BaseUrl: String
        get() = System.getenv("FLEXO_LAYER1_BASE_URL") ?: "http://localhost:8080"

    val sysmlv2BaseUrl: String
        get() = System.getenv("SYSMLV2_BASE_URL") ?: "http://localhost:8083"

    override suspend fun beforeSpec(spec: Spec) {
        super.beforeSpec(spec)
        // Ensure the sysmlv2 org exists on layer1 so subsequent project
        // creation calls have a parent org to attach to. The PUT is
        // idempotent: layer1 returns 200/304 if the org already exists.
        val token = Auth.getToken()
        val client = HttpClient()
        try {
            client.put("$layer1BaseUrl/orgs/sysmlv2") {
                header(HttpHeaders.Authorization, "Bearer $token")
                header(HttpHeaders.ContentType, "text/turtle")
                setBody("""<> <http://purl.org/dc/terms/title> "sysmlv2"@en .""")
            }
        } finally {
            client.close()
        }
    }
}
