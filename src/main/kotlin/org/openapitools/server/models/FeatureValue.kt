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
 * @param featureWithValue 
 * @param isDefault 
 * @param isImplied 
 * @param isImpliedIncluded 
 * @param isInitial 
 * @param isLibraryElement 
 * @param memberElement 
 * @param memberElementId 
 * @param memberName 
 * @param memberShortName 
 * @param membershipOwningNamespace 
 * @param name 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedMemberElement 
 * @param ownedMemberElementId 
 * @param ownedMemberName 
 * @param ownedMemberShortName 
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
 * @param `value` 
 * @param visibility 
 */
data class FeatureValue(
    val atId: java.util.UUID,
    val atType: FeatureValue.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val featureWithValue: Identified,
    val isDefault: kotlin.Boolean,
    val isImplied: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isInitial: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val memberElement: Identified,
    val memberElementId: kotlin.String,
    val memberName: kotlin.String,
    val memberShortName: kotlin.String,
    val membershipOwningNamespace: Identified,
    val name: kotlin.String,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedMemberElement: Identified,
    val ownedMemberElementId: kotlin.String,
    val ownedMemberName: kotlin.String,
    val ownedMemberShortName: kotlin.String,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelatedElement: Identified,
    val owningRelationship: Identified,
    val qualifiedName: kotlin.String,
    val relatedElement: kotlin.collections.List<Identified>,
    val shortName: kotlin.String,
    val source: kotlin.collections.List<Identified>,
    val target: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val `value`: Identified,
    val visibility: VisibilityKind
) 
{
    /**
    * 
    * Values: FeatureValue
    */
    enum class AtType(val value: kotlin.String){
        FeatureValue("FeatureValue");
    }
}
