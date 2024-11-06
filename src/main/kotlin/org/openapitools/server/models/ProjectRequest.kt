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

import org.openapitools.server.models.Identified

/**
 * 
 * @param name 
 * @param atType 
 * @param defaultBranch 
 * @param description 
 */
data class ProjectRequest(
    val name: kotlin.String,
    val atType: ProjectRequest.AtType? = null,
    val defaultBranch: Identified? = null,
    val description: kotlin.String? = null
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
