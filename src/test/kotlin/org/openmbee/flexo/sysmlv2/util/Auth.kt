package org.openmbee.flexo.sysmlv2.util

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import java.util.*

/**
 * Authenticates against the running login service to acquire a JWT bearer
 * token for the configured test user. Tokens are cached for the lifetime of
 * the JVM so they are only fetched once per test run.
 */
object Auth {
    private var cachedToken: String? = null

    val loginUrl: String
        get() = System.getenv("FLEXO_LOGIN_URL") ?: "http://localhost:8082/login"

    val testUser: String
        get() = System.getenv("FLEXO_TEST_USER") ?: "user01"

    val testPassword: String
        get() = System.getenv("FLEXO_TEST_PASSWORD") ?: "password1"

    suspend fun getToken(): String {
        cachedToken?.let { return it }
        val client = HttpClient()
        try {
            val response = client.get(loginUrl) {
                header(
                    HttpHeaders.Authorization,
                    "Basic ${Base64.getEncoder().encodeToString("$testUser:$testPassword".toByteArray())}"
                )
            }
            require(response.status.isSuccess()) { "Login failed: ${response.status}" }
            val token = Json.parseToJsonElement(response.bodyAsText())
                .jsonObject["token"]!!.jsonPrimitive.content
            cachedToken = token
            return token
        } finally {
            client.close()
        }
    }

    fun getTokenBlocking(): String = runBlocking { getToken() }

    fun clearToken() {
        cachedToken = null
    }
}
