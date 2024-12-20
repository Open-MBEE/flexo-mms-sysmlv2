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
import org.openmbee.flexo.sysmlv2.models.AssertConstraintUsageRequestAnyOfDirection
import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param association
 * @param behavior
 * @param chainingFeature
 * @param connectorEnd
 * @param declaredName
 * @param declaredShortName
 * @param differencingType
 * @param directedFeature
 * @param direction
 * @param documentation
 * @param effectStep
 * @param elementId
 * @param endFeature
 * @param endOwningType
 * @param feature
 * @param featureMembership
 * @param featureTarget
 * @param featuringType
 * @param guardExpression
 * @param importedMembership
 * @param inheritedFeature
 * @param inheritedMembership
 * @param input
 * @param interaction
 * @param intersectingType
 * @param isAbstract
 * @param isComposite
 * @param isConjugated
 * @param isDerived
 * @param isEnd
 * @param isImplied
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isSufficient
 * @param isUnique
 * @param itemFeature
 * @param itemFlowEnd
 * @param itemType
 * @param member
 * @param membership
 * @param multiplicity
 * @param name
 * @param output
 * @param ownedAnnotation
 * @param ownedConjugator
 * @param ownedDifferencing
 * @param ownedDisjoining
 * @param ownedElement
 * @param ownedEndFeature
 * @param ownedFeature
 * @param ownedFeatureChaining
 * @param ownedFeatureInverting
 * @param ownedFeatureMembership
 * @param ownedImport
 * @param ownedIntersecting
 * @param ownedMember
 * @param ownedMembership
 * @param ownedRedefinition
 * @param ownedReferenceSubsetting
 * @param ownedRelatedElement
 * @param ownedRelationship
 * @param ownedSpecialization
 * @param ownedSubsetting
 * @param ownedTypeFeaturing
 * @param ownedTyping
 * @param ownedUnioning
 * @param owner
 * @param owningFeatureMembership
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelatedElement
 * @param owningRelationship
 * @param owningType
 * @param parameter
 * @param qualifiedName
 * @param relatedElement
 * @param relatedFeature
 * @param shortName
 * @param source
 * @param sourceFeature
 * @param sourceOutputFeature
 * @param target
 * @param targetFeature
 * @param targetInputFeature
 * @param textualRepresentation
 * @param transitionStep
 * @param triggerStep
 * @param type
 * @param unioningType
 */
@Serializable
data class SuccessionItemFlowRequestAnyOf(
    @SerialName("@type")
    val atType: SuccessionItemFlowRequestAnyOf.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val association: kotlin.collections.List<Identified>? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val chainingFeature: kotlin.collections.List<Identified>? = null,
    val connectorEnd: kotlin.collections.List<Identified>? = null,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val direction: AssertConstraintUsageRequestAnyOfDirection? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val effectStep: kotlin.collections.List<Identified>? = null,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val endOwningType: ActionUsageRequestAnyOfMultiplicity? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val featureTarget: Identified? = null,
    val featuringType: kotlin.collections.List<Identified>? = null,
    val guardExpression: kotlin.collections.List<Identified>? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val inheritedFeature: kotlin.collections.List<Identified>? = null,
    val inheritedMembership: kotlin.collections.List<Identified>? = null,
    val input: kotlin.collections.List<Identified>? = null,
    val interaction: kotlin.collections.List<Identified>? = null,
    val intersectingType: kotlin.collections.List<Identified>? = null,
    val isAbstract: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isComposite: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isConjugated: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isDerived: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isEnd: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isImplied: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isOrdered: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isPortion: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isReadOnly: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isSufficient: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isUnique: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val itemFeature: ActionUsageRequestAnyOfMultiplicity? = null,
    val itemFlowEnd: kotlin.collections.List<Identified>? = null,
    val itemType: kotlin.collections.List<Identified>? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val multiplicity: ActionUsageRequestAnyOfMultiplicity? = null,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val output: kotlin.collections.List<Identified>? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedConjugator: ActionUsageRequestAnyOfMultiplicity? = null,
    val ownedDifferencing: kotlin.collections.List<Identified>? = null,
    val ownedDisjoining: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedEndFeature: kotlin.collections.List<Identified>? = null,
    val ownedFeature: kotlin.collections.List<Identified>? = null,
    val ownedFeatureChaining: kotlin.collections.List<Identified>? = null,
    val ownedFeatureInverting: kotlin.collections.List<Identified>? = null,
    val ownedFeatureMembership: kotlin.collections.List<Identified>? = null,
    val ownedImport: kotlin.collections.List<Identified>? = null,
    val ownedIntersecting: kotlin.collections.List<Identified>? = null,
    val ownedMember: kotlin.collections.List<Identified>? = null,
    val ownedMembership: kotlin.collections.List<Identified>? = null,
    val ownedRedefinition: kotlin.collections.List<Identified>? = null,
    val ownedReferenceSubsetting: ActionUsageRequestAnyOfMultiplicity? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val ownedSpecialization: kotlin.collections.List<Identified>? = null,
    val ownedSubsetting: kotlin.collections.List<Identified>? = null,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>? = null,
    val ownedTyping: kotlin.collections.List<Identified>? = null,
    val ownedUnioning: kotlin.collections.List<Identified>? = null,
    val owner: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningFeatureMembership: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningRelatedElement: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningType: ActionUsageRequestAnyOfMultiplicity? = null,
    val parameter: kotlin.collections.List<Identified>? = null,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val relatedFeature: kotlin.collections.List<Identified>? = null,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val sourceFeature: ActionUsageRequestAnyOfMultiplicity? = null,
    val sourceOutputFeature: ActionUsageRequestAnyOfMultiplicity? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val targetFeature: kotlin.collections.List<Identified>? = null,
    val targetInputFeature: ActionUsageRequestAnyOfMultiplicity? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val transitionStep: ActionUsageRequestAnyOfMultiplicity? = null,
    val triggerStep: kotlin.collections.List<Identified>? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null
)
{
    /**
    *
    * Values: SuccessionItemFlow
    */
    enum class AtType(val value: kotlin.String){
        SuccessionItemFlow("SuccessionItemFlow");
    }
}

