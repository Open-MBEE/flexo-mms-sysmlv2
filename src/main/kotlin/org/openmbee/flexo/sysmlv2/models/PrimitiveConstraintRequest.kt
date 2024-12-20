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

import org.openmbee.flexo.sysmlv2.models.PrimitiveConstraintValue

/**
 *
 * @param atType
 * @param `operator`
 * @param `property`
 * @param `value`
 * @param inverse
 */
@Serializable
data class PrimitiveConstraintRequest(
    @SerialName("@type")
    val atType: PrimitiveConstraintRequest.AtType,
    val `operator`: PrimitiveConstraintRequest.`Operator`,
    val `property`: kotlin.String,
    val `value`: PrimitiveConstraintValue,
    val inverse: kotlin.Boolean? = null
)
{
    /**
    *
    * Values: PrimitiveConstraint
    */
    enum class AtType(val value: kotlin.String){
        PrimitiveConstraint("PrimitiveConstraint");
    }
    /**
    *
    * Values: Equal,Greater_Than,Less_Than
    */
    enum class `Operator`(val value: kotlin.String){
        Equal("="),
        Greater_Than(">"),
        Less_Than("<");
    }
}

