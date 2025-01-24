package org.openmbee.flexo.sysmlv2

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import org.apache.commons.io.IOUtils
import org.apache.jena.graph.GraphMemFactory
import org.apache.jena.graph.Node
import org.apache.jena.rdf.model.Literal
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.rdf.model.ResourceFactory
import org.apache.jena.rdf.model.impl.ModelCom
import org.apache.jena.riot.RDFLanguages
import org.apache.jena.riot.RDFParser
import org.apache.jena.riot.system.PrefixMapAdapter
import org.apache.jena.shared.impl.PrefixMappingImpl
import java.nio.charset.StandardCharsets
import java.util.*

open class RdfBuilder {

    var String.en: Literal
        get() = ResourceFactory.createLangLiteral(this, "en")
        set(v) {}
}

fun Node.stringify(): String {
    return when {
        isVariable -> "?$name"
        isBlank -> "_:$blankNodeLabel"
        isLiteral -> {
            val lexical = "\""+literalLexicalForm
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")+"\""

            when {
                literalLanguage.isNotEmpty() -> "$lexical@${literalLanguage}"
                literalDatatypeURI.isNullOrEmpty() -> lexical
                else -> "$lexical@${literalDatatypeURI}"
            }
        }
        isURI -> "<"+uri.replace("([\\x00-\\x20<>\"{}|^`\\\\]|%(?![0-9A-F][0-9A-F]))".toRegex()) {
            // hex-encode the offending character
            val hex = it.value[0].code.toString(16)

            // if it's wider than 4 hex digits, use \UXXXXXXXX form; else use \uXXXX
            if (hex.length > 4) {
                "\\U" + hex.padStart(8, '0')
            } else {
                "\\u" + hex.padStart(4, '0')
            }
        }+">"
        else -> toString()
    }
}

fun RDFNode.stringify(): String {
    return when {
        isLiteral -> {
            val lit = this.asLiteral()
            val lexical = "\""+lit.lexicalForm
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")+"\""

            when {
                lit.language.isNotEmpty() -> "$lexical@${lit.language}"
                lit.datatypeURI.isNullOrEmpty() -> lexical
                else -> "$lexical@${lit.datatypeURI}"
            }
        }
        isURIResource -> "<${this.asResource().uri}>"
        isAnon -> "_:${this.asResource().id.labelString}"
        else -> toString()
    }
}

//fun RdfBuilder.literal(contents: String, langTag: String) {
//    return "${}"
//}



class TurtleBuilder: RdfBuilder() {
    fun thisSubject(vararg pairs: Pair<Property, RDFNode?>): String {
        return "<> "+pairs.joinToString(" ; ") { (predicate, value) -> value?.let {
            "<${predicate.uri}> ${value.stringify()}"
        }?: ""}+" .\n"
    }

}

class SparqlQueryBuilder {

}

class SparqlUpdateBuilder {

}

fun PipelineContext<*, ApplicationCall>.sysmlv2ElementIri(uuid: UUID): String {
    return "<urn:sysmlv2:${uuid}>"
}

class FlexoRequestBuilder {
    private var headers = mutableListOf<Pair<String, List<String>>>()
    private var path: String = ""
    private var queryParams = mutableMapOf<String, String>()
    private var body: String = ""

    fun addHeaders(vararg headers: Pair<String, String>) {
        this.headers.addAll(headers.map { it.first to listOf(it.second) })
    }

    fun orgPath(path: String) {
        this.path = "/orgs/sysml2$path"
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
            url {
                protocol = URLProtocol.HTTP
                host = "localhost"
                port = 8080
                path(path)

                queryParams.forEach {
                    parameters.append(it.key, it.value)
                }
            }
            this@FlexoRequestBuilder.headers.forEach { (key, value) ->
                header(key, value.joinToString())
            }
            setBody(this@FlexoRequestBuilder.body)
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

        // create response around parsed model and subject from Location header
        // TODO location is only present if request was a post
        val handler = FlexoModelHandlerWithFocalNode(model, response.headers["Location"])

        return setup(handler)
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

suspend fun flexoRequest(method: HttpMethod, auth: String, setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    val client = HttpClient()

    val builder = FlexoRequestBuilder()

    setup(builder)

    val request = builder.build()
    request.header(HttpHeaders.Authorization, auth)
    request.method = method

    val response = client.request(request)

    return FlexoResponse(response)
}

suspend fun PipelineContext<Unit, ApplicationCall>.flexoRequestGet(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Get, call.request.headers["Authorization"]!!, setup)
}

suspend fun PipelineContext<Unit, ApplicationCall>.flexoRequestPut(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Put, call.request.headers["Authorization"]!!, setup)
}

suspend fun PipelineContext<Unit, ApplicationCall>.flexoRequestPost(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Post, call.request.headers["Authorization"]!!, setup)
}

open class FlexoModelHandler(val model: Model, val prefixes: PrefixMappingImpl) {
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

    var String.suffix: String
        get() = this.substringAfterLast("/")
        set(v) {}
}

class FlexoModelHandlerWithFocalNode(
    model: Model,
    val self: String?,
    prefixes: PrefixMappingImpl= DEFAULT_PREFIX_MAPPING
): FlexoModelHandler(model, prefixes) {
    val primary = indexOut(self)
    val inverse = indexInv(self)
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
