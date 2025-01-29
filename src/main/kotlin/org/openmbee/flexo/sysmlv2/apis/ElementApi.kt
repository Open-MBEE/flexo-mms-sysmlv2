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
import kotlinx.serialization.json.*
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.vocabulary.RDF
import org.apache.jena.vocabulary.XSD
import org.openmbee.flexo.sysmlv2.*
import org.openmbee.flexo.sysmlv2.models.ProjectUsage

fun modelElementConstructQuery(elementTarget: String="?__element"): String {
    return """
        construct {
            $elementTarget a ?element_type ; 
                ?element_p ?element_o .
        }
        where {
            {
                $elementTarget a ?element_type ;
                    ?element_p ?element_o .
                    
                filter not exists {
                    ?element_o a rdf:Collection .
                }
            }  
            union {
                $elementTarget ?ownedRelation 
                ?element_o rdf:rest*/rdf:first ?ownedElement .
                
                ?ownedElement a ?ownedElementType .
                
                $elementTarget ?ownedRelation [
                    flexo:order ?order ;
                    rdf:member ?member ;
                ] .
            }
            
            optional {
                ?owningElement ?owningRelation ?owningList .
                
                ?owningList rdf:rest*/rdf:first $elementTarget .
                
                ?owningElement ?owningRelation ($elementTarget, ?b, ?c) .
            }
        }
    """
}

class InvalidTripleError(
    message: String,
    subjectIri: String,
    predicate: Property,
    value: RDFNode
): Error("$message at <$subjectIri> <${predicate.uri}> ${value.stringify()}")

fun FlexoModelHandler.extractModelElementToJson(elementIri: String): JsonObject {
    // direct outgoing properties of element
    val out = indexOut(elementIri)

    // extract type
    val type = out[RDF.type].resource()?.uri?.autoSuffix
    val id = elementIri.urnSuffix

    return buildJsonObject {
        put("@type", type)
        put("@id", id)

        // keeps track of json array annotations, if we already deserialized an array, ignore any triple with the same property
        val seenArrays = mutableListOf<String>()

        // outgoing properties
        out.forEach { (predicate, values) ->
            // extract the suffix name part
            var propertyKey = predicate.uri.autoSuffix

            // relations
            if(predicate.uri.startsWith(SYSMLV2.RELATION)) {
                // prefer the annotation triple for all relations, skip
            }
            // properties & annotations
            else {
                // transform the single object
                val obj = values.elementAt(0)

                // properties
                if(predicate.uri.startsWith(SYSMLV2.PROPERTY)) {
                    // multiple values means it's an array, skip and prefer JSON annotation
                    // if we've already seen a JSON annotation with the same property key then ignore
                    if (values.size > 1 || seenArrays.contains(propertyKey)) return@forEach

                    // object is a Literal
                    if (obj.isLiteral) {
                        val lit = obj.asLiteral()

                        // depending on its datatype
                        when (lit.datatype) {
                            XSD.xboolean -> put(propertyKey, lit.boolean)
                            XSD.integer -> put(propertyKey, lit.int)
                            XSD.decimal, XSD.xdouble -> put(propertyKey, lit.float)
                            else -> put(propertyKey, lit.string)
                        }
                    }
                    // object is a Resource
                    else {
                        put(propertyKey, buildJsonObject {
                            put("@id", obj.asResource().uri.autoSuffix)
                        })
                    }
                }
                // annotations
                else if(predicate.uri.startsWith(SYSMLV2.ANNOTATION_JSON)) {
                    // expect exactly 1 object
                    if(values.size != 1) {
                        throw InvalidTripleError("Expected exactly 1 object with this predicate", elementIri, predicate, obj)
                    }

                    // object is not a Literal
                    if (!obj.isLiteral) {
                        throw InvalidTripleError("Expected annotation property to point to an RDF literal", elementIri, predicate, obj)
                    }

                    // cast to literal
                    val lit = obj.asLiteral()

                    // expect valid JSON
                    val jsonElement = try {
                        Json.parseToJsonElement(lit.string)
                    } catch (parse: Error) {
                        throw InvalidTripleError("Expected annotation property to encode a JSON element", elementIri, predicate, obj)
                    }

                    // infer property key from URN
                    propertyKey = predicate.uri.urnSuffix

                    // add parsed element to JSON object
                    put(propertyKey, jsonElement)

                    // do not overwrite this property
                    seenArrays.add(propertyKey)
                }
                // something else
                else {
                    throw InvalidTripleError("Unrecognized triple purpose", elementIri, predicate, obj)
                }
            }
        }
    }
}



//val exampleContentString = """{
//  "owner" : {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  },
//  "textualRepresentation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "ownedAnnotation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "ownedElement" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "aliasIds" : [ "aliasIds", "aliasIds" ],
//  "@type" : "Element",
//  "ownedRelationship" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "documentation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "isImpliedIncluded" : true,
//  "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
//  "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//}"""
//
//val exampleContentString = """[ {
//  "owner" : {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  },
//  "textualRepresentation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "ownedAnnotation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "ownedElement" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "aliasIds" : [ "aliasIds", "aliasIds" ],
//  "@type" : "Element",
//  "ownedRelationship" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "documentation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "isImpliedIncluded" : true,
//  "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
//  "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//}, {
//  "owner" : {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  },
//  "textualRepresentation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "ownedAnnotation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "ownedElement" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "aliasIds" : [ "aliasIds", "aliasIds" ],
//  "@type" : "Element",
//  "ownedRelationship" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "documentation" : [ {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  }, {
//    "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//  } ],
//  "isImpliedIncluded" : true,
//  "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
//  "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//} ]"""

fun Route.ElementApi() {
    // get an element
    get<Paths.getElementByProjectCommitId> { getElement ->
        val elementIri = SYSMLV2.element(getElement.elementId.toString()).uri

        // submit POST request to query model
        val flexoResponse = flexoRequestPost {
            orgPath("/repos/${getElement.projectId}/commits/${getElement.commitId}/query")

            sparqlQuery {
                modelElementConstructQuery(elementIri)
            }
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }

        // parse the response model, extract the target element to JSON, and reply to client
        call.respond(flexoResponse.parseModel {
            // extract the target model element to JSON
            extractModelElementToJson(elementIri)
        })
    }

    // get multiple elements
    get<Paths.getElementsByProjectCommit> { getElements ->
        // submit POST request to query model
        val flexoResponse = flexoRequestGet {
            orgPath("/repos/${getElements.projectId}/branches/master/graph")

        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }
        val result = buildJsonArray {
            flexoResponse.parseModel {
                for(subject in model.listSubjects()) {
                    if (subject.uri != "urn:this") {
                        add(extractModelElementToJson(subject.uri))
                    }
                }
            }
        }

        // parse the response model, extract the elements to JSON, and reply to client
        call.respond(result)
    }

    get<Paths.getProjectUsageByProjectCommitElement> {

        val exampleContentString = """{
          "@type" : "ProjectUsage",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "usedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "usedProject" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }
        }"""
        call.respond(Json.decodeFromString<ProjectUsage>(exampleContentString))
    }

    get<Paths.getRootsByProjectCommit> {
        val exampleContentString = """[ {
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
        }, {
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
        } ]"""

        call.respond(Json.decodeFromString<List<JsonObject>>(exampleContentString))
    }

}
