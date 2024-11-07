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

import UUIDSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers


/**
 *
 * @param atId
 * @param atType
 */
@Serializable
data class DataIdentityRequest(
    val atId: java.util.UUID,
    val atType: DataIdentityRequest.AtType? = null
)
{
    /**
    *
    * Values: DataIdentity
    */
    enum class AtType(val value: kotlin.String){
        DataIdentity("DataIdentity");
    }
}

