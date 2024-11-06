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
import org.openapitools.server.models.LibraryPackageRequest
import org.openapitools.server.models.PackageRequestAnyOf

/**
 * 
 * @param atType 
 * @param atId 
 * @param aliasIds 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param filterCondition 
 * @param importedMembership 
 * @param isImpliedIncluded 
 * @param isLibraryElement 
 * @param member 
 * @param membership 
 * @param name 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedImport 
 * @param ownedMember 
 * @param ownedMembership 
 * @param ownedRelationship 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelationship 
 * @param qualifiedName 
 * @param shortName 
 * @param textualRepresentation 
 * @param isStandard 
 */
data class PackageRequest(
    val atType: PackageRequest.AtType,
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val filterCondition: kotlin.collections.List<Identified>? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val name: kotlin.String? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedImport: kotlin.collections.List<Identified>? = null,
    val ownedMember: kotlin.collections.List<Identified>? = null,
    val ownedMembership: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelationship: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val shortName: kotlin.String? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val isStandard: kotlin.Boolean? = null
) 
{
    /**
    * 
    * Values: LibraryPackage
    */
    enum class AtType(val value: kotlin.String){
        LibraryPackage("LibraryPackage");
    }
}
