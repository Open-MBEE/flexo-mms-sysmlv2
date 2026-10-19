package org.openmbee.flexo.sysmlv2

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import org.openmbee.opensysml.Connection
import org.openmbee.opensysml.ConnectionOptions
import org.openmbee.opensysml.Conversion
import org.openmbee.opensysml.ConversionException
import org.openmbee.opensysml.ConversionOptions
import org.openmbee.opensysml.Diagnostic
import java.util.Optional

/** Media types carrying SysML v2 / KerML textual notation. `text/plain` is read as SysML. */
val SYSML_NOTATION_TYPE = ContentType("application", "vnd.sysml")
val KERML_NOTATION_TYPE = ContentType("application", "vnd.kerml")
val NOTATION_TYPES = listOf(ContentType.Text.Plain, SYSML_NOTATION_TYPE, KERML_NOTATION_TYPE)

/** The notation media type [contentType] denotes (parameters dropped), or null for any other type. */
fun notationType(contentType: ContentType?): ContentType? =
    contentType?.let { ct -> NOTATION_TYPES.firstOrNull { ct.withoutParameters() == it } }

/** OpenSysML format name for a notation media type. */
fun notationFormat(type: ContentType): String = if (type == KERML_NOTATION_TYPE) "kerml" else "sysml"

/**
 * Notation media type the Accept header asks for, or null when JSON (the default) is wanted.
 * Each supported representation takes the quality of its most specific matching Accept range
 * (`text/plain;q=0` beside a `text` wildcard therefore refuses plain text). The notation type with
 * the highest quality wins only when that quality is strictly above JSON's; equal quality (a full
 * wildcard, or JSON beside a notation type) and the absence of an Accept header keep JSON as before.
 */
fun ApplicationRequest.acceptedNotationType(): ContentType? {
    val ranges = acceptItems().mapNotNull { item ->
        val type = try { ContentType.parse(item.value) } catch (e: BadContentTypeFormatException) { return@mapNotNull null }
        type.withoutParameters() to item.quality
    }
    if (ranges.isEmpty()) return null

    // (quality, specificity) of the most specific range matching the candidate; no match = unacceptable
    fun rank(candidate: ContentType): Pair<Double, Int> {
        var best: Pair<Double, Int>? = null
        for ((range, quality) in ranges) {
            val specificity = when {
                range.contentType == "*" -> 0
                range.contentSubtype == "*" -> if (range.contentType == candidate.contentType) 1 else continue
                range == candidate -> 2
                else -> continue
            }
            if (best == null || specificity > best.second) best = quality to specificity
        }
        return best ?: (0.0 to -1)
    }

    val order = compareBy<Pair<Double, Int>>({ it.first }, { it.second })
    val json = rank(ContentType.Application.Json)
    val notation = NOTATION_TYPES.map { it to rank(it) }
        .filter { it.second.first > 0.0 }
        .maxWithOrNull { a, b -> order.compare(a.second, b.second) }
        ?: return null
    return if (notation.second.first > json.first) notation.first else null
}

/** Notation was requested while `flexo.opensysml.url` is not configured (415 on input, 406 on output). */
class NotationUnavailableError(val status: HttpStatusCode, message: String) : RuntimeException(message)

/** OpenSysML reported errors for the notation or the element set; the message lists them. */
class NotationConversionError(val diagnostics: List<Diagnostic>, message: String) : RuntimeException(message)

/**
 * How OpenSysML's verdict on the model a commit would leave at the branch head is applied
 * (`flexo.opensysml.validate`, raised per request by `?validate=`): not at all, reported in
 * [DIAGNOSTIC_HEADER] headers while the commit proceeds, or refusing the commit on any error.
 */
enum class ValidationLevel {
    OFF, WARN, REJECT;

    companion object {
        val names: String get() = entries.joinToString(", ") { it.name.lowercase() }

        fun parseOrNull(value: String): ValidationLevel? =
            entries.firstOrNull { it.name.equals(value.trim(), ignoreCase = true) }
    }
}

/** The model the commit would leave at the branch head has errors (validation level `reject`). */
class ModelValidationError(val diagnostics: List<Diagnostic>, message: String) : RuntimeException(message)

fun Diagnostic.describe(): String {
    val where = span().map { " at ${it.startLine()}:${it.startColumn()}" }.orElse("")
    return "${severity().name.lowercase()}$where: ${message()}"
}

/** Response header carrying each OpenSysML diagnostic reported to the client, one per line. */
const val DIAGNOSTIC_HEADER = "X-SysML-Diagnostic"

/** Logs the diagnostics of [source] (a conversion, a validation) and adds one [DIAGNOSTIC_HEADER] line per diagnostic. */
fun ApplicationCall.reportDiagnostics(source: String, diagnostics: List<Diagnostic>) {
    diagnostics.forEach { diagnostic ->
        application.log.info("OpenSysML $source: ${diagnostic.describe()}")
        response.headers.append(DIAGNOSTIC_HEADER, diagnostic.headerValue())
    }
}

/** The header form of [describe]: one line, with the control characters a header cannot carry replaced. */
fun Diagnostic.headerValue(): String = describe().replace(Regex("[\\r\\n\\t]+"), " ").trim()

/**
 * Textual notation in/out through a running OpenSysML `sysml-grpc` service at `host:port`.
 * The client is pointed at that address only; it never downloads or starts a binary of its own.
 */
class OpenSysmlNotation(hostPort: String) : AutoCloseable {
    private val host: String
    private val port: Int
    private var connection: Connection? = null

    init {
        val address = hostPort.trim().removePrefix("http://").removePrefix("https://").trimEnd('/')
        val colon = address.lastIndexOf(':')
        require(colon > 0 && colon < address.length - 1) {
            "flexo.opensysml.url must be host:port of a running sysml-grpc, got '$hostPort'"
        }
        host = address.substring(0, colon)
        port = address.substring(colon + 1).toIntOrNull()?.takeIf { it in 1..65535 }
            ?: throw IllegalArgumentException("flexo.opensysml.url has an invalid port in '$hostPort'")
    }

    val address: String get() = "$host:$port"

    @Synchronized
    private fun connection(): Connection = connection ?: Connection.open(
        ConnectionOptions.builder().service(host, port).autoStart(false).build()
    ).also { connection = it }

    /** Notation (SysML or KerML) → API JSON element array, with OpenSysML's non-error diagnostics. */
    suspend fun toApiJson(notation: String, fromFormat: String): Pair<JsonArray, List<Diagnostic>> {
        val options = ConversionOptions.defaults().withFromFormat(fromFormat).withIdForm("uuid")
        val conversion = convert(notation, "api-json", options)
        val elements = Json.parseToJsonElement(conversion.content()) as? JsonArray
            ?: throw NotationConversionError(emptyList(), "OpenSysML returned no element array for the $fromFormat notation")
        return elements to conversion.diagnostics()
    }

    /** API JSON element array → notation in [toFormat] (`sysml` or `kerml`). */
    suspend fun toNotation(elements: JsonArray, toFormat: String): Conversion =
        convert(elements.toString(), toFormat, ConversionOptions.defaults().withFromFormat("api-json"))

    /**
     * OpenSysML's diagnostics for the model the API JSON [elements] form: the element set is
     * written as SysML notation and that notation is parsed and checked (`GetDiagnostics`).
     * An element set OpenSysML refuses to write is reported through its own diagnostics, or
     * the refusal itself as an error when it carries none; the warnings of a successful
     * conversion precede the model's diagnostics.
     */
    suspend fun diagnose(elements: JsonArray): List<Diagnostic> = withContext(Dispatchers.IO) {
        val options = ConversionOptions.defaults().withFromFormat("api-json")
        val conversion = try {
            connection().convert(elements.toString(), "sysml", options)
        } catch (e: ConversionException) {
            return@withContext e.diagnostics().ifEmpty {
                listOf(Diagnostic(Diagnostic.Severity.ERROR, e.message ?: "conversion failed", "", Optional.empty()))
            }
        }
        if (conversion.diagnostics().any { it.severity() == Diagnostic.Severity.ERROR }) {
            return@withContext conversion.diagnostics()
        }
        conversion.diagnostics() + connection().parse(conversion.content()).diagnostics()
    }

    private suspend fun convert(content: String, toFormat: String, options: ConversionOptions): Conversion {
        val conversion = try {
            withContext(Dispatchers.IO) { connection().convert(content, toFormat, options) }
        } catch (e: ConversionException) {
            throw NotationConversionError(e.diagnostics(), failureMessage(options.fromFormat().orElse(null), toFormat, e.diagnostics(), e.message))
        }
        val errors = conversion.diagnostics().filter { it.severity() == Diagnostic.Severity.ERROR }
        if (errors.isNotEmpty()) {
            throw NotationConversionError(errors, failureMessage(options.fromFormat().orElse(null), toFormat, errors, null))
        }
        return conversion
    }

    private fun failureMessage(fromFormat: String?, toFormat: String, diagnostics: List<Diagnostic>, fallback: String?): String {
        val head = "Conversion from $fromFormat to $toFormat failed"
        if (diagnostics.isEmpty()) return fallback?.let { "$head: $it" } ?: head
        return diagnostics.joinToString(separator = "\n", prefix = "$head:\n") { "- ${it.describe()}" }
    }

    @Synchronized
    override fun close() {
        connection?.close()
        connection = null
    }
}
