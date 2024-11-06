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

import org.openapitools.server.models.ActionDefinitionRequestAnyOfDeclaredShortName
import org.openapitools.server.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openapitools.server.models.ActionUsageRequestAnyOfMultiplicity
import org.openapitools.server.models.Identified

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
 */
data class PackageRequestAnyOf(
    val atType: PackageRequestAnyOf.AtType,
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val filterCondition: kotlin.collections.List<Identified>? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedImport: kotlin.collections.List<Identified>? = null,
    val ownedMember: kotlin.collections.List<Identified>? = null,
    val ownedMembership: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity? = null,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null
) 
{
    /**
    * 
    * Values: Package
    */
    enum class AtType(val value: kotlin.String){
        Package("Package");
    }
}

