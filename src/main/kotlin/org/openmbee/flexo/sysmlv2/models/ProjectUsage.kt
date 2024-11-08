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
package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atId
 * @param atType
 * @param usedCommit
 * @param usedProject
 */
data class ProjectUsage(
    val atId: java.util.UUID,
    val atType: ProjectUsage.AtType,
    val usedCommit: Identified,
    val usedProject: Identified
)
{
    /**
    *
    * Values: ProjectUsage
    */
    enum class AtType(val value: kotlin.String){
        ProjectUsage("ProjectUsage");
    }
}

