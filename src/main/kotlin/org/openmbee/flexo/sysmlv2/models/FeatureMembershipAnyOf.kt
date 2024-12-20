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

import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfDeclaredShortName
import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openmbee.flexo.sysmlv2.models.ActionUsageRequestAnyOfMultiplicity
import org.openmbee.flexo.sysmlv2.models.FeatureMembershipAnyOfVisibility
import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atId
 * @param atType
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
 * @param type
 * @param visibility
 */
@Serializable
data class FeatureMembershipAnyOf(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: FeatureMembershipAnyOf.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val feature: Identified,
    val isImplied: ActionDefinitionRequestAnyOfIsConjugated,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
    val memberElement: Identified,
    val memberElementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val memberName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val memberShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val membershipOwningNamespace: Identified,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedMemberElement: Identified,
    val ownedMemberElementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedMemberFeature: Identified,
    val ownedMemberName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedMemberShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: ActionUsageRequestAnyOfMultiplicity,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity,
    val owningRelatedElement: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val owningType: Identified,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val relatedElement: kotlin.collections.List<Identified>,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val source: kotlin.collections.List<Identified>,
    val target: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val type: Identified,
    val visibility: FeatureMembershipAnyOfVisibility
)
{
    /**
    *
    * Values: FeatureMembership
    */
    enum class AtType(val value: kotlin.String){
        FeatureMembership("FeatureMembership");
    }
}

