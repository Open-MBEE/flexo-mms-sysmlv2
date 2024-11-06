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
 * @param head 
 * @param name 
 * @param owningProject 
 * @param referencedCommit 
 */
data class Branch(
    val atId: java.util.UUID,
    val atType: Branch.AtType,
    val created: java.time.OffsetDateTime,
    val head: Identified,
    val name: kotlin.String,
    val owningProject: Identified,
    val referencedCommit: Identified
) 
{
    /**
    * 
    * Values: Branch
    */
    enum class AtType(val value: kotlin.String){
        Branch("Branch");
    }
}

