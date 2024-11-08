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

import com.google.gson.Gson
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

fun Route.MetaApi() {
    val gson = Gson()
    val empty = mutableMapOf<String, Any?>()

    get<Paths.getDatatypeById> {
        val exampleContentType = "application/json"
        val exampleContentString = """{
          "${'$'}schema" : "${'$'}schema",
          "${'$'}defs" : {
            "key" : ""
          },
          "additionalProperties" : true,
          "title" : "title",
          "type" : "type",
          "properties" : {
            "key" : ""
          },
          "required" : [ "required", "required" ],
          "${'$'}id" : "https://openapi-generator.tech"
        }"""

        when (exampleContentType) {
            "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
            "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
            else -> call.respondText(exampleContentString)
        }

    }

    get<Paths.getDatatypes> {
        val exampleContentType = "application/json"
        val exampleContentString = """{
          "${'$'}schema" : "${'$'}schema",
          "${'$'}defs" : {
            "key" : ""
          }
        }"""

        when (exampleContentType) {
            "application/json" -> call.respond(gson.fromJson(exampleContentString, empty::class.java))
            "application/xml" -> call.respondText(exampleContentString, ContentType.Text.Xml)
            else -> call.respondText(exampleContentString)
        }

    }

}
