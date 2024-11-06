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

import org.openapitools.server.models.DataIdentityRequest
import org.openapitools.server.models.DataRequest

/**
 * 
 * @param payload 
 * @param atType 
 * @param identity 
 */
data class DataVersionRequest(
    val payload: DataRequest,
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

