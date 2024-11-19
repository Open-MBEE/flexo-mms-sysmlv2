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
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.post
import io.ktor.server.resources.put
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString

import org.openmbee.flexo.sysmlv2.Paths
import org.openmbee.flexo.sysmlv2.models.Project
import org.openmbee.flexo.sysmlv2.models.ProjectRequest

fun Route.ProjectApi() {

    delete<Paths.deleteProjectById> {  it ->
        call.application.log.debug("${it.projectId}")
        val exampleContentString = """{
          "@type" : "Project",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "defaultBranch" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "description" : "description",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        }"""
        call.respond(Json.decodeFromString<Project>(exampleContentString))
        //call.respondText(exampleContentString, ContentType.Application.Json)
    }

    get<Paths.getProjectById> {
        call.application.log.debug("${it.projectId}")
        val exampleContentString = """{
          "@type" : "Project",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "defaultBranch" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "description" : "description",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        }"""
        call.respond(Json.decodeFromString<Project>(exampleContentString))
        //call.respondText(exampleContentString, ContentType.Application.Json)
    }

    get<Paths.getProjects> {
        val exampleContentString = """[ {
          "@type" : "Project",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "defaultBranch" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "description" : "description",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        }, {
          "@type" : "Project",
          "created" : "2000-01-23T04:56:07.000+00:00",
          "defaultBranch" : {
            "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
          },
          "name" : "name",
          "description" : "description",
          "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
        } ]"""
        call.respond(Json.decodeFromString<List<Project>>(exampleContentString))
        //call.respondText(exampleContentString, ContentType.Application.Json)
    }

    post<ProjectRequest>("/projects") {
        call.respond(it)
    }

    put<ProjectRequest>("/projects/{projectId}") {
        call.application.log.debug("${call.parameters["projectId"]}")
        call.respond(it)
    }

}
