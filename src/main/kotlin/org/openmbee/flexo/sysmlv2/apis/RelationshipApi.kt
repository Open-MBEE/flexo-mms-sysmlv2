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
import kotlinx.serialization.json.JsonObject

fun Route.RelationshipApi() {

    get<Paths.getRelationshipsByProjectCommitRelatedElement> {
        val exampleContentString = """[ {
          "@type" : "Relationship",
          "ownedRelatedElement" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "source" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "owner" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "textualRepresentation" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "isImplied" : true,
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
          "target" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "relatedElement" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ]
        }, {
          "@type" : "Relationship",
          "ownedRelatedElement" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "source" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "declaredName" : "ActionDefinitionRequest_anyOf_declaredShortName",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "owner" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "textualRepresentation" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "isImplied" : true,
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
          "target" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ],
          "relatedElement" : [ {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }, {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          } ]
        } ]"""
        call.respond(Json.decodeFromString<List<JsonObject>>(exampleContentString))
    }

}
