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

import org.openapitools.server.models.ConstraintRequest

/**
 * 
 * @param atType 
 * @param select 
 * @param `where` 
 */
data class QueryRequest(
    val atType: QueryRequest.AtType? = null,
    val select: kotlin.collections.List<kotlin.String>? = null,
    val `where`: ConstraintRequest? = null
) 
{
    /**
    * 
    * Values: Query
    */
    enum class AtType(val value: kotlin.String){
        Query("Query");
    }
}

