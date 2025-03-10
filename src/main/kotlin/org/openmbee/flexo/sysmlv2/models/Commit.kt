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
@file:UseSerializers(UUIDSerializer::class, OffsetDateTimeSerializer::class)

package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.infrastructure.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import org.openmbee.flexo.sysmlv2.infrastructure.OffsetDateTimeSerializer

import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atId
 * @param atType
 * @param created
 * @param description
 * @param owningProject
 * @param previousCommit
 */
@Serializable
data class Commit(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: Commit.AtType,
    val created: java.time.OffsetDateTime,
    val description: kotlin.String,
    val owningProject: Identified,
    //val previousCommit: kotlin.collections.List<Identified>
    val previousCommit: Identified?
)
{
    /**
    *
    * Values: Commit
    */
    enum class AtType(val value: kotlin.String){
        Commit("Commit");
    }
}

