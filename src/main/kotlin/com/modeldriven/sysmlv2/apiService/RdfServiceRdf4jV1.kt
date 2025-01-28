/*******************************************************************************
 * SysML 2 RDF API Implementation
 * Copyright (c) 2025 Model Driven Solutions, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of theGNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 *******************************************************************************/

 package com.modeldriven.sysmlv2.apiService

/**
 * Rdf4jService provides an extension to RdfServiceAbstract, using the RDF4J API for managing the graphs.
 * This uses fine grain edfAdd/rdfRemove instead on only using sparql update.
 */

import com.modeldriven.sysmlv2.apiService.RdfServiceRdf4j.SparQLQueryResult4J
import kotlinx.serialization.json.*
import org.eclipse.rdf4j.model.IRI
import org.eclipse.rdf4j.model.util.Values.literal
import org.eclipse.rdf4j.model.Value
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import java.time.LocalDate
import java.time.LocalDateTime

/**
 * RdfServiceRdf4j is a service class that provides an interface for managing and interacting
 * with RDF data using the RDF4J framework. It extends RDF-related capabilities to perform
 * CRUD operations on RDF graphs, translate objects to RDF terms, and manage RDF triples using
 * a specified graph configuration. This uses finer grain add/remove triples instead of all SparQlUpdates.
 *
 * @property rdf4JRepo The repository connection to manage RDF data storage and retrieval.
 * @property rdf4JCon The connection used to interact with the RDF4J repository.
 * @property rdfSubject The subject for RDF graph manipulation operations.
 * @property rdfPredicate The predicate for RDF triple manipulation operations.
 * @property rdfObject The object for RDF triple manipulation operations.
 */
open class RdfServiceRdf4jV1 (modelGraph:GraphConfig, projectGraph:GraphConfig) : RdfService(modelGraph, projectGraph) {
    // Add export to real DB using DB interface.
    var rdf4JRepo: Repository? = null // SailRepository(MemoryStore())
    var rdf4JCon: RepositoryConnection? =  null //rdf4JRepo.connection

    /** Only really needed for init **/
    fun getDBConnection():RepositoryConnection  {
        if (this.rdf4JCon == null) {
            rdf4JRepo = SailRepository(MemoryStore())
            rdf4JCon = rdf4JRepo?.connection
            checkNotNull(rdf4JCon)
            this.serviceInit()
        }
        return this.rdf4JCon!!
    }


    /**
     * Converts the provided object and type specifications into a JSON representation.
     *
     * @param predicateSpec The `PredicateSpec` instance that contains information about the schema type and value type
     *                 for the object being converted.
     * @param objArg The object to be converted into a JSON element. This can be an instance of various data types,
     *               such as primitive types, strings, or objects implementing specific interfaces.
     * @param objType An optional string representing the object's type, used for constructing JSON-LD terms
     *                with `@type` annotations.
     * @return A `JsonElement` that represents the input object within the context of the given type and schema
     *         specifications. This may include JSON primitives, objects, or arrays, depending on the input data.
     */
    override fun getJsonTerm(predicateSpec: PredicateSpec, obj: Any, objType: String?): JsonElement {
        var term: JsonElement? = null

        if (obj is JsonPrimitive) return obj


        val castType = predicateSpec.valueType
        val schemaKind = predicateSpec.schemaType
        val that = this

        if (schemaKind == "primitive" || schemaKind == "array" || schemaKind == "object") {

            when (castType) {
                "string", "uuid" -> term = JsonPrimitive(obj.toString())
                "integer" -> term = JsonPrimitive(if (obj is Int) obj else obj.toString().toInt())
                "boolean" -> term = JsonPrimitive(obj.toString() == "true")
                "number" -> term = JsonPrimitive(if (obj is Number) obj else obj.toString().toFloatOrNull())
                "date-time" -> term = JsonPrimitive(obj.toString())
                "date" -> term = JsonPrimitive(obj.toString())
                "reference" -> term = buildJsonObject {
                    put("@id", that.simpleTerm(obj.toString()))
                    //if (objType!=null)
                    //    put("@type", JsonPrimitive(objType))//(obj as ObjectCache).uriId) )
                }

                else -> {
                    term = JsonPrimitive(obj.toString())
                    logApiError("Warning: Qualified type not found: $castType for ${obj.toString()}")
                }
            }

        }

        if (term == null) {
            logApiError("Type not found: $castType for ${obj.toString()}")
            term = JsonPrimitive("ERROR, no binding for: <${obj.toString()}> as a $castType")
        }

        return term
    }

    //
    // Isolated rdf4J dependencies
    //


    /**
     * Resolves and returns an RDF `IRI` (Internationalized Resource Identifier) based on the provided object
     * and the graph configuration. The method supports various data types for the input object and handles
     * scenarios like extracting prefixes, handling cached objects, resolving JSON primitives, and returning
     * the input if it's already an IRI.
     *
     * @param obj The object to be converted into an RDF `IRI`. This parameter can be a `String`, `ObjectCache`, `JsonPrimitive`,
     *            or an existing `IRI`. If none of these types match, the object is converted to its string representation.
     * @param gc The graph configuration (`GraphConfig`) that provides context for the operation. This includes
     *           details like the base URI and graph prefixes used to resolve the `IRI`.
     * @return An RDF `IRI` that represents the provided object within the context of the graph configuration.
     */
    override fun getGraphResourceIRI(obj:String?, gc:GraphConfig): IRI? {

        // Calling iri("rdf:type") did not work, may be a rdf4j bug?
        var result:IRI? = null
        val rdf4jConnect = this.getDBConnection() // Required for init
        if (obj != null) {

            //return this.rdf4JCon.valueFactory.createIRI(obj)
            var delim = obj.indexOf(':')
            if (delim >= 0) {
                // To make rdf values.iri work, had to pull apart prefix and term
                val basePrefix: String = obj.substring(0, delim)
                val baseGC = this.graphsByPrefix.get(basePrefix)
                if (baseGC != null)
                    result = rdf4jConnect.valueFactory.createIRI(baseGC.baseURI, obj.substring(delim + 1))
                else {
                    logApiError("Prefix not found: $obj")
                    rdf4jConnect.valueFactory.createIRI((obj.substring(0, delim)), obj.substring(delim + 1))
                }
            } else {
                delim = obj.lastIndexOf('#')
                if (delim < 0) delim = obj.lastIndexOf('/')
                if (delim < 0)
                    result = rdf4jConnect.valueFactory.createIRI(gc.baseURI, obj)
                else
                    result = rdf4jConnect.valueFactory.createIRI((obj.substring(0, delim)), obj.substring(delim + 1))
            }
        }
        return result

    }
    /**
     * The IRI can be DB specific or a string, this returns it only as a string for use in queries.
     * The DSB specific will need to override this
     */
    override fun getGraphResourceString(obj:Any?, gc: GraphConfig):String? {
        if (obj is IRI) {
            return "<${obj.stringValue()}>"
        } else if (obj is Value) return obj.stringValue()
        else if (obj is String)
            return this.qualified(obj, gc)
        else return obj.toString()
    }

    override fun rdfIRI(base:String, term:String?) :IRI {
        val prefixed = this.graphsByPrefix.get(base)
        val iri = if (prefixed==null) base else prefixed.baseURI
        return this.getDBConnection().valueFactory.createIRI(iri, term)
    }
    override fun rdfIRI(iriStr:String?) :Any {
        var iri = iriStr
        val prefix = iriStr?.substringBefore(':')
        if (prefix!=null) {
            val prefixed = this.graphsByPrefix.get(prefix)
            if (prefixed!=null) iri = prefixed.baseURI + iriStr.substringAfter(':')
        } // Else unknown prefix (including http) is passed thru.
        return this.getDBConnection().valueFactory.createIRI(iri)
    }
    /**
     * Generates an RDF term/UIR/Literal, which may be DB specific, based on the provided object, its intended type, and the graph configuration.
     * and the graph configuration.Handles various data types such as strings, integers, booleans, dates, and references by mapping them
     * references by mapping them to corresponding RDF literals or resources.
     *
     * @param gc The graph configuration, represented as a `GraphConfig` instance. Provides context
     *           for the RDF graph, including base URI and metamodel specifications.
     * @param obj The object to be converted into an RDF term. Can be of types such as string, integer,
     *            boolean, date, or other supported types. If the object is a `JsonPrimitive`, or CachedObject its
     *            content will be extracted.
     * @param castType A string representing the desired type of the resultant RDF term. Supported types
     *                 include "string", "uuid", "integer", "boolean", "number", "date-time", "date", and "reference".
     *                 If the type is unrecognized, the string representation of the object will be used.
     * @return The generated RDF `Value`, which can be a literal or an IRI, depending on the input
     *         object and castType.
     */
    override fun getGraphTerm( predicateSpec: PredicateSpec, obj:Any?, gc:GraphConfig):Value? {
        var term:Value? = null
        var termStr:String? = this.getValueAsString(predicateSpec, obj)
        val rdfConnection = this.getDBConnection()
        print("<$obj><$termStr>")
        if (termStr!=null && termStr.startsWith('\"') && termStr.endsWith('\"')) {
            termStr = termStr.substring(1, termStr.length - 1)
            print("Stripped: $termStr")
        }
        if (termStr!=null)
            when (predicateSpec.valueType) {
                "string","uuid","String"    -> term = rdfConnection.valueFactory.createLiteral(termStr)
                "integer"   -> term = rdfConnection.valueFactory.createLiteral(termStr.toInt())
                "boolean" -> term = rdfConnection.valueFactory.createLiteral(termStr=="true")
                "number" -> term = rdfConnection.valueFactory.createLiteral(termStr.toFloat())
                "date-time" -> term = rdfConnection.valueFactory.createLiteral(LocalDate.parse(termStr))
                "date" -> term = rdfConnection.valueFactory.createLiteral(LocalDate.parse(termStr))//objLocal as Date)
                "reference" -> term = this.getGraphResourceIRI(termStr, if (predicateSpec.subjectGraph!=null) this.getRdfGraph(predicateSpec.subjectGraph!!)!! else gc)//predicateSpec.boundGc()!!)
                else -> {
                    term = literal(termStr)
                    logApiError("Qualified type not found: ${predicateSpec.valueType} for ${termStr}")
                }
            }
        return term
    }

    fun serviceInit() {
        //for ( aNamespace in this.rdf4JCon.getNamespaces()) println("Namespace pre init: ${aNamespace.prefix} ${aNamespace.name}")
        for (aGraph in this.rdfGraphs.values) {

                this.getDBConnection().setNamespace(aGraph.prefix, aGraph.baseURI)
        }
        for ( aNamespace in getDBConnection().getNamespaces()) println("Namespace: ${aNamespace.prefix} ${aNamespace.name}")
    }

    fun serviceClose() {
        getDBConnection().close()
    }
    /**
     * Get the DB specific "helper" mixin for a GraphConfig.
     */
    fun getGraphHelper(gc:GraphConfig):Rdf4jV1Helper{
        if (gc.graphImpl == null) {gc.graphImpl = Rdf4jV1Helper(gc, this)}
        return gc.graphImpl as Rdf4jV1Helper
    }


    override fun rdfAdd(s:Any?, p:Any?, o:Any?, gc:GraphConfig) {
        if (s!=null && p!=null && o!=null)
            this.getGraphHelper(gc).model.add(s as IRI, p as IRI, o as Value, this.getGraphHelper(gc).context)
        else
            logApiError("ERROR: rdfAdd: null parameter $s $p $o")
    }
    override fun rdfRemove(s:Any?, p:Any?, o:Any?, gc:GraphConfig) {
        if (s != null && p != null && o != null)
            this.getGraphHelper(gc).model.remove(s as IRI, p as IRI, o as Value, this.getGraphHelper(gc).context)
        else
            logApiError("ERROR: rdfAdd: null parameter $s $p $o")
    }

    private var header:String? = null
    override fun sparqlUpdate(query:String, gc:GraphConfig) {
        if (header==null) {
            header = ""
            for (gc in this.rdfGraphs.values) {
                //if (!(gc.baseURI.endsWith(":"))) // urn: is not a prefix
                header += "PREFIX ${gc.prefix}: <${gc.baseURI}>\n"
            }
        }
        val update= header+query
        if (this.exportStream !=null) {// ToDo log service
            this.exportStream.println("##SparQL Update ${LocalDateTime.now()}")
            this.exportStream.println(update)
            this.exportStream.flush()
        }
        try {
            this.rdf4JCon?.prepareUpdate(update)?.execute()
            if (this.exportStream != null) {// ToDo log service
                this.exportStream.println("##SparQL Update complete ${LocalDateTime.now()}")
                this.exportStream.flush()
            }
        } catch (e: Exception) {
            this.exportStream.println("##SparQL Update Exception $e")
            println("##SparQL Update Exception query \n$update")
            logApiError("SparQL Update Exception $e")

        }
    }
    /**
     * Override of SparqlQuery returns the DB specific QueryHelper initilized with the Query.
     */
    override fun sparqlQuery(theQuery:String):QueryResultInterface {
        val queryResult = this.rdf4JCon?.prepareTupleQuery(theQuery)?.evaluate()
        return if (queryResult==null) SparQLResultJson(JsonObject(mapOf())) else SparQLQueryResult4J(queryResult)
    }
    /**
     * A "mixin class for GraphConfig that propvides DB specific properties and operations for that graph.
     * In the case of rdf$J, provides the "model" and "context" objects and sets namespaces.
     */
    class Rdf4jV1Helper(gc:GraphConfig, rdfService:RdfServiceRdf4jV1) {
        var model = rdfService.getDBConnection() // the model object on which to perform queries and such.
        val context:IRI = model.valueFactory.createIRI(gc.baseURI, "") // The named graph

    }

}
