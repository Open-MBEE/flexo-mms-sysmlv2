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
 * @param atId 
 * @param atType 
 * @param created 
 * @param defaultBranch 
 * @param description 
 * @param name 
 */
data class Project(
    val atId: java.util.UUID,
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

