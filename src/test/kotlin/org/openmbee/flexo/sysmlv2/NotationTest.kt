package org.openmbee.flexo.sysmlv2

import io.kotest.core.test.Enabled
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.ApplicationTestBuilder
import kotlinx.serialization.json.*
import org.openmbee.flexo.sysmlv2.util.*
import org.openmbee.opensysml.Connection
import org.openmbee.opensysml.ConnectionOptions
import org.openmbee.opensysml.ConversionOptions

private val openSysmlUrl: String? = System.getenv("FLEXO_OPENSYSML_URL")?.takeIf { it.isNotBlank() }

private val requiresOpenSysml = { _: io.kotest.core.test.TestCase ->
    if (openSysmlUrl == null) Enabled.disabled(
        "FLEXO_OPENSYSML_URL is unset: start sysml-grpc with " +
            "'docker compose -f src/test/resources/docker-compose.yml up -d' and run with FLEXO_OPENSYSML_URL=localhost:50051"
    ) else Enabled.enabled
}

/**
 * Textual notation in/out through OpenSysML. The "off" tests run always; the
 * round-trip tests need a sysml-grpc at FLEXO_OPENSYSML_URL and skip otherwise.
 */
class NotationTest : ProjectAny() {
    private val model = "package P { part def A; part a : A; }"
    private val notationOff = mapOf("flexo.opensysml.url" to "")

    private suspend fun ApplicationTestBuilder.postNotation(body: String, type: ContentType = ContentType.Text.Plain): HttpResponse =
        httpPost("/projects/$demoProjectId/commits") {
            contentType(type)
            setBody(body)
        }

    private suspend fun HttpResponse.commitId(): String =
        Json.parseToJsonElement(bodyAsText()).jsonObject["@id"]!!.jsonPrimitive.content

    private suspend fun ApplicationTestBuilder.elementsJson(commitId: String): List<JsonObject> {
        val response = httpGet("/projects/$demoProjectId/commits/$commitId/elements")
        response.status shouldBe HttpStatusCode.OK
        return Json.parseToJsonElement(response.bodyAsText()).jsonArray.map { it.jsonObject }
    }

    /** (type, id, declaredName) triples: the element set independent of ordering and derived properties. */
    private fun identities(elements: List<JsonObject>): List<Triple<String, String, String?>> = elements.map {
        Triple(
            it["@type"]!!.jsonPrimitive.content,
            it["@id"]!!.jsonPrimitive.content,
            it["declaredName"]?.jsonPrimitive?.contentOrNull
        )
    }

    /** Re-parse notation through the OpenSysML client itself (not the service under test). */
    private fun reparse(notation: String): List<JsonObject> {
        val (host, port) = openSysmlUrl!!.split(":").let { it[0] to it[1].toInt() }
        Connection.open(ConnectionOptions.builder().service(host, port).autoStart(false).build()).use { connection ->
            val conversion = connection.convert(
                notation, "api-json",
                ConversionOptions.defaults().withFromFormat("sysml").withIdForm("uuid")
            )
            return Json.parseToJsonElement(conversion.content()).jsonArray.map { it.jsonObject }
        }
    }

    init {
        "notation POST is 415 Unsupported Media Type when OpenSysML is not configured" {
            testApplication(notationOff) {
                for (type in listOf(ContentType.Text.Plain, SYSML_NOTATION_TYPE, KERML_NOTATION_TYPE)) {
                    val response = postNotation(model, type)
                    response.status shouldBe HttpStatusCode.UnsupportedMediaType
                    val error = Json.parseToJsonElement(response.bodyAsText()).jsonObject
                    error["@type"]!!.jsonPrimitive.content shouldBe "Error"
                    error["description"]!!.jsonPrimitive.content shouldContain "FLEXO_OPENSYSML_URL"
                }
            }
        }

        "notation GET is 406 Not Acceptable when OpenSysML is not configured, JSON still served" {
            testApplication(notationOff) {
                val commit = commitChanges(demoProjectId, """{"@type":"Commit","change":[
                    {"@type":"DataVersion","identity":{"@id":"e1","@type":"DataIdentity"},
                     "payload":{"@id":"e1","@type":"Package","declaredName":"P"}}]}""")
                commit.status shouldBe HttpStatusCode.OK
                val commitId = commit.commitId()
                for (type in listOf(ContentType.Text.Plain, SYSML_NOTATION_TYPE)) {
                    val response = httpGet("/projects/$demoProjectId/commits/$commitId/elements") { accept(type) }
                    response.status shouldBe HttpStatusCode.NotAcceptable
                    Json.parseToJsonElement(response.bodyAsText()).jsonObject["@type"]!!.jsonPrimitive.content shouldBe "Error"
                }
                elementsJson(commitId).map { it["@type"]!!.jsonPrimitive.content } shouldBe listOf("Package")
            }
        }

        "notation round trip: text/plain in, JSON and notation out, re-parsed element set matches".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication {
                val post = postNotation(model)
                post.status shouldBe HttpStatusCode.OK
                val commitId = post.commitId()

                val elements = elementsJson(commitId)
                elements.map { it["@type"]!!.jsonPrimitive.content } shouldContainExactlyInAnyOrder listOf(
                    "Namespace", "Package", "PartDefinition", "PartUsage", "FeatureTyping",
                    "OwningMembership", "OwningMembership", "OwningMembership"
                )
                elements.filter { it["declaredName"] != null }
                    .associate { it["declaredName"]!!.jsonPrimitive.content to it["@type"]!!.jsonPrimitive.content } shouldBe
                    mapOf("P" to "Package", "A" to "PartDefinition", "a" to "PartUsage")

                for (type in listOf(ContentType.Text.Plain, SYSML_NOTATION_TYPE)) {
                    val text = httpGet("/projects/$demoProjectId/commits/$commitId/elements") { accept(type) }
                    text.status shouldBe HttpStatusCode.OK
                    text.contentType().shouldNotBeNull().withoutParameters() shouldBe type
                    val notation = text.bodyAsText()
                    notation shouldContain "package P"
                    notation shouldContain "part def A"
                    notation shouldContain "part a : A"
                    identities(reparse(notation)) shouldContainExactlyInAnyOrder identities(elements)
                }
            }
        }

        "application/vnd.sysml and application/vnd.kerml bodies are committed".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication {
                val sysml = postNotation(model, SYSML_NOTATION_TYPE)
                sysml.status shouldBe HttpStatusCode.OK
                elementsJson(sysml.commitId()).mapNotNull { it["declaredName"]?.jsonPrimitive?.content } shouldContainExactlyInAnyOrder listOf("P", "A", "a")

                val kerml = postNotation("package K { class C; feature f : C; }", KERML_NOTATION_TYPE)
                kerml.status shouldBe HttpStatusCode.OK
                val named = elementsJson(kerml.commitId()).filter { it["declaredName"] != null }
                    .associate { it["declaredName"]!!.jsonPrimitive.content to it["@type"]!!.jsonPrimitive.content }
                // the branch keeps the earlier SysML commit; the KerML elements are added to it
                named.keys shouldContainExactlyInAnyOrder listOf("P", "A", "a", "K", "C", "f")
                named["K"] shouldBe "Package"
            }
        }

        "notation with syntax errors is 400 with the diagnostics listed".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication {
                val response = postNotation("package P { part def A;")
                response.status shouldBe HttpStatusCode.BadRequest
                val error = Json.parseToJsonElement(response.bodyAsText()).jsonObject
                error["@type"]!!.jsonPrimitive.content shouldBe "Error"
                error["description"]!!.jsonPrimitive.content shouldContain "error at 1:"
            }
        }
    }
}
