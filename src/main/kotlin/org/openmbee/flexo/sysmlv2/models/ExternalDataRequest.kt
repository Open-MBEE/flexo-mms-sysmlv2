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
@file:UseSerializers(UUIDSerializer::class, URISerializer::class)

package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.infrastructure.URISerializer
import org.openmbee.flexo.sysmlv2.infrastructure.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers


/**
 *
 * @param atType
 * @param atId
 * @param resourceIdentifier
 */
@Serializable
data class ExternalDataRequest(
    @SerialName("@type")
    val atType: ExternalDataRequest.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val resourceIdentifier: java.net.URI? = null
)
{
    /**
    *
    * Values: ExternalData
    */
    enum class AtType(val value: kotlin.String){
        ExternalData("ExternalData");
    }
}

