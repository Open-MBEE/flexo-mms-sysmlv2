/*******************************************************************************
 * SysML 2 RDF API Implementation
 * Copyright (c) 2025 Model Driven Solutions, Inc.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of theGNU Lesser General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * @license LGPL-3.0-or-later <http://spdx.org/licenses/LGPL-3.0-or-later>
 *
 *******************************************************************************/

package com.modeldriven.sysmlv2.apiService
import com.modeldriven.com.modeldriven.sysmlv2.apiService.ApiCrudInterface
import kotlinx.serialization.json.*
import java.time.LocalDateTime

fun logApiError(txt:String) { //ToDo temp-debug
    println("LOG: $txt")
    if (!txt.startsWith("Warning:"))
        throw Exception(txt)

}

/**
 * The `ApiServiceRoot` class provides a superclass for API services, operations for interacting with an RDF-based service.
 * The service allows manipulation of resources
 * within a model and a project. The service is intended as the implementation of SysML-API services.
 *
 * @constructor Creates an instance of `ApiCrudService` with the specified RDF service.
 * @param rdfService The RDF service instance used for interacting with models and projects.
 */
open class ApiServiceRoot( val rdfService: RdfService) {

    inner open class ApiObject(gc:GraphConfig, val sourceJson:JsonObject?, defaultType:String, explicitIdentifier:String?=null, isNew: Boolean=false) : ModelResource(rdfService, gc, explicitIdentifier, defaultType, isNew=isNew) {
        init {
            if (sourceJson!=null) {
                identifier =
                    if (sourceJson["@id"] != null) sourceJson["@id"]?.jsonPrimitive?.content!! else if (explicitIdentifier != null) explicitIdentifier else null
                typeName =
                    if (sourceJson["@type"] != null) sourceJson["@type"]?.jsonPrimitive?.content else defaultType
            }
        }
    }
}

/**
 * The `ApiCrudService` class provides CRUD operations for interacting with an RDF-based service. It extends
 * the `ApiServiceRoot` and implements the `ApiCrudInterface`. The service allows manipulation of resources
 * within a model and a project. The service is intended as the implementation of SysML-API services.
 *
 * @constructor Creates an instance of `ApiCrudService` with the specified RDF service.
 * @param rdfService The RDF service instance used for interacting with models and projects.
 */
class ApiCrudService( rdfService: RdfService):ApiServiceRoot(rdfService) , ApiCrudInterface {

    inner class PayloadObject(val explicitIdentifier: String?, sourceJson: JsonObject, isNew:Boolean=false) : ApiObject(rdfService.gcModel, sourceJson, "Element", explicitIdentifier, isNew=isNew) {}

    inner class DataVersionObject(sourceJson: JsonObject, val isNewPayload:Boolean=false) : ApiObject(rdfService.gcProject, sourceJson, "DataVersion", isNew=true) {
        val payload: JsonObject? = sourceJson["payload"]?.jsonObject
        var identityId: String? = sourceJson["identity"]?.jsonObject?.get("@id")?.jsonPrimitive?.content // Identity of payload
        val identity:String = if (identityId!=null) identityId!! else rdfService.getUniqueUUID()
        //val identityQualified: String = rdfService.qualified(identity , rdfService.gcModel)!!
        val payloadObject = if(payload!=null) PayloadObject(identity, payload, isNew=isNewPayload) else null

        init{this.isNew = true}

        fun execStore() {
            if (!(payload==null && identityId==null)) {
                if (payloadObject == null) {
                    rdfService.deleteObject(rdfService.gcModel, identity)
                } else {
                    payloadObject.updateObjectJson(payload)
                }
            } else
                logApiError("Payload and identity cant both be null in $sourceJson")
        }
    }
    override fun changeService(changes: JsonArray, isNewPayload:Boolean) {
        rdfService.exportStream.println("##Start change transaction at ${LocalDateTime.now()}")
        this.rdfService.startTransaction()
        for (dataVersion in changes) {
            if (dataVersion is JsonObject) {
                val dataVersionObject = DataVersionObject(dataVersion, isNewPayload=isNewPayload)
                dataVersionObject.execStore()
            } else {
                logApiError("Warning: Change is not a JSON object: $dataVersion")
            }
        }
        rdfService.exportStream.println("##Ending change transaction at ${LocalDateTime.now()}")
        this.rdfService.endTransaction()
        rdfService.exportStream.println("##Change Transaction complete at ${LocalDateTime.now()}")
    }
    override fun getElements(uniqueID:String?) : JsonArray {
        return rdfService.getJsonById(rdfService.gcModel!!, uniqueID )
    }
}