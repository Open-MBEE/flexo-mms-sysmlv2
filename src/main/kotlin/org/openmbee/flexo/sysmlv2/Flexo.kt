package org.openmbee.flexo.sysmlv2

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import org.apache.commons.io.IOUtils
import org.apache.jena.graph.GraphMemFactory
import org.apache.jena.rdf.model.Literal
import org.apache.jena.rdf.model.Model
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.rdf.model.ResourceFactory
import org.apache.jena.rdf.model.impl.ModelCom
import org.apache.jena.riot.RDFLanguages
import org.apache.jena.riot.RDFParser
import org.apache.jena.riot.system.PrefixMapAdapter
import java.nio.charset.StandardCharsets

open class RdfBuilder {

}

//fun RdfBuilder.literal(contents: String, langTag: String) {
//    return "${}"
//}

class TurtleBuilder: RdfBuilder() {
    fun thisSubject(vararg pairs: Pair<Property, RDFNode?>) {

    }

    var String.en: Literal
        get() = ResourceFactory.createLangLiteral(this, "en")
        set(v) {}
}

class FlexoRequestBuilder {
    private var headers = mutableListOf<Pair<String, List<String>>>()
    private var path: String = ""
    private var queryParams = mutableMapOf<String, String>()
    private var boddy: String = ""

    fun addHeaders(vararg headers: Pair<String, String>) {
        this.headers.addAll(headers.map { it.first to listOf(it.second) })
    }

    fun orgPath(path: String) {
        this.path = "/orgs/sysml2/$path"
    }

    fun addQueryParams(vararg params: Pair<String, String>) {
        queryParams.putAll(params)
    }

    fun turtle(setup: TurtleBuilder.() -> Unit) {
        this.headers.add("Content-Type" to listOf("text/turtle"))
    }

    fun build(): HttpRequestBuilder {
        return request {
            url {
                protocol = URLProtocol.HTTPS
                host = "localhost"
                port = 8080
                path(path)

                queryParams.forEach {
                    parameters.append(it.key, it.value)
                }
            }

            headersOf(*this@FlexoRequestBuilder.headers.toTypedArray())

            contentType(ContentType("text", "turtle"))

            setBody(body)
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

    suspend fun <TReturn> parseLdp(setup: FlexoResponseHandler.()->TReturn): TReturn {
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
        val handler = FlexoResponseHandler(response.headers["Location"], model)

        return setup(handler)
    }
}

suspend fun flexoRequest(method: HttpMethod, setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    val client = HttpClient()

    val builder = FlexoRequestBuilder()

    setup(builder)

    val request = builder.build()

    val response = client.request(request)

    return FlexoResponse(response)
}

suspend fun flexoRequestGet(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Get, setup)
}

suspend fun flexoRequestPut(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Put, setup)
}

suspend fun flexoRequestPost(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
    return flexoRequest(HttpMethod.Post, setup)
}


class FlexoResponseHandler(val self: String?, val model: Model) {
    val primary: PrefixedRdfPropertiesMap
    val inverse: PrefixedRdfPropertiesMap

    init {
        val outgoingProperties: MutableMap<Property, MutableSet<RDFNode>> = mutableMapOf()
        val incomingProperties: MutableMap<Property, MutableSet<RDFNode>> = mutableMapOf()

        self?.let {
            val selfResource = model.getResource(self)

            for(stmt in selfResource.listProperties()) {
                outgoingProperties.computeIfAbsent(stmt.predicate) { mutableSetOf() }.add(stmt.`object`)
            }

            for(stmt in model.listStatements(null, null, selfResource)) {
                incomingProperties.computeIfAbsent(stmt.predicate) { mutableSetOf() }.add(stmt.subject)
            }
        }

        primary = PrefixedRdfPropertiesMap(outgoingProperties, DEFAULT_PREFIX_MAPPING)
        inverse = PrefixedRdfPropertiesMap(incomingProperties, DEFAULT_PREFIX_MAPPING)
    }
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
