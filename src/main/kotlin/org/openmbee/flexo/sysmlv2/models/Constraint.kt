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

import org.openmbee.flexo.sysmlv2.models.CompositeConstraint
import org.openmbee.flexo.sysmlv2.models.PrimitiveConstraint
import org.openmbee.flexo.sysmlv2.models.PrimitiveConstraintValue

/**
 *
 * @param atType
 * @param constraint
 * @param `operator`
 * @param inverse
 * @param `property`
 * @param `value`
 */
@Serializable
data class Constraint(
    @SerialName("@type")
    val atType: Constraint.AtType,
    val constraint: kotlin.collections.List<Constraint>,
    val `operator`: Constraint.`Operator`,
    val inverse: kotlin.Boolean,
    val `property`: kotlin.String,
    val `value`: PrimitiveConstraintValue
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

