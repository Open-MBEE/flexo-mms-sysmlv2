package com.modeldriven.sysmlv2.apiService
/**
import com.sun.jdi.request.ExceptionRequest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

import java.time.LocalDateTime
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.util.pipeline.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.apache.commons.io.IOUtils

import org.apache.jena.graph.GraphMemFactory
import org.apache.jena.graph.Node
import org.apache.jena.graph.NodeFactory
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
import org.apache.jena.vocabulary.RDF

import projects.sysml.FlexoRequestBuilder
import projects.sysml.MMS
import projects.sysml.SYSMLV2
import projects.sysml.apis.commitFromModel
import projects.sysml.flexoRequest
import projects.sysml.flexoRequestPost
import projects.sysml.forward
import projects.sysml.literal
import projects.sysml.models.Commit
import projects.sysml.stringify
import java.util.UUID
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.isNotEmpty


open public class RdfServiceflexo(modelGraph:GraphConfig, projectGraph:GraphConfig ) : RdfService(modelGraph, projectGraph) {
    // Add export to real DB using DB interface.
    //val flexoRepo: Repository = SailRepository(MemoryStore())
    val flexoCon: String = "TEMPORARY" //RepositoryConnection = flexoRepo.connection

    suspend fun postFlexoUpdate(projectId:String, updateText:String, commit: Commit){


        // first, lock the given commit
        val flexoResponseLock = flexoRequestPost {
            orgPath("/repos/$projectId/locks")

            turtle {
                """
                    <> mms:commit mor-commit:${commit.previousCommit} .
                """.trimIndent()
            }
        }

        // forward failures to client
        if(flexoResponseLock.isFailure()) {
            throw Exception("Flexo lock failure") //ToDo better handler
        }

        // extract lock ID from response model
        val lockId = flexoResponseLock.parseLdp {
            primary[MMS.id].literal()!!
        }

        // submit POST request to commit model
        //suspend fun flexoRequest(method: HttpMethod, auth: String, setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
        //suspend fun PipelineContext<Unit, ApplicationCall>.flexoRequestPost(setup: FlexoRequestBuilder.() -> Unit): FlexoResponse {
        //    return flexoRequest(HttpMethod.Post, call.request.headers["Authorization"]!!, setup)
        val flexoResponseUpdate = flexoRequest(HttpMethod.Post, call.request.headers["Authorization"]!!,{
            //flexoRequestPost {
            orgPath("/repos/$projectId/locks/$lockId/update")

            // construct body payload
            sparqlUpdate {
                updateText
            }
        })

        // forward failures to client
        if(flexoResponseUpdate.isFailure()) {
            throw Exception("Flexo response failure: $flexoResponseUpdate") //ToDo better handlerflexoResponseUpdate)
        }

        // parse the response model, convert it to JSON, and reply to client
        call.respond(flexoResponseUpdate.parseLdp {
            commitFromModel(self!!, primary, UUID.fromString(projectId))
        })
    }

    /**
     * Utilize end transaction to perform a SparQL update to flexo based on the contents of the updater object.
     */
    override fun endTransaction(transactionID:String?){
        for (gc in this.rdfGraphs.values) {
            val updater = gc.updater
            val update = gc.updater.getCombinedUpdate()
            if (!update.isEmpty()) {
                if (this.exportStream !=null) {// ToDo log service
                    this.exportStream.println("##SparQL Update ${LocalDateTime.now()}")
                    this.exportStream.println(update)
                    this.exportStream.flush()
                }
                try {
                    this.flexoCon.prepareUpdate(update).execute()
                    if (this.exportStream !=null) {// ToDo log service
                        this.exportStream.println("##SparQL Update complete ${LocalDateTime.now()}")
                        this.exportStream.flush()
                    }
                } catch (e: Exception) {
                    this.exportStream.println("##SparQL Update Exception $e")
                    println("##SparQL Update Exception query \n$update")
                    logApiError("SparQL Update Exception $e")

                }
            }
        }
    }

    /**
     * Get the DB specific "helper" mixin for a GraphConfig.
     */
    fun getGraphHelper(gc:GraphConfig):flexoHelper{
        if (gc.graphImpl == null) {gc.graphImpl = flexoHelper(gc, this)}
        return gc.graphImpl as flexoHelper
    }

    /**
     * Override of SparqlQuery returns the DB specific QueryHelper initilized with the Query.
     */
    override fun sparqlQuery(theQuery:String):RdfQueryHelperStub {return RdfQueryHelper(this,theQuery)}
    /**
     * A "mixin class for GraphConfig that propvides DB specific properties and operations for that graph.
     * In the case of rdf$J, provides the "model" and "context" objects and sets namespaces.
     */
    class flexoHelper(gc:GraphConfig, rdfService:RdfServiceflexo) {
        var model = rdfService.flexoCon // the model object on which to perform queries and such.
        val context:String = gc.baseURI //model.valueFactory.createIRI(gc.baseURI, "") // The named graph

    }

    /**
     * The `RdfQueryHelper` class is a concrete implementation for executing and managing RDF queries using the
     * flexo framework. It extends the `RdfQueryHelperStub` base class, providing actual functionality to execute
     * SPARQL queries, traverse results, and retrieve query bindings in various formats.
     *
     * This class encapsulates the behavior related to processing query results, ensuring proper handling and
     * closing of resources.
     *
     * @constructor Creates an instance of `RdfQueryHelper` with the specified RDF service and query string.
     * @param rdfService The `RdfServiceflexo` instance used for executing the query.
     * @param theQuery The SPARQL query string to execute through the RDF service.
     */
    class RdfQueryHelper(rdfService: RdfServiceflexo, theQuery:String) : RdfQueryHelperStub(rdfService, theQuery) {
        val queryResult = rdfService.flexoCon.prepareTupleQuery(theQuery).evaluate()
        var currentBindingSet: BindingSet? = null
        init {
            //println("Executing query: \n$theQuery")
        }

        override fun next(): Boolean {currentBindingSet = queryResult.next(); return this.hasNext()}
        override fun hasNext():Boolean  {
            val isNext =queryResult.hasNext()
            if (!isNext) this.close()
            return isNext
        }
        override fun getValueString(key:String):String? {return currentBindingSet?.getValue(key)}
        override fun isLiteralValue(key:String):Boolean = currentBindingSet?.getValue(key) is Literal
        override fun nextBindingJson(): JsonObject {
            val elements = HashMap<String, JsonPrimitive>() // Can we make this ordered?
            if (this.next()) {
                if (currentBindingSet != null) {
                    for (binding in currentBindingSet!!) {
                        elements[binding.name] = JsonPrimitive(binding.value.stringValue())
                    }
                }
            } else
                this.close()
            return JsonObject(elements)
        }
        override fun close() {queryResult.close()}
    }
}
**/