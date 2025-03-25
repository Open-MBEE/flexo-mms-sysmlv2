package org.openmbee.flexo.sysmlv2

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import org.apache.commons.io.IOUtils
import org.apache.jena.datatypes.xsd.XSDDatatype
import org.apache.jena.graph.GraphMemFactory
import org.apache.jena.graph.Node
import org.apache.jena.rdf.model.*
import org.apache.jena.rdf.model.impl.ModelCom
import org.apache.jena.riot.RDFLanguages
import org.apache.jena.riot.RDFParser
import org.apache.jena.riot.system.PrefixMapAdapter
import org.apache.jena.shared.PrefixMapping
import org.apache.jena.vocabulary.RDF
import java.nio.charset.StandardCharsets

fun String.reindent(width: Int): String {
    return "\n"+this.trimIndent().prependIndent("    ".repeat(width))
}

open class RdfBuilder {
    var String.en: Literal
        get() = ResourceFactory.createLangLiteral(this, "en")
        set(v) {}
}

fun escapeRdfDoubleQuotedLiteralContents(contents: String): String {
    return contents
        .replace("\\", "\\\\")
        .replace("\"", "\\\"")
        .replace("\n", "\\n")
}

fun escapeRdfIri(iri: String): String {
    return iri.replace("([\\x00-\\x20<>\"{}|^`\\\\]|%(?![0-9A-F][0-9A-F]))".toRegex()) {
        // hex-encode the offending character
        val hex = it.value[0].code.toString(16)

        // if it's wider than 4 hex digits, use \UXXXXXXXX form; else use \uXXXX
        if (hex.length > 4) {
            "\\U" + hex.padStart(8, '0')
        } else {
            "\\u" + hex.padStart(4, '0')
        }
    }
}

fun shortenIri(iri: String, prefixes: PrefixMapping): String {
    return if(iri == RDF.type.uri) return "a"
    else if(iri.startsWith("urn:")) {
        prefixes.getNsURIPrefix(iri.substringBeforeLast(':')+':')?.let {
            "$it:${iri.substringAfterLast(':')}"
        } ?: "<${escapeRdfIri(iri)}>"
    }
    else {
        prefixes.qnameFor(iri) ?: "<${escapeRdfIri(iri)}>"
    }
}

@JvmOverloads
fun Node.stringify(prefixes: PrefixMapping= DEFAULT_PREFIX_MAPPING): String {
    return when {
        isVariable -> "?$name"
        isBlank -> "_:$blankNodeLabel"
        isLiteral -> {
            val lexical = "\""+ escapeRdfDoubleQuotedLiteralContents(literalLexicalForm)+"\""

            when {
                literalLanguage.isNotEmpty() -> "$lexical@${literalLanguage}"
                literalDatatypeURI.isNullOrEmpty() -> lexical
                else -> {
                    when(literalDatatype) {
                        XSDDatatype.XSDboolean -> literalLexicalForm
                        XSDDatatype.XSDdecimal -> literalLexicalForm
                        XSDDatatype.XSDstring -> lexical
                        else -> "$lexical^^<${literalDatatypeURI}>"
                    }
                }
            }
        }
        isURI -> shortenIri(uri, prefixes)
        else -> toString()
    }
}

@JvmOverloads
fun RDFNode.stringify(prefixes: PrefixMapping = DEFAULT_PREFIX_MAPPING): String {
    return when {
        isLiteral -> {
            val lit = asLiteral()
            val lexical = "\""+ escapeRdfDoubleQuotedLiteralContents(lit.lexicalForm)+"\""

            when {
                lit.language.isNotEmpty() -> "$lexical@${lit.language}"
                lit.datatypeURI.isNullOrEmpty() -> lexical
                else -> "$lexical^^<${lit.datatypeURI}>"
            }
        }
        isURIResource -> shortenIri(asResource().uri, prefixes)
        isAnon -> "_:${asResource().id.labelString}"
        else -> toString()
    }
}

class TurtleBuilder: RdfBuilder() {
    fun thisSubject(vararg pairs: Pair<Property, RDFNode?>): String {
        return "<> "+pairs.joinToString(" ; ") { (predicate, value) -> value?.let {
            "<${predicate.uri}> ${value.stringify()}"
        }?: ""}+" .\n"
    }
}

class SparqlQueryBuilder {}

class SparqlUpdateBuilder {}

class FlexoRequestBuilder(
    private val method: HttpMethod,
    private val config: FlexoConfig = GlobalFlexoConfig
) {
    private var flexoProtocol = config.protocol
    private var flexoHost = config.host
    private var flexoPort = config.port

    private var headers = mutableListOf<Pair<String, List<String>>>()
    private var path: String = ""
    private var queryParams = mutableMapOf<String, String>()
    private var body: String = ""

    var timeout = config.defaultTimeout

    fun addHeaders(vararg headers: Pair<String, String>) {
        this.headers.addAll(headers.map { it.first to listOf(it.second) })
    }

    fun orgPath(path: String, org: String=config.org) {
        this.path = "/orgs/$org$path"
    }

    fun addQueryParams(vararg params: Pair<String, String>) {
        queryParams.putAll(params)
    }

    fun turtle(setup: TurtleBuilder.() -> String) {
        this.headers.add("Content-Type" to listOf("text/turtle"))

        body = setup(TurtleBuilder())
    }

    fun sparqlQuery(setup: SparqlQueryBuilder.() -> String) {
        this.headers.add("Content-Type" to listOf("application/sparql-query"))

        body = setup(SparqlQueryBuilder())
    }

    fun sparqlUpdate(setup: SparqlUpdateBuilder.() -> String) {
        this.headers.add("Content-Type" to listOf("application/sparql-update"))

        body = setup(SparqlUpdateBuilder())
    }

    fun build(): HttpRequestBuilder {
        return request {
            method = this@FlexoRequestBuilder.method

            url {
                protocol = flexoProtocol
                host = flexoHost
                port = flexoPort

                path(path)

                queryParams.forEach {
                    parameters.append(it.key, it.value)
                }
            }

            this@FlexoRequestBuilder.headers.forEach { (key, value) ->
                header(key, value.joinToString())
            }

            setBody(this@FlexoRequestBuilder.body)

            timeout {
                requestTimeoutMillis = this@FlexoRequestBuilder.timeout * 1000
            }
        }
    }
}

class FlexoResponse(
    val response: HttpResponse,
) {
    fun isSuccess(): Boolean {
        return response.status.isSuccess()
    }

    fun isFailure(): Boolean {
        return !isSuccess()
    }

    suspend fun <TReturn> parseLdp(setup: FlexoModelHandlerWithFocalNode.()->TReturn): TReturn {
        return setup(parseModel {
            // create response around parsed model and subject from Location header
            FlexoModelHandlerWithFocalNode(model, response.headers["Location"])
        })
    }


    suspend fun <TReturn> parseModel(setup: FlexoModelHandler.()->TReturn): TReturn {
        // parse Content-Type from response header
        val contentType = ContentType.parse(response.headers["Content-Type"]?: "text/turtle")

        // convert Content-Type to Jena RDF language
        val language = RDFLanguages.contentTypeToLang(contentType.withoutParameters().toString())

        // create memory model
        val model = ModelCom(GraphMemFactory.createGraphMem())

        // parse input document into model
        RDFParser.create().apply {
            prefixes(PrefixMapAdapter(DEFAULT_PREFIX_MAPPING))
            lang(language)
    //        errorHandler(ErrorHandlerFactory.errorHandlerWarn)
    //        if(baseIri != null) base(baseIri)
            source(IOUtils.toInputStream(response.bodyAsText(), StandardCharsets.UTF_8))
            parse(model)
        }

        val handler = FlexoModelHandler(model, DEFAULT_PREFIX_MAPPING)

        return setup(handler)
    }
}

suspend fun PipelineContext<*, ApplicationCall>.flexoRequest(method: HttpMethod, setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    // prepare client
    val client = HttpClient() {
        install(HttpTimeout)
    }

    // create request builder
    val builder = FlexoRequestBuilder(method)
    val auth = call.request.headers["Authorization"]?: GlobalFlexoConfig.auth
    // forward auth header from client
    builder.addHeaders(HttpHeaders.Authorization to auth)

    // apply caller setup
    setup(builder)

    // build request
    val request = builder.build()

    // submit request
    val response = client.request(request)

    // wrap response
    return FlexoResponse(response)
}

suspend fun PipelineContext<*, ApplicationCall>.flexoRequestGet(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Get, setup)
}

suspend fun PipelineContext<*, ApplicationCall>.flexoRequestPut(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Put, setup)
}

suspend fun PipelineContext<*, ApplicationCall>.flexoRequestPost(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Post, setup)
}

open class FlexoModelHandler(val model: Model, val prefixes: PrefixMapping) {
    fun indexOut(iri: String?): Map<Property, Set<RDFNode>> {
        val outgoingProperties: MutableMap<Property, MutableSet<RDFNode>> = mutableMapOf()
        val selfResource = model.getResource(iri)

        for(stmt in selfResource.listProperties()) {
            outgoingProperties.computeIfAbsent(stmt.predicate) { mutableSetOf() }.add(stmt.`object`)
        }

        return PrefixedRdfPropertiesMap(outgoingProperties, prefixes)
    }

    fun indexInv(iri: String?): Map<Property, Set<RDFNode>> {
        val incomingProperties: MutableMap<Property, MutableSet<RDFNode>> = mutableMapOf()
        val selfResource = model.getResource(iri)

        for(stmt in model.listStatements(null, null, selfResource)) {
            incomingProperties.computeIfAbsent(stmt.predicate) { mutableSetOf() }.add(stmt.subject)
        }

        return PrefixedRdfPropertiesMap(incomingProperties, prefixes)
    }

    var String.uriSuffix: String
        get() = if(startsWith(SYSMLV2.VOCABULARY)) substringAfterLast('#') else substringAfterLast('/')
        set(v) {}

    var String.urnSuffix: String
        get() = substringAfterLast(':')
        set(v) {}

    var String.autoSuffix: String
        get() = if(startsWith(SYSMLV2.BASE)) urnSuffix else uriSuffix
        set(v) {}
}

class FlexoModelHandlerWithFocalNode(
    model: Model,
    val focalIri: String?,
    prefixes: PrefixMapping= DEFAULT_PREFIX_MAPPING
): FlexoModelHandler(model, prefixes) {
    val focalOutgoing = indexOut(focalIri)
    val focalIncoming = indexInv(focalIri)
}

suspend fun PipelineContext<*, ApplicationCall>.forward(flexoResponse: FlexoResponse) {
    val response = flexoResponse.response

    call.respondText(response.bodyAsText(), response.contentType(), response.status) {
        // forward the response headers
        headersOf(*response.headers.names().map { name ->
            name to headers.getAll(name).orEmpty()
        }.toTypedArray())
    }
}
