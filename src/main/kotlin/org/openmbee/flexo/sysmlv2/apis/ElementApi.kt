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
import kotlinx.serialization.json.*
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.vocabulary.RDF
import org.apache.jena.vocabulary.XSD
import org.openmbee.flexo.sysmlv2.*

fun modelElementConstructQuery(elementTarget: String="?__element"): String {
    return """
        construct {
            $elementTarget a ?element_type ; 
                ?element_p ?element_o .
        }
        where {
            $elementTarget a ?element_type ;
                ?element_p ?element_o .
        }
    """.trimIndent()
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

            // reference the first value
            val obj = values.elementAt(0)

            if(predicate.uri.startsWith(SYSMLV2.VOCABULARY)) {
                // multiple values means it's an array, skip and prefer JSON annotation
                // if we've already seen a JSON annotation with the same property key then ignore
                if (values.size > 1 || seenArrays.contains(propertyKey)) return@forEach

                // object is a resource
                if (obj.isResource) {
                    put(propertyKey, buildJsonObject {
                        put("@id", obj.asResource().uri.autoSuffix)
                    })
                } else if (obj.isLiteral) {
                    val lit = obj.asLiteral()

                    // depending on its datatype
                    when (lit.datatype.uri) {
                        XSD.xboolean.uri -> put(propertyKey, lit.boolean)
                        XSD.integer.uri -> put(propertyKey, lit.int)
                        XSD.decimal.uri, XSD.xdouble.uri -> put(propertyKey, lit.float)
                        else -> put(propertyKey, lit.string)
                    }
                }
                // invalid
                else {
                    throw InvalidTripleError("Don't know what this is", elementIri, predicate, obj)
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
                //throw InvalidTripleError("Unrecognized triple purpose", elementIri, predicate, obj)
            }
        }
    }
}

fun Route.ElementApi() {
    // get an element
    get<Paths.getElementByProjectCommitId> { getElement ->
        val elementIri = SYSMLV2.element(getElement.elementId.toString()).uri

        // submit POST request to query model
        val flexoResponse = flexoRequestPost {
            orgPath("/repos/${getElement.projectId}/locks/Commit.${getElement.commitId}/query")
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
            orgPath("/repos/${getElements.projectId}/locks/Commit.${getElements.commitId}/graph")
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }
        // parse the response model, extract the elements to JSON, and reply to client
        val result = buildJsonArray {
            flexoResponse.parseModel {
                for(subject in model.listSubjects()) {
                    add(extractModelElementToJson(subject.uri))
                }
            }
        }
        call.respond(result)
    }

    get<Paths.getProjectUsageByProjectCommitElement> {
        val flexoResponse = flexoRequestPost {
            orgPath("/repos/${it.projectId}/locks/Commit.${it.commitId}/query")
            sparqlQuery {
                """
                prefix sysml: <https://www.omg.org/spec/SysML#>
                construct {
                  ?element a sysml:ProjectUsage ;
                         ?element_p ?element_o .
                }
                where {
                  ?element a sysml:ProjectUsage ;
                        ?element_p ?element_o .
                }
                """.trimIndent()
            }
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }

        // parse the response model, extract the elements to JSON, and reply to client
        val result = buildJsonArray {
            flexoResponse.parseModel {
                for(subject in model.listSubjects()) {
                    add(extractModelElementToJson(subject.uri))
                }
            }
        }
        call.respond(result)
    }

    get<Paths.getRootsByProjectCommit> {
        // submit POST request to query model
        val flexoResponse = flexoRequestPost {
            orgPath("/repos/${it.projectId}/locks/Commit.${it.commitId}/query")
            sparqlQuery {
                """
                prefix sysml: <https://www.omg.org/spec/SysML#>
                construct {
                  ?element a ?element_type ;
                         ?element_p ?element_o .
                }
                where {
                  ?element a ?element_type ;
                        ?element_p ?element_o .
                  filter not exists {
                     {
                        ?element sysml:owner ?owner .
                     }
                     union
                     {
                        ?element sysml:source ?source ;
                                 sysml:target ?target ;
                                 sysml:owningRelatedElement ?related .
                     }          
                  }
                }
                """.trimIndent()
            }
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }

        // parse the response model, extract the elements to JSON, and reply to client
        val result = buildJsonArray {
            flexoResponse.parseModel {
                for(subject in model.listSubjects()) {
                    add(extractModelElementToJson(subject.uri))
                }
            }
        }
        call.respond(result)
    }

}
