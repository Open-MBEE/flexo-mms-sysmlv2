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

import org.openapitools.server.models.PrimitiveConstraintValue

/**
 * 
 * @param atType 
 * @param inverse 
 * @param `operator` 
 * @param `property` 
 * @param `value` 
 */
data class PrimitiveConstraint(
    val atType: PrimitiveConstraint.AtType,
    val inverse: kotlin.Boolean,
    val `operator`: PrimitiveConstraint.`Operator`,
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

