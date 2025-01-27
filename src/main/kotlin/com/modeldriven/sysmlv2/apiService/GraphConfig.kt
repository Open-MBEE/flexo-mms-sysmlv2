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
 * GraphConfig Represents the configuration for a graph, including base URI, prefix, and optional schema specifications.
 * This class can be extended and linked to specific graph implementations using the `graphImpl` member.
 *
 * Note on graph "key": Within the RDF service graphs are not arbitrary, they play specific roles.
 * Most notable is "Model" and "Project", which are the only graphs modified. The key is used to find the
 * appropriate graph for a purpose, this allows "prefix" to be variable, however prefix defaults to key.
 *
 * The key should be one of:
 *   - "Model": the graph for the current graph being queried or modified
 *   - "Project": graph holding all the project related metadata and change data about the model in various versions
 *   - "API": The API model
 *   ' "rdf": W3C RDF
 *   - "RDFS": W3C RDF Schema
 *   - "XSD": W3C XML data types
 *   - "OWL": W3c OWL
 *   - "Prior": Prior graph being used for merge or diff (not yet implemented)
 *
 * @property prefix The prefix used to identify the graph.
 * @property baseURI The base URI of the graph.
 * @property keyArg A unique key to identify the graph within the RdfService, which defaults to `prefix` if not provided.
 * @property graphName name of a named graph, may be the same as baseURI, null means do not insert a named graph
 * @property rdfService RdfService bound to implementing this graph, it may be late bound
 * @property updater Updater object used to update this graph
 */
public open class GraphConfig (val prefix:String, val baseURI:String, keyArg:String?=null, var graphName:String?=null,
                               var rdfService: RdfService?=null, updaterArg:RdfUpdater?=null) {
    // Subclass this with links to real graph using graphImpl
    var updater:RdfUpdater = updaterArg ?: RdfUpdater(this,rdfService)
    var key:String = "ERROR" // Key of graph, Should be set by init
    var metamodelSpec:SchemaSpec? = null // Metamodel OPEN-API based) that defines the PredicateSpecs for this graph
    var deleteToEdit:Boolean = true // Do not query for existing state, pre-delete only
    open var graphImpl:Any? = null // place for RDF library specifics


    init {this.defaultedKey(keyArg,rdfService)}

    fun defaultedKey(keyArg:String?, rdfService: RdfService?=null) {
        this.key = keyArg ?: this.prefix
        this.updater = RdfUpdater(this, rdfService)
        if (this.metamodelSpec==null && rdfService!=null) {

            // Define default metamodel for graph types, binds context for class names
            // Since there is no context in the API spec
            var metamodelGraph = when(this.key) {
                "Model" -> "SysML"
                "Project" -> "API"
                        else -> null
            }
            if (metamodelGraph!=null)
                this.metamodelSpec = schemaSpecFactory(metamodelGraph, rdfService )
        }
    }
    fun getCombinedUpdate():String {
        return this.updater.getCombinedUpdate()
    }

}