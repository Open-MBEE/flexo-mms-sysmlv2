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

package org.openmbee.flexo.sysmlv2.apiService

import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.util.pipeline.PipelineContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import org.apache.jena.graph.Node
import org.apache.jena.graph.NodeFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.vocabulary.RDF
import org.openmbee.flexo.sysmlv2.DEFAULT_PREFIX_MAPPING
import org.openmbee.flexo.sysmlv2.SYSMLV2
import org.openmbee.flexo.sysmlv2.apis.InvalidSysmlSerializationError
import org.openmbee.flexo.sysmlv2.apis.toRdfLiteralNode
import org.openmbee.flexo.sysmlv2.flexoRequestPut
import org.openmbee.flexo.sysmlv2.forward
import org.openmbee.flexo.sysmlv2.models.Commit
import org.openmbee.flexo.sysmlv2.models.CommitRequest
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.reindent
import org.openmbee.flexo.sysmlv2.stringify
import java.time.OffsetDateTime
import java.util.UUID
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.forEach
import org.openmbee.flexo.sysmlv2.flexoRequestPut
import org.openmbee.flexo.sysmlv2.flexoRequest

class ApiServiceError(message: String): Error(message)

/**
 * The `ApiServiceRoot` class provides a superclass for API services, operations for interacting with an RDF-based service.
 * The service allows manipulation of resources
 * within a model and a project. The service is intended as the implementation of SysML-API services.
 *
 * @constructor Creates an instance of `ApiCrudService` with the specified RDF service.
 * @param rdfService The RDF service instance used for interacting with models and projects.
 */
open class ApiServiceRoot(val context : PipelineContext<*, ApplicationCall>) {
}

/**
 * The `ApiCrudService` class provides CRUD operations for interacting with the back-end repositopry to imnplement the
 * service interfaces. It extends
 * the `ApiServiceRoot` and implements the `ApiCrudInterface`. The service allows manipulation of resources
 * within a model and a project. The service is intended as the implementation of SysML-API services.
 *
 * @constructor Creates an instance of `ApiCrudService` with the specified RDF service.
 * @param context Information needed by back-end to implement the api
 * @param projectID Project on which to operate
 */
class ApiCrudService(context : PipelineContext<*, ApplicationCall>, val projectId:String?):ApiServiceRoot(context) , ApiCrudInterface {

     override suspend fun changeService(changes:JsonArray, isNewPayload:Boolean) {
         val inserts = mutableListOf<String>()
         val deleteIncoming = mutableListOf<String>()
         val values = mutableListOf<String>()

         // each change (DataVersionRequest)
         for((index, change ) in changes.withIndex()) {
             val payload = change.jsonObject.get("payload")?.jsonObject
             val identity = change.jsonObject.get("identity")?.jsonObject
             //val (payload, atType, identity) = change

//            // assert @type
//            assert(atType == DataVersionRequest.AtType.DataVersion) { ".change[$index][\"@type\"] value of \"$atType\" is not supported" }

             // handle combinations of potential identity id and payload id in DataVersionRequest
             // 1. identityId is present and payloadId is present: if they match, good, if not, bad
             // 2. identityId is present and payloadId is not present and payload is not null
             //     - use identityId
             // 3. both identityId and payloadId are not present, but payload is not null
             //     - generate a new uuid
             // 4. identityId is not present and payloadId is present
             //     - use payloadId
             val identityId = identity?.getOrDefault("@id", null)?.jsonPrimitive?.content?.replace("#", "")
             var payloadId = payload?.getOrDefault("@id", null)?.jsonPrimitive?.content?.replace("#", "")
             if (identityId != null && payloadId != null && identityId != payloadId) {
                 //bad, log error?
                 continue
             }
             if (identityId == null && payloadId == null && payload != null) {
                 payloadId = UUID.randomUUID().toString() // generate an id
             }

             // subject node, target element
             val elementNode: Node = SYSMLV2.element(identityId ?: payloadId!!).asNode()

             // transform payload into property pairs
             mutableListOf<Pair<Property, Set<Node>>>().apply {
                 // add to values block
                 values.add(elementNode.stringify())

                 // payload is null, delete element
                 if(payload == null) {
                     // delete incoming
                     deleteIncoming.add(elementNode.stringify())

                     // done
                     return@apply
                 }

                 // encode each key/value
                 payload.forEach { (key, value) ->
                     // skip null values (although technically not allowed)
                     if(value == null) return@forEach

                     // depending on the key
                     when(key) {
                         // reserved @id
                         "@id" -> {
//                            elementNode = SYSMLV2.element(value.jsonPrimitive.content).asNode()
                         }

                         // reserved @type
                         "@type" -> add(RDF.type to setOf(SYSMLV2.type(value.jsonPrimitive.content).asNode()))

                         // everything else
                         else -> {
                             // depending on JSON type of value
                             when(value) {
                                 // boolean, number, string
                                 is JsonPrimitive -> {
                                     add(SYSMLV2.prop(key) to setOf(value.toRdfLiteralNode()))
                                 }
                                 // array
                                 is JsonArray -> {
                                     // store entire array as serialized JSON
                                     add(SYSMLV2.annotationJson(key) to setOf(NodeFactory.createLiteral(Json.encodeToString(value))))

                                     // non-empty list
                                     if (value.isNotEmpty()) {
                                         // and first element is an object
                                         if(value[0] is JsonObject) {
                                             // create additional triples to link the elements
                                             add(SYSMLV2.relation(key) to value.jsonArray.map {
                                                 SYSMLV2.element(it.jsonObject["@id"]!!.jsonPrimitive.content).asNode()
                                             }.toSet())
                                         }
                                         // and first element is a primitive
                                         else if(value[0] is JsonPrimitive) {
                                             // create additional triples to provide literals from primitives
                                             add(SYSMLV2.prop(key) to value.jsonArray.map {
                                                 it.jsonPrimitive.toRdfLiteralNode()
                                             }.toSet())
                                         }
                                         // unexpected
                                         else {
                                             throw InvalidSysmlSerializationError("Unexpected JSON element type at .change[$index].$key")
                                         }
                                     }
                                 }
                                 // object
                                 is JsonObject -> {
                                     val valueObj = value.jsonObject

                                     // @id reference
                                     if(valueObj.containsKey("@id")) {
                                         if(valueObj.keys.size > 1) {
                                             throw Error("Unexpected extra keys at .${key}")
                                         }

                                         //
                                         add(SYSMLV2.relation(key) to setOf(SYSMLV2.element(valueObj["@id"]!!.jsonPrimitive.content).asNode()))
                                     }
                                     else {
//                                        value.jsonObject.forEach { subkey, subvalue ->
//                                        }
                                         throw InvalidSysmlSerializationError("Unexpected JSON object at .change[$index].$key == ${Json.encodeToString(value)}")
                                     }
                                 }
                             }
                         }
                     }
                 }

                 // generate the RDF data for SPARQL INSERT clause
                 inserts.add("""
                    ${elementNode.stringify()} ${joinToString(" ;\n") { pair ->
                     pair.first.stringify()+" "+pair.second.joinToString(", ") { it.stringify() }
                 }.reindent(6)} .
                """.trimIndent())
             }
         }

         // build SPARQL UPDATE string
         var sparqlUpdateString = """
            ${DEFAULT_PREFIX_MAPPING.nsPrefixMap.filter { (id, iri) ->
             iri.startsWith(SYSMLV2.BASE)
         }.toList().joinToString("\n") { (id, iri) ->
             "prefix $id: <$iri>"
         }.reindent(3)}

            delete {
                ?element_n ?element_p ?element_o .

                ${if(deleteIncoming.isNotEmpty()) {
             """
                        ?incoming ?incoming_relation_p ?element_del ;
                            ?incoming_order_p ?incoming_order_o .
                    """.reindent(4)
         } else ""}
            }
            insert {
                ${inserts.joinToString("\n\n").reindent(4)}
            }
            where {
                optional {
                    ?element_n ?element_p ?element_o .
                }
                
                values ?element_n {
                    ${values.joinToString("\n").reindent(5)}
                }               
        """

         // only delete incoming if there are elements being deleted
         if(deleteIncoming.isNotEmpty()) {
             sparqlUpdateString += """
                optional {
                    ?incoming ?incoming_relation_p ?element_del ;
                        ?incoming_order_p ?incoming_order_o .
                    
                    filter(
                        str(?incoming_order_p) = concat(
                            "${SYSMLV2.ANNOTATION_JSON}",
                            strAfter(
                                str(?incoming_relation_p),
                                "${SYSMLV2.RELATION}"
                            )
                        )
                    )
                }

                values ?element_del {
                    ${deleteIncoming.joinToString("\n").reindent(5)}
                }
            """
         }

         // close the update string
         sparqlUpdateString += """
            }
        """

         // trim indent for better inspectability
         sparqlUpdateString = sparqlUpdateString.trimIndent()

         // to get around flexo update issue
         val turtleLoad = """
            ${DEFAULT_PREFIX_MAPPING.nsPrefixMap.filter { (id, iri) ->
             iri.startsWith(SYSMLV2.BASE)
         }.toList().joinToString("\n") { (id, iri) ->
             "@prefix $id: <$iri> ."
         }.reindent(3)}
            
            ${inserts.joinToString("\n\n").reindent(4)}
        """
         // submit POST request to commit model
         /*val flexoResponseUpdate = flexoRequestPost {
             orgPath("/repos/$projectId/branches/master/update")

             // construct body payload
             sparqlUpdate {
                 sparqlUpdateString
             }
         }*/
         val flexoResponseLoad = context.flexoRequestPut {
             orgPath("/repos/$projectId/branches/master/graph")
             turtle {
                 turtleLoad
             }
         }
         // forward failures to client
         if(flexoResponseLoad.isFailure()) {
             throw ApiServiceError( flexoResponseLoad.toString() )
         }


     }


}