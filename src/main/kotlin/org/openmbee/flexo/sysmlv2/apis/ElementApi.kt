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

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import org.openmbee.flexo.sysmlv2.Paths
import io.ktor.server.resources.options
import io.ktor.server.resources.get
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.resources.delete
import io.ktor.server.resources.head
import io.ktor.server.resources.patch
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.openmbee.flexo.sysmlv2.models.Element
import org.openmbee.flexo.sysmlv2.models.ProjectUsage

fun Route.ElementApi() {
    get<Paths.getElementByProjectCommitId> {
        val exampleContentString = """{
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
        }"""
        call.respond(Json.decodeFromString<Element>(exampleContentString))
    }

    get<Paths.getElementsByProjectCommit> {
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
        call.respond(Json.decodeFromString<List<Element>>(exampleContentString))
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

        call.respond(Json.decodeFromString<List<Element>>(exampleContentString))
    }

}
