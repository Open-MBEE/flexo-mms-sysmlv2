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
import io.ktor.server.response.*
import io.ktor.server.resources.get
import io.ktor.server.resources.delete
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.decodeFromString
import org.apache.jena.rdf.model.Property
import org.apache.jena.rdf.model.RDFNode
import org.apache.jena.rdf.model.ResourceFactory
import org.apache.jena.vocabulary.DCTerms
import org.apache.jena.vocabulary.RDF
import org.openmbee.flexo.sysmlv2.*
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.Tag
import org.openmbee.flexo.sysmlv2.models.TagRequest
import java.time.OffsetDateTime
import java.util.*

fun FlexoModelHandler.tagFromResponse(
    outgoing: Map<Property, Set<RDFNode>>,
    projectUuid: UUID,
    tagUuid: UUID
): Tag {
    val commit = Identified(UUID.fromString(outgoing[MMS.commit]!!.resource()!!.uri.uriSuffix))
    return Tag(
        atId = tagUuid,
        atType = Tag.AtType.Tag,
        created = OffsetDateTime.parse(
            outgoing[MMS.created]?.literal()
                ?: OffsetDateTime.now().toString()
        ),
        owningProject = Identified(projectUuid),
        referencedCommit = commit,
        taggedCommit = commit,
        name = outgoing[DCTerms.title]?.literal() ?: "",
    )
}
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

    get<Paths.getTagByProjectAndId> { path ->
        val flexoResponse = flexoRequestGet {
            orgPath("/repos/${path.projectId}/locks/${path.tagId}")
        }
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }
        val tags = flexoResponse.parseModel {
            model.listSubjectsWithProperty(RDF.type, MMS.Lock).mapWith {
                tagFromResponse(it.outgoing(), path.projectId, UUID.fromString(it.uri.uriSuffix))
            }.toList()
        }
        call.respond(if (tags.isEmpty()) "" else tags[0])

    }

    get<Paths.getTagsByProject> { path ->
        // submit GET request for all tags
        val flexoResponse = flexoRequestGet {
            orgPath("/repos/${path.projectId}/locks")
        }
        if(flexoResponse.isFailure()) {
            return@get forward(flexoResponse)
        }
        // parse the response model, convert it to JSON, and reply to client
        call.respond(flexoResponse.parseModel {
            val tags = mutableListOf<Tag>()
            // find all locks and transform each one into a tag by its outgoing triples
            model.listSubjectsWithProperty(RDF.type, MMS.Lock).forEach {
                // ignore flexo created locks
                if (it.uri.uriSuffix.startsWith("Commit.")) {
                    return@forEach
                }
                tags.add(tagFromResponse(it.outgoing(), path.projectId, UUID.fromString(it.uri.uriSuffix)))
            }
            tags
        })
    }

    post<TagRequest>("/projects/{projectId}/tags") { request ->
        val projectId = call.parameters["projectId"]
        val tagId = UUID.randomUUID()
        val createTagResponse = flexoRequestPost {
            orgPath("/repos/${projectId}/locks")
            addHeaders(
                "Slug" to tagId.toString()
            )
            turtle {
                thisSubject(
                    MMS.ref to ResourceFactory.createResource("./Commit.${request.taggedCommit.atId}"),
                    DCTerms.title to request.name.en
                )
            }
        }
        // forward failures to client
        if(createTagResponse.isFailure()) {
            return@post forward(createTagResponse)
        }
        call.respond(createTagResponse.parseLdp {
            tagFromResponse(focalOutgoing, UUID.fromString(projectId), tagId)
        })
    }
}
