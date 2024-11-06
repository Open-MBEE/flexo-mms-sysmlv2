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
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param general 
 * @param isImplied 
 * @param isImpliedIncluded 
 * @param isLibraryElement 
 * @param name 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedRelatedElement 
 * @param ownedRelationship 
 * @param owner 
 * @param owningClassifier 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelatedElement 
 * @param owningRelationship 
 * @param owningType 
 * @param qualifiedName 
 * @param relatedElement 
 * @param shortName 
 * @param source 
 * @param specific 
 * @param subclassifier 
 * @param superclassifier 
 * @param target 
 * @param textualRepresentation 
 */
data class Subclassification(
    val atId: java.util.UUID,
    val atType: Subclassification.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val general: Identified,
    val isImplied: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val name: kotlin.String,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: Identified,
    val owningClassifier: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelatedElement: Identified,
    val owningRelationship: Identified,
    val owningType: Identified,
    val qualifiedName: kotlin.String,
    val relatedElement: kotlin.collections.List<Identified>,
    val shortName: kotlin.String,
    val source: kotlin.collections.List<Identified>,
    val specific: Identified,
    val subclassifier: Identified,
    val superclassifier: Identified,
    val target: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>
) 
{
    /**
    * 
    * Values: Subclassification
    */
    enum class AtType(val value: kotlin.String){
        Subclassification("Subclassification");
    }
}

