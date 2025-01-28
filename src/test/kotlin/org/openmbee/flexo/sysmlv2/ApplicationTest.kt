package org.openmbee.flexo.sysmlv2

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.server.config.*
import io.ktor.server.testing.*
import io.ktor.utils.io.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeAll
import java.io.File
import java.util.*

class ApplicationTest {

    @Test
    fun testStore() = testApplication {
        //assume the docker compose for layer1 is up locally
        //set the token string from login at the companion object below, sysml2 org should have been created on flexo
        //this is calling to this sysmlv2 service's api project put
        /*
        val res = client.put("/projects/92de867a-4eb5-4e9d-83d2-acf0a8166564") {
            headers {
                append(HttpHeaders.Authorization, "Bearer $token")
                append(HttpHeaders.ContentType, "application/json")
            }
            setBody("""
                {"description": "test", "name": "test"}
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
         */
        val res3 = client.post("/projects/92de867a-4eb5-4e9d-83d2-acf0a8166564/commits") {
            header(HttpHeaders.Authorization, "Bearer $token")
            contentType(ContentType.Application.Json)
            setBody(changeJson)
        }
        val res4 = client.get("/projects/92de867a-4eb5-4e9d-83d2-acf0a8166564/commits/92de867a-4eb5-4e9d-83d2-acf0a8166564/elements") {
            header(HttpHeaders.Authorization, "Bearer $token")
        }
        res4
    }

    companion object {
        var token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJmbGV4by1tbXMtYXVkaWVuY2UiLCJpc3MiOiJodHRwOi8vZmxleG8tbW1zLXNlcnZpY2VzIiwidXNlcm5hbWUiOiJsZGFwL3VzZXIvdXNlcjAxIiwiZ3JvdXBzIjpbXSwiZXhwIjoxNzM3NzkxMDA3fQ.45Rs1WQOnlea6wXJ9v9uIVl1o3x1TfchIicDYSMFgRw"

        val changeJson = """
{"change": [
    {
      "@type": "DataVersion",
      "identity": {"@id":"bb1d79c2-1306-5b35-a807-93e46fc3431c"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Spacecraft System",
        "connectionEnd": [{
          "@id": "e03dab82-7485-4aa3-a63c-3e9bed70ad82"
          },
          {
          "@id": "c98b83b4-9911-436c-ae0c-af6b95b81ea2"
          }]
      }
    },
        {
      "@type": "DataVersion",
      "identity": {"@id":"e03dab82-7485-4aa3-a63c-3e9bed70ad82"},
      "payload": {
        "@type": "ConnectionDefinition",
        "name":"ConnectionDefinition...1"
      }
    },
    {
      "@type": "DataVersion",
      "identity": {"@id":"c98b83b4-9911-436c-ae0c-af6b95b81ea2"},
      "payload": {
        "@type": "ConnectionDefinition",
        "name":"ConnectionDefinition...2"
      }
    },
    {
      "@type": "DataVersion",
      "identity": {"@id":"299028fa-6527-4c82-9017-8de82e0663f3"},
      "payload": {
        "@type": "PartDefinition",
        "name":"Payload System"
      }
    }
  ]
}
""".trimIndent()

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

            token = Json.parseToJsonElement(tokenResponse.bodyAsText()).jsonObject["token"]!!.jsonPrimitive.content
            //pre create default sysml2 org that flexo requests use

            client.put("http://localhost:8080/orgs/sysml2") {
                    headers {
                        append(HttpHeaders.Authorization, "Bearer $token")
                        append(HttpHeaders.ContentType, "text/turtle")
                    }
                    setBody(
                        """
                    <> dct:title "sysml2"@en .
                """.trimIndent()
                    )
                }

            client.put("http://localhost:8080/orgs/sysml2/repos/92de867a-4eb5-4e9d-83d2-acf0a8166564") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                    append(HttpHeaders.ContentType, "text/turtle")
                }
                setBody(
                    """
                    <> dct:title "testproject"@en .
                """.trimIndent()
                )
            }
            client.put("http://localhost:8080/orgs/sysml2/repos/92de867a-4eb5-4e9d-83d2-acf0a8166564/branches/master/graph") {
                headers {
                    append(HttpHeaders.Authorization, "Bearer $token")
                    append(HttpHeaders.ContentType, "text/turtle")
                }
                setBody("<urn:this> <urn:always> <urn:matches> .")
            }

        }
    }
}

