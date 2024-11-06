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
import org.openapitools.server.models.VisibilityKindRequest

/**
 * 
 * @param atType 
 * @param atId 
 * @param aliasIds 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param feature 
 * @param isImplied 
 * @param isImpliedIncluded 
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
 * @param ownedMemberFeature 
 * @param ownedMemberName 
 * @param ownedMemberShortName 
 * @param ownedRelatedElement 
 * @param ownedRelationship 
 * @param ownedRendering 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelatedElement 
 * @param owningRelationship 
 * @param owningType 
 * @param qualifiedName 
 * @param referencedRendering 
 * @param relatedElement 
 * @param shortName 
 * @param source 
 * @param target 
 * @param textualRepresentation 
 * @param type 
 * @param visibility 
 */
data class ViewRenderingMembershipRequest(
    val atType: ViewRenderingMembershipRequest.AtType,
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val feature: Identified? = null,
    val isImplied: kotlin.Boolean? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val memberElement: Identified? = null,
    val memberElementId: kotlin.String? = null,
    val memberName: kotlin.String? = null,
    val memberShortName: kotlin.String? = null,
    val membershipOwningNamespace: Identified? = null,
    val name: kotlin.String? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedMemberElement: Identified? = null,
    val ownedMemberElementId: kotlin.String? = null,
    val ownedMemberFeature: Identified? = null,
    val ownedMemberName: kotlin.String? = null,
    val ownedMemberShortName: kotlin.String? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val ownedRendering: Identified? = null,
    val owner: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelatedElement: Identified? = null,
    val owningRelationship: Identified? = null,
    val owningType: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val referencedRendering: Identified? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val shortName: kotlin.String? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val type: Identified? = null,
    val visibility: VisibilityKindRequest? = null
) 
{
    /**
    * 
    * Values: ViewRenderingMembership
    */
    enum class AtType(val value: kotlin.String){
        ViewRenderingMembership("ViewRenderingMembership");
    }
}

