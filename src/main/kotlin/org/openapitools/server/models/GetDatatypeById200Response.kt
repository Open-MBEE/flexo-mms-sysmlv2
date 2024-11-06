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


/**
 * 
 * @param dollarSchema 
 * @param dollarId 
 * @param title 
 * @param type 
 * @param properties 
 * @param dollarDefs 
 * @param required 
 * @param additionalProperties 
 */
data class GetDatatypeById200Response(
    val dollarSchema: kotlin.String,
    val dollarId: java.net.URI,
    val title: kotlin.String,
    val type: kotlin.String,
    val properties: kotlin.collections.Map<kotlin.String, kotlin.Any>,
    val dollarDefs: kotlin.collections.Map<kotlin.String, kotlin.Any>? = null,
    val required: kotlin.collections.List<kotlin.String>? = null,
    val additionalProperties: kotlin.Boolean? = null
) 
