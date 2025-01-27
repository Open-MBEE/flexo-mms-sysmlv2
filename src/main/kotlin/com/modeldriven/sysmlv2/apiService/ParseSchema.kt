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
 * Create "ContainerSpec" (essentially an abbreviated class ) and PredicateSpec (an abriviated property) as part of
 * a "SchemaSpec" (Essentialy a metamoel) from SysML open API
 * Note: Tried using Swagger,  had problems. Would like to use XMI but do not have XMI for the API model.
 * So, this is a cheap parser for the openapi, just to get what I need. It is not general
 * but works with the SysML spec. Consider replacing with some open source.
 */

import kotlinx.serialization.json.*

/**
 * Represents a type specification that includes a name, schema type, and value type.
 * This class is used to define properties related to a specific type and can determine
 * if the type has an ordered structure based on its schema type.
 *
 * @property predicate The name of the type specification.
 * @property schemaType The schema type associated with this type (e.g., "array").
 * Can be null if no schema type is defined.
 * @property valueType The value type associated with the type specification.
 * Can be null if no value type is defined.
 * @property graphKey Key of graph holding these predicates.
 * @property rdfService RdfService supplying utility functions
 */
public class PredicateSpec(val predicate:String, val schemaType:String, var valueType:String?, val graphKey:String, val rdfService:RdfService) {
    val isOrdered = schemaType=="array" // Assume ordered for array
    var gc:GraphConfig? = rdfService.getRdfGraph(graphKey)
    private var qualifiedStr:String? = null
    private var rdfResource:Any? = null
    var subjectGraph:String? = null

    fun getAnnotationPredicate():String? {
        if (schemaType=="array")
            return "<${gc?.baseURI}annotation:json:${this.predicate}>"
        else
            return null
    }

    fun getQualified():String? {
        if (qualifiedStr==null) {
            if (predicate.contains(':'))
                qualifiedStr = predicate
            else
                qualifiedStr = if (this.boundGc()!=null) rdfService.qualified(predicate, this.boundGc()) else null
        }
        return qualifiedStr
    }
    fun getRdfIRI():Any? {
        if (this.rdfResource==null)
            this.rdfResource =  if (this.boundGc()!=null) rdfService.getGraphResourceIRI(this.getQualified(), this.boundGc()!!) else null
        return this.rdfResource
    }
    fun getPrefix() = this.boundGc()?.prefix

    /**
     * Provides for late binding of a graph to an rdfService
     */
    fun boundGc():GraphConfig? {
        if (gc==null) {
            gc = rdfService.getRdfGraph(this.graphKey)
        }
        return gc
    }
}

//val defaultStringSpec = PredicateSpec("UNDEFINED", "primitive", "string", "SysML") // moved to rdfservicew
//val rdfTypeSpec = PredicateSpec("type", "primitive", "reference", "rdf")

/**
 * Represents the specification of a container including its schema type, name, and associated predicates.
 *
 * @constructor Initializes a `ContainerSpec` with the given parameters. If `schemaType` is null, it defaults to "object".
 * @property schemaType The type of schema that represents the container. Defaults to "object" if null.
 * @property name The name of the container.
 * @property schemaSpec The associated schema specification.
 * @property rdfService The service used for RDF operations.
 * @property propertyTypes A map that holds the predicates associated with the container.
 */
class ContainerSpec(var schemaType:String?, val name:String, val schemaSpec: SchemaSpec, val rdfService:RdfService) {
    val propertyTypes:HashMap<String, PredicateSpec> = HashMap<String, PredicateSpec>()
    init {
        if (schemaType==null) schemaType = "object"
    }

    fun addPredicateSpec(name:String,schemaType:String,valueType:String) {
        propertyTypes[name] = PredicateSpec(name,schemaType, valueType, schemaSpec.graphKey, rdfService)
    }
    fun getPredicateSpec(name:String):PredicateSpec? = propertyTypes.get(name)
}

/**
 * Represents a specification for a schema (set of containers), providing functionality to parse and
 * manage JSON schema information based on an input OPEN-API file. Note that this is not general code
 * for APON-API but based on the SysML spec.
 *
 * @property graphKey The base prefix that will be utilized within the schema.
 * @property apiFileName The name of the API file containing the schema definitions.
 * @property rdfService RdfService providing utility functions
 */
public class SchemaSpec(var graphKey:String, val apiFileName:String?, val rdfService: RdfService) {
    val containers : HashMap<String, ContainerSpec> = HashMap<String, ContainerSpec>()
    val toMerge = ArrayList<Merge>()

    class Merge(val source:String, val container:ContainerSpec, val property:String?){}


    init {
        var apiString:String? = null

        if (!apiFileName.isNullOrBlank()) {
            val apiFile = this::class.java.getResource(apiFileName)//= File("openapi.json")
            apiString = apiFile?.readText()
            if (apiFile == null) {
                logApiError("ERROR: API file not found: $apiFileName")
            }
        }

        if (apiString != null) {
            val apiObjects = Json.parseToJsonElement(apiString) as JsonObject
            apiString = "" // Free up mem
            val components = apiObjects.get("components") as JsonObject
            val schemas = components.get("schemas") as JsonObject //Do these get garbage collected?
            for (schemaKey in schemas.keys) {
                //println(schemaKey)
                val schema = schemas[schemaKey] as JsonObject
                val schemaType = schema.get("type")?.jsonPrimitive?.contentOrNull

                val newContainer = ContainerSpec(schemaType, schemaKey, this, rdfService)
                containers[schemaKey] = newContainer

                val properties = schema.get("properties") as JsonObject?
                val oneOf = if (schema.contains("anyOf")) schema.get("anyOf") as JsonArray?
                else schema.get("oneOf") as JsonArray?


                if (properties != null) {
                    for (propertyKey in properties.keys) {
                        val property = properties[propertyKey] as JsonObject
                        val predicateSpec = this.makePredicateSpec(propertyKey, property, newContainer)
                        newContainer.propertyTypes[propertyKey] = predicateSpec
                        if (propertyKey.startsWith('@'))
                            newContainer.propertyTypes[propertyKey.substringAfter('@')] = predicateSpec
                        //println("Property: ${newContainer.name}: ${predicateSpec.name} ${predicateSpec.schemaType}, ${predicateSpec.valueType}")
                    }
                }
                if (oneOf is JsonArray) {
                    // need to make this recursive
                    for (one in oneOf) {
                        if (one is JsonObject) {
                            val oneRef = one.get("\$ref")
                            if (oneRef is JsonPrimitive) {
                                this.toMerge.add(
                                    Merge(
                                        oneRef.content.substringAfterLast("/"),
                                        newContainer,null
                                    )
                                ) // Merge ref schema to this one
                            } else {
                                val subspec: PredicateSpec = this.makePredicateSpec(schemaKey, one, newContainer)
                                newContainer.propertyTypes[schemaKey] = subspec
                                if (schemaKey.startsWith('@'))
                                    newContainer.propertyTypes[schemaKey.substringAfter('@')] = subspec
                            }

                            val properties2 = one["properties"] as JsonObject?
                            if (properties2 != null) {
                                for (propertyKey in properties2.keys) {
                                    val property = properties2[propertyKey] as JsonObject
                                    val predicateSpec = this.makePredicateSpec(propertyKey, property, newContainer)
                                    newContainer.propertyTypes[propertyKey] = predicateSpec
                                    if (propertyKey.startsWith('@'))
                                        newContainer.propertyTypes[propertyKey.substringAfter('@')] = predicateSpec
                                    //println("Property: ${newContainer.name}: ${predicateSpec.name} ${predicateSpec.schemaType}, ${predicateSpec.valueType}")
                                }
                            }
                        }
                    }

                }
                else {
                    val predicateSpec = this.makePredicateSpec(schemaKey, schema, newContainer)
                    newContainer.propertyTypes[schemaKey] = predicateSpec
                    if (schemaKey.startsWith('@'))
                        newContainer.propertyTypes[schemaKey.substringAfter('@')] = predicateSpec
                    //println("Not property ${newContainer.name}: ${predicateSpec.name} ${predicateSpec.schemaType}, ${predicateSpec.valueType}")
                }

            }
        }
        // Merge references for oneOn / anyOf
        for (merge in toMerge) {
            val source = this.containers.get(merge.source)
            val dest = merge.container
            if (source is ContainerSpec) {
                if (source.schemaType == "object" ) {
                    // Merge object properties
                    for (propertyName in source.propertyTypes.keys) {
                        val property = source.propertyTypes[propertyName]
                        dest.propertyTypes[propertyName] = property!!
                    }
                } else {
                    // Must be primitive type of property, handles enumerated types
                    val modProperty = dest.propertyTypes.get(merge.property!!)
                    if (modProperty!=null && modProperty.schemaType!=null) modProperty.valueType = source.schemaType
                }
            }else{
                logApiError("ERROR: No \$ref schema found for ${merge.source}")
            }


        }

    }
    fun makePredicateSpec(name:String, property:JsonObject, container:ContainerSpec):PredicateSpec {
        var theJsonType:String? = null
        var theValueType:String? = null
        var canBeNull = false
        val propertyTypeElement = property.get("type")
        val propertyType = if (propertyTypeElement is JsonPrimitive) propertyTypeElement.contentOrNull else null

        if (propertyType != null) {
            if (propertyType=="array"){
                theJsonType = "array"
                val subspec:PredicateSpec = this.makePredicateSpec("NOT_USED", property.get("items") as JsonObject, container)
                theValueType = subspec.valueType
            } else if (propertyType=="object"){
                theJsonType = "object"
                val dollarRefElement = property.get("\$ref") as JsonPrimitive?
                if (dollarRefElement != null) {
                    val refStr = dollarRefElement.content
                    this.toMerge.add(Merge(refStr.substringAfterLast("/"), container, name))
                    //strip the end element
                    theValueType = "reference"
                } else
                    theValueType = propertyType
            } else {
                theJsonType = "primitive"
                if (propertyType=="string"){
                    val formatElement = property.get("format") as JsonPrimitive?
                    if (formatElement != null) {
                        if(formatElement.content=="uri") {
                            theValueType = "reference"
                        } else if (formatElement.content in listOf("date-time", "date", "uuid")) theValueType = formatElement.content
                        else
                            theValueType = propertyType
                    } else
                        theValueType = propertyType
                } else
                    theValueType = propertyType
            }
        } else {
            val dollarRefElement = property.get("\$ref") as JsonPrimitive?
            if (dollarRefElement != null) {
                theJsonType = "object"
                val refStr = dollarRefElement.content
                //strip the end element
                theValueType = "reference" // NO USING ACTUAL TYPE refStr.substringAfterLast("/")
                this.toMerge.add(Merge(refStr.substringAfterLast("/"), container, name))

            } else {
                var oneOf = property.get("oneOf") as JsonArray?
                if (oneOf==null) oneOf = property.get("anyOf") as JsonArray?
                if (oneOf != null) {
                    for (one in oneOf) {
                        val oneRef = if (one is JsonObject) one.get("\$ref") else null
                        if (oneRef is JsonPrimitive) {
                            this.toMerge.add(Merge(oneRef.content.substringAfterLast("/"), container, name))
                        } else {
                            val subspec: PredicateSpec = this.makePredicateSpec("NOT_USED", (one as JsonObject), container)
                            if ((subspec.valueType == "null") || (subspec.schemaType == null)) {
                                canBeNull = true
                            } else {
                                theJsonType = subspec.schemaType
                                theValueType = subspec.valueType
                            }
                        }
                    }
                } else {
                    logApiError("ERROR: Do not understand property $name : $property")
                }
            }
        }
       /**if (!(theValueType  in setOf("date-time", "date", "uuid", "reference", "integer", "string", "number","boolean", "null", null))) {
            //logApiError(("Invalid JSON value type $theValueType"))
            theValueType = "reference"
            //assert((!(theValueType  in setOf("date-time", "date", "uuid", "reference", "integer", "string", "number","boolean", "null"))))
        }**/
        if (theJsonType==null) {
            //logApiError(("Invalid null schemaType $name : $property"))
            theJsonType = "primitive"
            theValueType = "reference"
        }
        return PredicateSpec(name, theJsonType, theValueType, this.graphKey, this.rdfService )

    }
    fun getPredicateSpec(typeName:String, predicateName:String):PredicateSpec? {
        var spec:PredicateSpec? = null
        val container = this.containers.get(typeName)
        if (container!=null) {
            val spec2 = container.getPredicateSpec(predicateName)
            if (spec2 != null) spec = spec2
            //else
            //    logApiError("Warning: Predicate \"$predicateName\" Not found in schema \"$typeName\" ")
            //} else
            //   logApiError("ERROR: Schema Not found  $typeName")
        }
        return spec
    }
}

private var schemaSpecs:HashMap<String,SchemaSpec> = HashMap()

/**
 * Factory method for creating or retrieving a SchemaSpec instance based on the provided graphKey.
 * If a SchemaSpec for the specified graphKey already exists in the schemaSpecs cache, it is returned.
 * Otherwise, a new SchemaSpec is created and returned based on the openapi file for the graph..
 *
 * @param graphKey The key identifying the schema graph.
 * @param rdfService A service providing RDF data processing capabilities.
 * @return An instance of SchemaSpec associated with the provided graphKey.
 */
fun schemaSpecFactory(graphKey:String, rdfService: RdfService):SchemaSpec {
    if (graphKey in schemaSpecs) return schemaSpecs[graphKey]!!
    val apiFileName:String? =
    when (graphKey) {
        "SysML" -> "/openapi.json"
        "API" -> "/openapi.json" // These seems to be a problem with "/openapi-sans-schemas.json")
        "UNKOWN" -> "/UNKOWNapi.json"
        else -> null // Class names not read from JSON
    }
    val spec =  SchemaSpec(graphKey, apiFileName, rdfService)

    return spec
}
