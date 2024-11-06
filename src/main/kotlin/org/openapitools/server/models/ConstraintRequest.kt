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
package org.openapitools.server.models

import org.openapitools.server.models.CompositeConstraintRequest
import org.openapitools.server.models.Constraint
import org.openapitools.server.models.PrimitiveConstraintRequest
import org.openapitools.server.models.PrimitiveConstraintValue

/**
 * 
 * @param atType 
 * @param constraint 
 * @param `operator` 
 * @param `property` 
 * @param `value` 
 * @param inverse 
 */
data class ConstraintRequest(
    val atType: ConstraintRequest.AtType,
    val constraint: kotlin.collections.List<Constraint>,
    val `operator`: ConstraintRequest.`Operator`,
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

