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
 * @param defaultBranch
 * @param description
 * @param name
 */
@Serializable
data class Project(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: Project.AtType,
    val created: java.time.OffsetDateTime,
    val defaultBranch: Identified,
    val description: kotlin.String,
    val name: kotlin.String
)
{
    /**
    *
    * Values: Project
    */
    enum class AtType(val value: kotlin.String){
        Project("Project");
    }
}

