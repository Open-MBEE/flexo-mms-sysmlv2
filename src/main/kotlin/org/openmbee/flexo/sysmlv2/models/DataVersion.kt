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

import org.openmbee.flexo.sysmlv2.models.Data
import org.openmbee.flexo.sysmlv2.models.DataIdentity

/**
 *
 * @param atId
 * @param atType
 * @param identity
 * @param payload
 */
data class DataVersion(
    val atId: java.util.UUID,
    val atType: DataVersion.AtType,
    val identity: DataIdentity,
    val payload: Data
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

