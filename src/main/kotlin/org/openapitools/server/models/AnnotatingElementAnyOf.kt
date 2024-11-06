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
 * @param atId 
 * @param atType 
 * @param aliasIds 
 * @param annotatedElement 
 * @param `annotation` 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param isImpliedIncluded 
 * @param isLibraryElement 
 * @param name 
 * @param ownedAnnotatingRelationship 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedRelationship 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelationship 
 * @param qualifiedName 
 * @param shortName 
 * @param textualRepresentation 
 */
data class AnnotatingElementAnyOf(
    val atId: java.util.UUID,
    val atType: AnnotatingElementAnyOf.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val annotatedElement: kotlin.collections.List<Identified>,
    val `annotation`: kotlin.collections.List<Identified>,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: ActionUsageRequestAnyOfMultiplicity,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val textualRepresentation: kotlin.collections.List<Identified>
) 
{
    /**
    * 
    * Values: AnnotatingElement
    */
    enum class AtType(val value: kotlin.String){
        AnnotatingElement("AnnotatingElement");
    }
}

