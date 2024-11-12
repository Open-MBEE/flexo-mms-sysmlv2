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
@file:UseSerializers(UUIDSerializer::class)

package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.infrastructure.UUIDSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.VisibilityKindRequest

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param condition
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
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
 * @param visibility
 */
@Serializable
data class ElementFilterMembershipRequest(
    @SerialName("@type")
    val atType: ElementFilterMembershipRequest.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val condition: Identified? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
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
    val ownedMemberName: kotlin.String? = null,
    val ownedMemberShortName: kotlin.String? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelatedElement: Identified? = null,
    val owningRelationship: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val shortName: kotlin.String? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val visibility: VisibilityKindRequest? = null
)
{
    /**
    *
    * Values: ElementFilterMembership
    */
    enum class AtType(val value: kotlin.String){
        ElementFilterMembership("ElementFilterMembership");
    }
}

