package org.openmbee.flexo.sysmlv2

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.util.*

class ApplicationTest {

    @Test
    fun testStore() = testApplication {
        environment {
            config = ApplicationConfig("application.test.conf")
        }

        val payload = javaClass.classLoader.getResource("PartsTreeRedefinition.json")?.readText()

        val r1 = client.post("/projects/81ef/commits") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody(payload)
        }

        //assume the docker compose for layer1 is up locally
        //set the token string from login at the companion object below, sysml2 org should have been created on flexo
        //this is calling to this sysmlv2 service's project api
        val res = client.post("/projects") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody("""
                {
                    "@type": "Project",
                    "name": "test",
                    "description": "test"
                }
            """.trimIndent())
        }
        println(res.status)
        val res2 = client.get("/projects/92de867a-4eb5-4e9d-83d2-acf0a8166564") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
            }
        }.apply {
            println(bodyAsText())
        }
    }

    companion object {
        var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJmbGV4by1tbXMtYXVkaWVuY2UiLCJpc3MiOiJodHRwOi8vZmxleG8tbW1zLXNlcnZpY2VzIiwidXNlcm5hbWUiOiJsZGFwL3VzZXIvdXNlcjAxIiwiZ3JvdXBzIjpbXSwiZXhwIjoxNzM3NzkxMDA3fQ.45Rs1WQOnlea6wXJ9v9uIVl1o3x1TfchIicDYSMFgRw"

        @JvmStatic
        @BeforeAll
        fun setup() = runTest {
            val client = HttpClient()
            //login and get token
            val tokenResponse =
                client.get("http://localhost:8082/login") { // assume docker compose for flexo is up
                    headers {
                        append(
                            HttpHeaders.Authorization,
                            "Basic ${Base64.getEncoder().encodeToString("user01:password1".toByteArray())}"
                        )
                    }
                }

            val body = tokenResponse.bodyAsText()
            val status = tokenResponse.status
            require(status.isSuccess()) { "Unsuccessful response $status: $body" }

            token = Json.parseToJsonElement(tokenResponse.bodyAsText()).jsonObject["token"]!!.jsonPrimitive.content
            //pre create default sysml2 org that flexo requests use

            client.put("http://localhost:8080/orgs/sysml2") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $token")
                        append(HttpHeaders.ContentType, "text/turtle")
                    }
                    setBody("""
                        <> dct:title "sysml2"@en .
                    """)
                }

        }
    }
}

