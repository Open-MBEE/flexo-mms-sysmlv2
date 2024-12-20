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
import org.openmbee.flexo.sysmlv2.models.Tag
import org.openmbee.flexo.sysmlv2.models.TagRequest

fun Route.TagApi() {

    delete<Paths.deleteTagByProjectAndId> {
        val exampleContentString = """{
          "@type" : "Tag",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "taggedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "referencedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "owningProject" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }
        }"""

        call.respond(Json.decodeFromString<Tag>(exampleContentString))
    }

    get<Paths.getTagByProjectAndId> {
        val exampleContentString = """{
          "@type" : "Tag",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "taggedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "referencedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "owningProject" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }
        }"""

        call.respond(Json.decodeFromString<Tag>(exampleContentString))

    }

    get<Paths.getTagsByProject> {
        val exampleContentString = """[ {
          "@type" : "Tag",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "taggedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "referencedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "owningProject" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }
        }, {
          "@type" : "Tag",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "taggedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91",
          "referencedCommit" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "owningProject" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          }
        } ]"""

        call.respond(Json.decodeFromString<List<Tag>>(exampleContentString))
    }

    post<TagRequest>("/projects/{projectId}/tags") {
        call.respond(it)
    }

}
