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
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.vocabulary.DCTerms
import org.apache.jena.vocabulary.RDF
import org.openmbee.flexo.sysmlv2.*
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.Project
import org.openmbee.flexo.sysmlv2.models.ProjectRequest
import java.time.OffsetDateTime
import java.util.*


//    val exampleContentString = """{
//      "@type" : "Project",
//      "created" : "2000-01-23T04:56:07.000+00:00",
//      "defaultBranch" : {
//        "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//      },
//      "name" : "name",
//      "description" : "description",
//      "@id" : "046b6c7f-0b8a-43b9-b35d-6489e6daee91"
//    }"""
fun FlexoResponseHandler.projectFromResponse(
    outgoing: Map<Property, Set<RDFNode>> = primary,
    projectUuid: UUID = UUID.fromString(outgoing[MMS.id]?.literal()),
    branchUuid: UUID = UUID.fromString(outgoing[MMS.defaultBranchId]?.literal())
): Project {
    return Project(
        atId = projectUuid,
        atType = Project.AtType.Project,
        created = OffsetDateTime.parse(
            outgoing[MMS.created]?.literal()
                ?: OffsetDateTime.now().toString()),
        defaultBranch = Identified(branchUuid),
        description = outgoing[DCTerms.description]?.literal()?: "",
        name = outgoing[DCTerms.title]?.literal()?: ""
    )
}

fun Route.ProjectApi() {
    // delete a project
    delete<Paths.deleteProjectById> { deleteProject ->
        call.application.log.debug("${deleteProject.projectId}")

        notImplemented()
    }

    // get a project by its ID
    get<Paths.getProjectById> { getProject ->
        // fetch the project by the given project ID
        val flexoResponse = flexoRequestGet {
            orgPath("/repos/${getProject.projectId}")
        }

        // parse the response model, convert it JSON, and reply to client
        call.respond(flexoResponse.parseLdp {
            projectFromResponse(primary, UUID.fromString(self))
        })
    }

    // get all projects
    get<Paths.getProjects> { getProjects ->
        // submit GET request for all repos
        val flexoResponse = flexoRequestGet {
            orgPath("/repos")
        }

        // parse the response model, convert it to JSON, and reply to client
        call.respond(flexoResponse.parseLdp {
            // find all repos and transform each one into a project by its outgoing triples
            model.listSubjectsWithProperty(RDF.type, model.createResource(MMS.Repo)).mapWith {
                projectFromResponse(it.outgoing())
            }.toList()
        })
    }

    // create new project via POST
    post<ProjectRequest>("/projects") { projectRequest ->
        // generate a UUID for the repo
        val repoUuid = UUID.randomUUID()

        // generate a UUID for the default branch
        val branchUuid = UUID.randomUUID()

        // submit POST request to create new repo
        val flexoResponse = flexoRequestPost {
            orgPath("/repos")

            // set project ID slug
            addHeaders(
                "Slug" to repoUuid.toString()
            )

            // build query parameters; set default branch ID
            addQueryParams(
                "defaultBranchId" to branchUuid.toString()
            )

            // construct body payload
            turtle {
                thisSubject(
                    DCTerms.title to projectRequest.name.en,
                    DCTerms.description to projectRequest.description?.en,
                )
            }
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@post forward(flexoResponse)
        }

        // parse the response model, convert it to JSON, and reply to client
        call.respond(flexoResponse.parseLdp {
            projectFromResponse()
        })
    }

    // create new project via PUT
    put<ProjectRequest>("/projects/{projectId}") { projectRequest ->
        val projectId = "${call.parameters["projectId"]}"

        // generate a UUID for the default branch
        val branchUuid = UUID.randomUUID()

        // submit PUT request to create new repo
        val flexoResponse = flexoRequestPut {
            orgPath("/repos/${projectId}")

            // build query parameters; set default branch ID
            addQueryParams(
                "defaultBranchId" to branchUuid.toString()
            )

            // construct body payload
            turtle {
                thisSubject(
                    DCTerms.title to projectRequest.name.en,
                    DCTerms.description to projectRequest.description?.en,
                )
            }
        }

        // forward failures to client
        if(flexoResponse.isFailure()) {
            return@put forward(flexoResponse)
        }

        // parse the response model, convert it to JSON, and reply to client
        call.respond(flexoResponse.parseLdp {
            projectFromResponse()
        })
    }
}
