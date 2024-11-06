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
 * @param aliasIds 
 * @param conjugatedPortDefinition 
 * @param conjugatedType 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param isImplied 
 * @param isImpliedIncluded 
 * @param isLibraryElement 
 * @param name 
 * @param originalPortDefinition 
 * @param originalType 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedRelatedElement 
 * @param ownedRelationship 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelatedElement 
 * @param owningRelationship 
 * @param owningType 
 * @param qualifiedName 
 * @param relatedElement 
 * @param shortName 
 * @param source 
 * @param target 
 * @param textualRepresentation 
 */
data class PortConjugation(
    val atId: java.util.UUID,
    val atType: PortConjugation.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val conjugatedPortDefinition: Identified,
    val conjugatedType: Identified,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val isImplied: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val name: kotlin.String,
    val originalPortDefinition: Identified,
    val originalType: Identified,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelatedElement: Identified,
    val owningRelationship: Identified,
    val owningType: Identified,
    val qualifiedName: kotlin.String,
    val relatedElement: kotlin.collections.List<Identified>,
    val shortName: kotlin.String,
    val source: kotlin.collections.List<Identified>,
    val target: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>
) 
{
    /**
    * 
    * Values: PortConjugation
    */
    enum class AtType(val value: kotlin.String){
        PortConjugation("PortConjugation");
    }
}

