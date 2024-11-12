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
@file:UseSerializers(UUIDSerializer::class)

package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.infrastructure.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.openmbee.flexo.sysmlv2.models.DataIdentityRequest
import org.openmbee.flexo.sysmlv2.models.DataRequest

/**
 *
 * @param payload
 * @param atType
 * @param identity
 */
@Serializable
data class DataVersionRequest(
    val payload: DataRequest,
    @SerialName("@type")
    val atType: DataVersionRequest.AtType? = null,
    val identity: DataIdentityRequest? = null
)
{
    /**
    *
    * Values: DataVersion
    */
    enum class AtType(val value: kotlin.String){
        DataVersion("DataVersion");
    }
}

