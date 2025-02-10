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

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

/**
 * Represents a model resource that interacts with RDF data through an RDF service
 * and a graph configuration. This class facilitates the management and synchronization
 * of resource data with an RDF-based backend.
 *
 * @property rdfService The RDF service allowing interactions with the RDF storage and methods for data manipulation.
 * @property gc The configuration object for the RDF graph, providing contextual details about the data model.
 * @property identifier The unique identifier for the resource. It is initially set by the explicitIdentifier parameter
 * or updated based on the source JSON data.
 * @property typeName The type name of the resource, initialized with the defaultType parameter or updated from the source JSON data.
 *
 * @constructor Creates a new instance of ModelResource with the specified RDF service, graph configuration, and optional parameters.
 *
 * @param rdfService An instance of RdfService used for performing operations on RDF data.
 * @param gc The configuration object that provides metadata and configuration for the graph operations.
 * @param explicitIdentifier An optional parameter for setting an explicit identifier for the resource. Can be null.
 * @param defaultType The default type name of the resource, which can be overridden by the source JSON data. Defaults to "element".
 * @param isNew True will indicate a new object being created, no attempt is made to delete or query any existing state. However, false is really "unkown" and may be new.
 */
open class ModelResource(val rdfService:RdfService, val gc:GraphConfig, explicitIdentifier:String?=null, defaultType:String="element", var isNew:Boolean=false ) {
    var identifier: String? = explicitIdentifier // Unique identifier of the object - may not be a URI
    var typeName: String? = defaultType // Name of the type of the object

    /**
     * Updates the current object's data using the provided JSON object.
     *
     * This method integrates the source JSON object into the RDF DB,
     * handling additions, deletions, and modifications of properties. It validates
     * and processes RDF specifications for properties and updates them accordingly.
     * It also checks for and logs errors if a mismatch in identifiers or other issues
     * are encountered during the update operation.
     *
     * @param sourceJson The new JSON object to be used for updating the resource.
     *                   Must contain valid `@id` and optionally an `@type`.
     *
     */
    fun updateObjectJson(sourceJson: JsonObject? = null) {
        requireNotNull(sourceJson)

        var existingArray: JsonArray? = null
        var existingJson: JsonObject? = null

        val newID:String? = sourceJson["@id"]?.jsonPrimitive?.content
        if (newID != null) {
            if (this.identifier!=null && this.identifier!=newID) {
                logApiError("Error: Trying to update a resource with different ID: ${this.identifier} to ${newID}")
                return
            } else {
                this.identifier = newID
            }

        } else if (this.identifier==null) {
            logApiError("Error: No identifier for $sourceJson")
        }

        if (sourceJson["@type"] != null) this.typeName = sourceJson["@type"]?.jsonPrimitive?.content

        if (!this.isNew) {
            // Get or delete possible existing json for edit
            existingArray = if (!(this.gc.deleteToEdit)) {
                // Get existing object to compare against input for property level edit
                rdfService.getJsonById(rdfService.gcModel!!, this.identifier) // Get existing object
            } else {
                // Delete all contents of existing object so all properties will be re-created.
                rdfService.deleteObjectContents(this.gc, this.identifier)
                null
            }
            existingJson =
                if (existingArray != null && existingArray.isNotEmpty()) (existingArray[0] as JsonObject) else null
        }

        // Handle deleted properties
        if (existingJson!=null) for (key in existingJson.keys) {
            if (key=="@id") continue
            if (sourceJson[key]==null) {
                var predicateSpec = if (key=="@type")

                    rdfService.rdfTypeSpec else
                    this.gc.metamodelSpec?.getPredicateSpec(this.typeName!!, key)
                if (predicateSpec==null) {
                    //logApiError("Warning: Predicate <$key> not known in <$typeName> - defaults used")
                    predicateSpec = rdfService.makeDefaultPredicateSpec(this.typeName, key) // ToDo make value sensitive
                }
                rdfService.deleteValue(this.gc, this.identifier!!, predicateSpec, existingJson[key])
            }
        }
        // Handle properties of new value
        for (key in sourceJson.keys) {
            if (key=="@id") continue
            val jsonProperty = sourceJson[key]!!
            var predicateSpec = if (key=="@type")
                rdfService.rdfTypeSpec else
                this.gc.metamodelSpec?.getPredicateSpec(this.typeName!!, key)

            if (predicateSpec==null) {
                //logApiError("Warning: Predicate <$key> not known in <$this.typeName> - defaults used")
                predicateSpec = rdfService.makeDefaultPredicateSpec(this.typeName, key) // ToDo make value sensitive
            }
            if (existingJson != null && existingJson[key] != null && existingJson[key] != jsonProperty) {
                // We have an edit
                rdfService.deleteValue(this.gc, this.identifier!!, predicateSpec, existingJson[key])
            }
            // Add value
            rdfService.addValue(this.gc, this.identifier!!, predicateSpec, jsonProperty)
        }
        this.isNew = false
        rdfService.endUpdate(this.identifier!!) // Hook to tell DB we are done with this object
    }
}