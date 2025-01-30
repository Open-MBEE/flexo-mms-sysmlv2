/**
* Systems Modeling API and Services
* REST/HTTP platform specific model (PSM) for the Systems Modeling API and Services
*
* The version of the OpenAPI document: 1.0
*
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openmbee.flexo.sysmlv2.apis

import io.ktor.server.application.*
import io.ktor.server.resources.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.*
import org.apache.jena.datatypes.xsd.XSDDatatype
import org.apache.jena.graph.Node
import org.apache.jena.graph.NodeFactory
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.vocabulary.DCTerms
import org.apache.jena.vocabulary.RDF
import org.openmbee.flexo.sysmlv2.*
import org.openmbee.flexo.sysmlv2.models.Commit
import org.openmbee.flexo.sysmlv2.models.CommitRequest
import org.openmbee.flexo.sysmlv2.models.DataVersion
import org.openmbee.flexo.sysmlv2.models.Identified
import java.time.OffsetDateTime
import java.util.*

class InvalidSysmlSerializationError(message: String): Error(message)

fun FlexoModelHandler.commitFromModel(
    commitIri: String,
    properties: Map<Property, Set<RDFNode>?>,
    projectUuid: UUID=UUID.fromString(properties[MMS.id].resource()?.uri?.uriSuffix?: ""),
): Commit {
    // generate commit object
    return Commit(
        atId = UUID.fromString(commitIri.uriSuffix),
        atType = Commit.AtType.Commit,
        created = OffsetDateTime.parse(properties[MMS.submitted]!!.literal()!!),
        description = properties[DCTerms.description]?.literal()?: "",
        owningProject = Identified(atId = projectUuid),
        //previousCommit = properties[MMS.parent]?.map {
        //    Identified(atId = UUID.fromString(it.asResource().uri.uriSuffix))
        //}?: emptyList()
        previousCommit = null
    )
}

fun JsonPrimitive.toRdfLiteralNode(): Node {
    // resolve to XSD datatype
    val datatype = if(jsonPrimitive.isString) {
        XSDDatatype.XSDstring
    }
    else if(jsonPrimitive.booleanOrNull != null) {
        XSDDatatype.XSDboolean
    }
    else {
        XSDDatatype.XSDdecimal
    }

    // create typed literal
    return NodeFactory.createLiteral(content, datatype)
}

fun Route.CommitApi() {
    get<Paths.getChangeByProjectCommitId> {
        val exampleContentString = """{
          "payload" : {
            "owner" : {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            },
            "textualRepresentation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "ownedAnnotation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "ownedElement" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "aliasIds" : [ "aliasIds", "aliasIds" ],
            "@type" : "Element",
            "ownedRelationship" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "documentation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "isImpliedIncluded" : true,
            "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "@type" : "DataVersion",
          "identity" : {
            "@type" : "DataIdentity",
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        }"""
        call.respond(Json.decodeFromString<DataVersion>(exampleContentString))
    }

    get<Paths.getChangesByProjectCommit> {
        val exampleContentString = """[ {
          "payload" : {
            "owner" : {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            },
            "textualRepresentation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "ownedAnnotation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "ownedElement" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "aliasIds" : [ "aliasIds", "aliasIds" ],
            "@type" : "Element",
            "ownedRelationship" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "documentation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "isImpliedIncluded" : true,
            "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "@type" : "DataVersion",
          "identity" : {
            "@type" : "DataIdentity",
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        }, {
          "payload" : {
            "owner" : {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            },
            "textualRepresentation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "ownedAnnotation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "ownedElement" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "aliasIds" : [ "aliasIds", "aliasIds" ],
            "@type" : "Element",
            "ownedRelationship" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "documentation" : [ {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            }, {
              "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
            } ],
            "isImpliedIncluded" : true,
            "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "@type" : "DataVersion",
          "identity" : {
            "@type" : "DataIdentity",
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        } ]"""
        call.respond(Json.decodeFromString<List<DataVersion>>(exampleContentString))
    }

    get<Paths.getCommitByProjectAndId> { getCommits ->
        // submit GET request to retrieve project metadata
        val flexoResponse = flexoRequestPost {
            orgPath("/repos/${getCommits.projectId}/query")

            sparqlQuery {
                """
                    prefix mms: <${MMS.uri}>

                    select ?commit_p ?commit_o {
                        ?commit a mms:Commit ;
                            mms:id ${NodeFactory.createLiteral(getCommits.commitId.toString()).stringify()} ;
                            ?commit_p ?commit_o .
                    }
                """.trimIndent()
            }
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }

        // parse the response model, convert it to JSON, and reply to client
        /*call.respond(flexoResponse.parseModel {
            // each commit node
            for(commit in indexInv(MMS.Commit.uri)[RDF.type]?: emptySet()) {
                // reference the commit's IRI
                val commitIri = commit.asResource().uri;

                // generate commit object
                commitFromModel(commitIri, indexOut(commitIri), getCommits.projectId)
            }
        })*/
        call.respond(Commit(
            atId = getCommits.commitId,
            atType = Commit.AtType.Commit,
            created = OffsetDateTime.now(),
            description = "",
            owningProject = Identified(atId = getCommits.projectId),
            previousCommit = null
        ))
    }

    get<Paths.getCommitsByProject> { getCommits ->
        // submit GET request to retrieve project metadata
        val flexoResponse = flexoRequestGet {
            orgPath("/repos/${getCommits.projectId}")
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }

        // parse the response model, convert it to JSON, and reply to client
        /*call.respond(flexoResponse.parseModel {
            // each commit node
            for(commit in indexInv(MMS.Commit.uri)[RDF.type]?: emptySet()) {
                // reference the commit's IRI
                val commitIri = commit.asResource().uri;

                // generate commit object
                commitFromModel(commitIri, indexOut(commitIri), getCommits.projectId)
            }
        })*/
        call.respond(listOf(Commit(
            atId = UUID.randomUUID(),
            atType = Commit.AtType.Commit,
            created = OffsetDateTime.now(),
            description = "",
            owningProject = Identified(atId = getCommits.projectId),
            previousCommit = null
        )))
    }

    post<CommitRequest>("/projects/{projectId}/commits") { commit ->
        val projectId = "${call.parameters["projectId"]}"

        val inserts = mutableListOf<String>()
        val deleteIncoming = mutableListOf<String>()
        val values = mutableListOf<String>()

        // each change (DataVersionRequest)
        for((index, change) in commit.change.withIndex()) {
            val (payload, atType, identity) = change

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
            val identityId = identity?.atId?.toString()
            var payloadId = payload?.getOrDefault("@id", null)?.jsonPrimitive?.content
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
        val flexoResponseLoad = flexoRequestPut {
            orgPath("/repos/$projectId/branches/master/graph")
            turtle {
                turtleLoad
            }
        }
        // forward failures to client
        if(flexoResponseLoad.isFailure()) {
            return@post forward(flexoResponseLoad)
        }

        // parse the response model, convert it to JSON, and reply to client
        // TODO load model doesn't return location header or commit info
        //call.respond(flexoResponseLoad.parseLdp {
        //    commitFromModel(focalIri!!, focalOutgoing, UUID.fromString(projectId))
        //})
        call.respond(Commit(
            atId = UUID.randomUUID(),
            atType = Commit.AtType.Commit,
            created = OffsetDateTime.now(),
            description = "",
            owningProject = Identified(atId = UUID.fromString(projectId)),
            previousCommit = null
        ))
    }
}
