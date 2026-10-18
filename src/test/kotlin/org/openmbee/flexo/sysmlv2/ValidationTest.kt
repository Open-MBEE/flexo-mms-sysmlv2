package org.openmbee.flexo.sysmlv2

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.test.Enabled
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContain
import io.kotest.matchers.string.shouldMatch
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.ApplicationTestBuilder
import kotlinx.serialization.json.*
import org.openmbee.flexo.sysmlv2.util.*
import org.openmbee.opensysml.Connection
import org.openmbee.opensysml.ConnectionOptions
import org.openmbee.opensysml.ConversionOptions
import kotlin.system.measureTimeMillis

private val openSysmlUrl: String? = System.getenv("FLEXO_OPENSYSML_URL")?.takeIf { it.isNotBlank() }

private val requiresOpenSysml = { _: io.kotest.core.test.TestCase ->
    if (openSysmlUrl == null) Enabled.disabled(
        "FLEXO_OPENSYSML_URL is unset: start sysml-grpc with " +
            "'docker compose -f src/test/resources/docker-compose.yml up -d' and run with FLEXO_OPENSYSML_URL=localhost:50051"
    ) else Enabled.enabled
}

private val benchmark = { _: io.kotest.core.test.TestCase ->
    if (openSysmlUrl == null || System.getenv("FLEXO_VALIDATION_BENCHMARK").isNullOrBlank()) Enabled.disabled(
        "set FLEXO_VALIDATION_BENCHMARK=1 (and FLEXO_OPENSYSML_URL) to time validated commits"
    ) else Enabled.enabled
}

/**
 * Validation of the model a commit would leave at the branch head (`flexo.opensysml.validate`).
 * The startup and `off` tests run always; the rest need a sysml-grpc at FLEXO_OPENSYSML_URL.
 */
class ValidationTest : ProjectAny() {
    private val clean = "package P { part def A; part a : A; }"
    private val cycle = "package P { part def A :> B; part def B :> A; }"
    private val off = mapOf("flexo.opensysml.validate" to "off")
    private val warn = mapOf("flexo.opensysml.validate" to "warn")
    private val reject = mapOf("flexo.opensysml.validate" to "reject")

    private suspend fun ApplicationTestBuilder.postNotation(body: String, query: String = ""): HttpResponse =
        httpPost("/projects/$demoProjectId/commits$query") {
            contentType(ContentType.Text.Plain)
            setBody(body)
        }

    private fun HttpResponse.diagnostics(): List<String> = headers.getAll(DIAGNOSTIC_HEADER) ?: emptyList()

    private suspend fun HttpResponse.error(): JsonObject = Json.parseToJsonElement(bodyAsText()).jsonObject.also {
        it["@type"]!!.jsonPrimitive.content shouldBe "Error"
    }

    private suspend fun ApplicationTestBuilder.commitCount(): Int {
        val response = getCommits(demoProjectId)
        response.status shouldBe HttpStatusCode.OK
        return Json.parseToJsonElement(response.bodyAsText()).jsonArray.size
    }

    private suspend fun ApplicationTestBuilder.elements(commitId: String): List<JsonObject> {
        val response = httpGet("/projects/$demoProjectId/commits/$commitId/elements")
        response.status shouldBe HttpStatusCode.OK
        return Json.parseToJsonElement(response.bodyAsText()).jsonArray.map { it.jsonObject }
    }

    /** The API JSON OpenSysML itself writes for [notation], through the client (not the service under test). */
    private fun apiJson(notation: String): List<JsonObject> {
        val (host, port) = openSysmlUrl!!.split(":").let { it[0] to it[1].toInt() }
        Connection.open(ConnectionOptions.builder().service(host, port).autoStart(false).build()).use { connection ->
            val conversion = connection.convert(
                notation, "api-json",
                ConversionOptions.defaults().withFromFormat("sysml").withIdForm("uuid")
            )
            return Json.parseToJsonElement(conversion.content()).jsonArray.map { it.jsonObject }
        }
    }

    private fun changeList(elements: List<JsonObject>): String = buildJsonObject {
        put("@type", "Commit")
        putJsonArray("change") {
            for (element in elements) addJsonObject {
                put("@type", "DataVersion")
                put("payload", element)
                putJsonObject("identity") {
                    put("@id", element["@id"]!!.jsonPrimitive.content)
                    put("@type", "DataIdentity")
                }
            }
        }
    }.toString()

    /**
     * `part a : Missing;` as a JSON client sends it: the elements of [clean] without the
     * definition `A`, so `a` and its FeatureTyping reference an element the model does not have.
     */
    private fun unresolvedTypeChange(): String {
        val elements = apiJson(clean)
        val definition = elements.first { it["@type"]!!.jsonPrimitive.content == "PartDefinition" }["@id"]!!.jsonPrimitive.content
        val dropped = elements.filter {
            it["@id"]!!.jsonPrimitive.content == definition ||
                it["ownedRelatedElement"]?.jsonArray?.any { owned -> owned.jsonObject["@id"]?.jsonPrimitive?.content == definition } == true
        }
        return changeList(elements - dropped.toSet())
    }

    /** About 500 elements: [definitions] part definitions and as many typed part usages. */
    private fun generated(definitions: Int): String = buildString {
        append("package G {")
        for (i in 0 until definitions) append(" part def D$i;")
        for (i in 0 until definitions) append(" part p$i : D$i;")
        append(" }")
    }

    init {
        "warn and reject refuse to start without an OpenSysML service" {
            for (level in listOf("warn", "reject")) {
                val failure = shouldThrowAny {
                    testApplication(mapOf("flexo.opensysml.url" to "", "flexo.opensysml.validate" to level)) {
                        httpGet("/projects")
                    }
                }
                failure.message.shouldNotBeNull() shouldContain "FLEXO_OPENSYSML_URL"
            }
            // an unknown level is refused at startup too, whatever the url
            shouldThrowAny {
                testApplication(mapOf("flexo.opensysml.validate" to "sometimes")) { httpGet("/projects") }
            }.message.shouldNotBeNull() shouldContain "off, warn, reject"
        }

        "off is the default and commits without consulting OpenSysML" {
            testApplication(mapOf("flexo.opensysml.url" to "")) {
                val commit = commitChanges(demoProjectId, """{"@type":"Commit","change":[
                    {"@type":"DataVersion","identity":{"@id":"e1","@type":"DataIdentity"},
                     "payload":{"@id":"e1","@type":"PartUsage","declaredName":"a","type":[{"@id":"e2"}]}}]}""")
                commit.status shouldBe HttpStatusCode.OK
                commit.diagnostics().shouldBeEmpty()
                val explicit = commitChanges(demoProjectId, """{"@type":"Commit","change":[]}""")
                explicit.status shouldBe HttpStatusCode.OK
                // raising the level needs the service
                val raised = httpPost("/projects/$demoProjectId/commits?validate=reject") {
                    setJsonBody("""{"@type":"Commit","change":[]}""")
                }
                raised.status shouldBe HttpStatusCode.BadRequest
                raised.error()["description"]!!.jsonPrimitive.content shouldContain "FLEXO_OPENSYSML_URL"
            }
        }

        "off stores a broken model unchanged".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication(off) {
                val response = postNotation(cycle)
                response.status shouldBe HttpStatusCode.OK
                response.diagnostics().shouldBeEmpty()
                elements(response.atId()).count { it["@type"]!!.jsonPrimitive.content == "PartDefinition" } shouldBe 2
            }
        }

        "warn commits and answers the diagnostics as headers".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication(warn) {
                val before = commitCount()
                // `part a : Missing;` as JSON: nothing at the head resolves the reference
                val json = commitChanges(demoProjectId, unresolvedTypeChange())
                json.status shouldBe HttpStatusCode.OK
                json.diagnostics().shouldHaveSize(1)[0] shouldContain "error"
                commitCount() shouldBe before + 1

                val notation = postNotation(cycle)
                notation.status shouldBe HttpStatusCode.OK
                // the cycle's A also resolves the typing committed above
                val diagnostics = notation.diagnostics()
                diagnostics shouldHaveSize 2
                // located in the notation of the whole head, which OpenSysML writes over several lines
                diagnostics[0] shouldMatch Regex("error at \\d+:\\d+: A participates in a specialization cycle")
                diagnostics[1] shouldContain "B participates in a specialization cycle"
                commitCount() shouldBe before + 2

                val ok = postNotation(clean, "?replace=true")
                ok.status shouldBe HttpStatusCode.OK
                ok.diagnostics().shouldBeEmpty()
            }
        }

        "reject refuses a model with errors with 422 and writes nothing".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication(reject) {
                val before = commitCount()
                val notation = postNotation(cycle)
                notation.status shouldBe HttpStatusCode.UnprocessableEntity
                val description = notation.error()["description"]!!.jsonPrimitive.content
                description shouldContain "2 error(s)"
                description shouldContain "A participates in a specialization cycle"
                notation.diagnostics() shouldHaveSize 2
                notation.diagnostics()[0] shouldContain "error at 1:"

                val json = commitChanges(demoProjectId, unresolvedTypeChange())
                json.status shouldBe HttpStatusCode.UnprocessableEntity
                json.diagnostics() shouldHaveSize 1
                json.error()["description"]!!.jsonPrimitive.content shouldContain "1 error(s)"
                commitCount() shouldBe before

                // notation with an unresolved name cannot be stored at all: refused before validation
                postNotation("package P { part a : Missing; }").status shouldBe HttpStatusCode.BadRequest
                commitCount() shouldBe before
            }
        }

        "reject commits a clean model, and judges the head with the change applied".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication(reject) {
                val first = postNotation(clean)
                first.status shouldBe HttpStatusCode.OK
                first.diagnostics().shouldBeEmpty()
                val stored = elements(first.atId())
                val a = stored.first { it["declaredName"]?.jsonPrimitive?.contentOrNull == "a" }
                val definition = stored.first { it["declaredName"]?.jsonPrimitive?.contentOrNull == "A" }["@id"]!!.jsonPrimitive.content

                // a change referencing only elements already at the head is valid in their company
                val typing = stored.first { it["@type"]!!.jsonPrimitive.content == "FeatureTyping" }
                val retyped = commitChanges(demoProjectId, changeList(listOf(
                    JsonObject(a + ("declaredShortName" to JsonPrimitive("a1")))
                )))
                retyped.status shouldBe HttpStatusCode.OK
                retyped.diagnostics().shouldBeEmpty()
                elements(retyped.atId()).first { it["@id"] == a["@id"] }["declaredShortName"]!!.jsonPrimitive.content shouldBe "a1"

                // deleting the definition alone leaves its membership without a member: an error
                val membership = stored.first { it["memberElement"]?.jsonObject?.get("@id")?.jsonPrimitive?.content == definition }
                val dangling = commitChanges(demoProjectId, """{"@type":"Commit","change":[
                    {"@type":"DataVersion","identity":{"@id":"$definition","@type":"DataIdentity"},"payload":null}]}""")
                dangling.status shouldBe HttpStatusCode.UnprocessableEntity
                dangling.error()["description"]!!.jsonPrimitive.content shouldContain "membership"

                // with its membership and typing gone too, `a` is simply untyped
                val deleted = commitChanges(demoProjectId, """{"@type":"Commit","change":[
                    {"@type":"DataVersion","identity":{"@id":"$definition","@type":"DataIdentity"},"payload":null},
                    {"@type":"DataVersion","identity":{"@id":"${membership["@id"]!!.jsonPrimitive.content}","@type":"DataIdentity"},"payload":null},
                    {"@type":"DataVersion","identity":{"@id":"${typing["@id"]!!.jsonPrimitive.content}","@type":"DataIdentity"},"payload":null}]}""")
                deleted.status shouldBe HttpStatusCode.OK
                deleted.diagnostics().shouldBeEmpty()
                val remaining = elements(deleted.atId())
                remaining.none { it["@id"]!!.jsonPrimitive.content == definition } shouldBe true
                (remaining.first { it["@id"] == a["@id"] }["type"]?.jsonArray ?: JsonArray(emptyList())).shouldBeEmpty()

                // replace=true is validated as the whole new model
                postNotation(cycle, "?replace=true").status shouldBe HttpStatusCode.UnprocessableEntity
                val replaced = postNotation(clean, "?replace=true")
                replaced.status shouldBe HttpStatusCode.OK
                replaced.diagnostics().shouldBeEmpty()
            }
        }

        "a request may raise the configured level but not lower it".config(enabledOrReasonIf = requiresOpenSysml) {
            testApplication(off) {
                val raised = postNotation(cycle, "?validate=warn")
                raised.status shouldBe HttpStatusCode.OK
                raised.diagnostics() shouldHaveSize 2
                postNotation(cycle, "?validate=reject").status shouldBe HttpStatusCode.UnprocessableEntity
                postNotation(cycle, "?validate=off").status shouldBe HttpStatusCode.OK
                val unknown = postNotation(cycle, "?validate=strict")
                unknown.status shouldBe HttpStatusCode.BadRequest
                unknown.error()["description"]!!.jsonPrimitive.content shouldContain "off, warn, reject"
            }
            testApplication(warn) {
                val loosened = postNotation(cycle, "?validate=off")
                loosened.status shouldBe HttpStatusCode.BadRequest
                loosened.error()["description"]!!.jsonPrimitive.content shouldContain "cannot loosen the configured level warn"
                postNotation(cycle, "?validate=warn").status shouldBe HttpStatusCode.OK
                postNotation(cycle, "?validate=reject").status shouldBe HttpStatusCode.UnprocessableEntity
            }
            testApplication(reject) {
                for (level in listOf("off", "warn")) {
                    val loosened = postNotation(clean, "?validate=$level")
                    loosened.status shouldBe HttpStatusCode.BadRequest
                    loosened.error()["description"]!!.jsonPrimitive.content shouldContain "cannot loosen the configured level reject"
                }
                // the head holds the cycle committed above, so only a replacing commit can be clean
                postNotation(clean, "?validate=reject").status shouldBe HttpStatusCode.UnprocessableEntity
                postNotation(clean, "?validate=reject&replace=true").status shouldBe HttpStatusCode.OK
            }
        }

        "latency of a validated commit".config(enabledOrReasonIf = benchmark) {
            val models = mapOf("test model (8 elements)" to clean, "generated model (~500 elements)" to generated(100))
            for ((name, model) in models) {
                val timings = mutableMapOf<String, Long>()
                for (level in listOf("off", "reject")) {
                    testApplication(mapOf("flexo.opensysml.validate" to level)) {
                        postNotation(model).status shouldBe HttpStatusCode.OK // warm up
                        val runs = 5
                        var total = 0L
                        var size = 0
                        repeat(runs) {
                            var response: HttpResponse? = null
                            total += measureTimeMillis { response = postNotation(model) }
                            response!!.status shouldBe HttpStatusCode.OK
                            size = elements(response!!.atId()).size
                        }
                        timings[level] = total / runs
                        println("$name, $size stored elements, validate=$level: ${total / runs} ms per commit (mean of $runs)")
                    }
                }
                println("$name: validation adds ${timings["reject"]!! - timings["off"]!!} ms")
            }
        }
    }
}
