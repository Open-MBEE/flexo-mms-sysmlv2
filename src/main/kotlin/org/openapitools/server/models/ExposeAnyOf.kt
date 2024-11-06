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
import org.openapitools.server.models.VisibilityKind

/**
 * 
 * @param atId 
 * @param atType 
 * @param aliasIds 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param importOwningNamespace 
 * @param importedElement 
 * @param isImplied 
 * @param isImpliedIncluded 
 * @param isImportAll 
 * @param isLibraryElement 
 * @param isRecursive 
 * @param name 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedRelatedElement 
 * @param ownedRelationship 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelatedElement 
 * @param owningRelationship 
 * @param qualifiedName 
 * @param relatedElement 
 * @param shortName 
 * @param source 
 * @param target 
 * @param textualRepresentation 
 * @param visibility 
 */
data class ExposeAnyOf(
    val atId: java.util.UUID,
    val atType: ExposeAnyOf.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val importOwningNamespace: Identified,
    val importedElement: Identified,
    val isImplied: ActionDefinitionRequestAnyOfIsConjugated,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isImportAll: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
    val isRecursive: ActionDefinitionRequestAnyOfIsConjugated,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: ActionUsageRequestAnyOfMultiplicity,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity,
    val owningRelatedElement: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val relatedElement: kotlin.collections.List<Identified>,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val source: kotlin.collections.List<Identified>,
    val target: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val visibility: VisibilityKind
) 
{
    /**
    * 
    * Values: Expose
    */
    enum class AtType(val value: kotlin.String){
        Expose("Expose");
    }
}

