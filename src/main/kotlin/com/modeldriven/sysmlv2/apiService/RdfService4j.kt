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

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import org.eclipse.rdf4j.model.IRI
import org.eclipse.rdf4j.model.Literal
import org.eclipse.rdf4j.query.BindingSet
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.sail.memory.MemoryStore;
import java.time.LocalDateTime

open public class RdfServiceRdf4j(modelGraph:GraphConfig, projectGraph:GraphConfig ) : RdfService(modelGraph, projectGraph) {
    // Add export to real DB using DB interface.
    val rdf4JRepo: Repository = SailRepository(MemoryStore())
    val rdf4JCon: RepositoryConnection = rdf4JRepo.connection

    /**
     * Utilize end transaction to perform a SparQL update to Rdf4J based on the contents of the updater object.
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
                    this.rdf4JCon.prepareUpdate(update).execute()
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
    fun getGraphHelper(gc:GraphConfig):Rdf4jHelper{
        if (gc.graphImpl == null) {gc.graphImpl = Rdf4jHelper(gc, this)}
        return gc.graphImpl as Rdf4jHelper
    }

    /**
     * Override of SparqlQuery returns the DB specific QueryHelper initilized with the Query.
     */
    override fun sparqlQuery(theQuery:String):RdfQueryHelperStub {return RdfQueryHelper(this,theQuery)}
    /**
     * A "mixin class for GraphConfig that propvides DB specific properties and operations for that graph.
     * In the case of rdf$J, provides the "model" and "context" objects and sets namespaces.
     */
    class Rdf4jHelper(gc:GraphConfig, rdfService:RdfServiceRdf4j) {
        var model = rdfService.rdf4JCon // the model object on which to perform queries and such.
        val context:IRI = model.valueFactory.createIRI(gc.baseURI, "") // The named graph

    }

    /**
     * The `RdfQueryHelper` class is a concrete implementation for executing and managing RDF queries using the
     * Rdf4j framework. It extends the `RdfQueryHelperStub` base class, providing actual functionality to execute
     * SPARQL queries, traverse results, and retrieve query bindings in various formats.
     *
     * This class encapsulates the behavior related to processing query results, ensuring proper handling and
     * closing of resources.
     *
     * @constructor Creates an instance of `RdfQueryHelper` with the specified RDF service and query string.
     * @param rdfService The `RdfServiceRdf4j` instance used for executing the query.
     * @param theQuery The SPARQL query string to execute through the RDF service.
     */
    class RdfQueryHelper(rdfService: RdfServiceRdf4j, theQuery:String) : RdfQueryHelperStub(rdfService, theQuery) {
        val queryResult = rdfService.rdf4JCon.prepareTupleQuery(theQuery).evaluate()
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
        override fun getValueString(key:String):String? {return currentBindingSet?.getValue(key)?.stringValue()}
        override fun isLiteralValue(key:String):Boolean = currentBindingSet?.getValue(key) is Literal
        override fun nextBindingJson(): JsonObject {
            val elements = HashMap<String, JsonPrimitive>() // Can we make this ordered?
            if (this.next()) {
                if (currentBindingSet != null) {
                    for (binding in currentBindingSet!!) {
                        elements[binding.name] = JsonPrimitive(binding.value.stringValue())
                    }
                }
            }
            return JsonObject(elements)
        }
        override fun close() {queryResult.close()}
    }
}
