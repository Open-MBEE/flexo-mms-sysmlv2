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
import org.openmbee.flexo.sysmlv2.models.AssertConstraintUsageRequestAnyOfPortionKind
import org.openmbee.flexo.sysmlv2.models.BooleanExpressionRequestAnyOf
import org.openmbee.flexo.sysmlv2.models.ConstraintUsageRequest
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.InvariantRequest

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param behavior
 * @param chainingFeature
 * @param declaredName
 * @param declaredShortName
 * @param differencingType
 * @param directedFeature
 * @param direction
 * @param documentation
 * @param elementId
 * @param endFeature
 * @param endOwningType
 * @param feature
 * @param featureMembership
 * @param featureTarget
 * @param featuringType
 * @param function
 * @param importedMembership
 * @param inheritedFeature
 * @param inheritedMembership
 * @param input
 * @param intersectingType
 * @param isAbstract
 * @param isComposite
 * @param isConjugated
 * @param isDerived
 * @param isEnd
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param isModelLevelEvaluable
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isSufficient
 * @param isUnique
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
 * @param owningRelationship
 * @param owningType
 * @param parameter
 * @param predicate
 * @param qualifiedName
 * @param result
 * @param shortName
 * @param textualRepresentation
 * @param type
 * @param unioningType
 * @param isNegated
 * @param assertedConstraint
 * @param constraintDefinition
 * @param definition
 * @param directedUsage
 * @param individualDefinition
 * @param isIndividual
 * @param isReference
 * @param isVariation
 * @param nestedAction
 * @param nestedAllocation
 * @param nestedAnalysisCase
 * @param nestedAttribute
 * @param nestedCalculation
 * @param nestedCase
 * @param nestedConcern
 * @param nestedConnection
 * @param nestedConstraint
 * @param nestedEnumeration
 * @param nestedFlow
 * @param nestedInterface
 * @param nestedItem
 * @param nestedMetadata
 * @param nestedOccurrence
 * @param nestedPart
 * @param nestedPort
 * @param nestedReference
 * @param nestedRendering
 * @param nestedRequirement
 * @param nestedState
 * @param nestedTransition
 * @param nestedUsage
 * @param nestedUseCase
 * @param nestedVerificationCase
 * @param nestedView
 * @param nestedViewpoint
 * @param occurrenceDefinition
 * @param owningDefinition
 * @param owningUsage
 * @param portionKind
 * @param usage
 * @param variant
 * @param variantMembership
 * @param actorParameter
 * @param assumedConstraint
 * @param framedConcern
 * @param reqId
 * @param requiredConstraint
 * @param requirementDefinition
 * @param satisfiedRequirement
 * @param satisfyingFeature
 * @param stakeholderParameter
 * @param subjectParameter
 * @param text
 * @param concernDefinition
 * @param viewpointDefinition
 * @param viewpointStakeholder
 */
@Serializable
data class BooleanExpressionRequest(
    @SerialName("@type")
    val atType: BooleanExpressionRequest.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val chainingFeature: kotlin.collections.List<Identified>? = null,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val direction: AssertConstraintUsageRequestAnyOfDirection? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val endOwningType: ActionUsageRequestAnyOfMultiplicity? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val featureTarget: Identified? = null,
    val featuringType: kotlin.collections.List<Identified>? = null,
    val function: Identified? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val inheritedFeature: kotlin.collections.List<Identified>? = null,
    val inheritedMembership: kotlin.collections.List<Identified>? = null,
    val input: kotlin.collections.List<Identified>? = null,
    val intersectingType: kotlin.collections.List<Identified>? = null,
    val isAbstract: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isComposite: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isConjugated: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isDerived: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isEnd: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isModelLevelEvaluable: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isOrdered: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isPortion: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isReadOnly: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isSufficient: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isUnique: ActionDefinitionRequestAnyOfIsConjugated? = null,
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
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningType: ActionUsageRequestAnyOfMultiplicity? = null,
    val parameter: kotlin.collections.List<Identified>? = null,
    val predicate: ActionUsageRequestAnyOfMultiplicity? = null,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val result: Identified? = null,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
    val isNegated: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val assertedConstraint: Identified? = null,
    val constraintDefinition: Identified? = null,
    val definition: kotlin.collections.List<Identified>? = null,
    val directedUsage: kotlin.collections.List<Identified>? = null,
    val individualDefinition: ActionUsageRequestAnyOfMultiplicity? = null,
    val isIndividual: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isReference: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isVariation: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val nestedAction: kotlin.collections.List<Identified>? = null,
    val nestedAllocation: kotlin.collections.List<Identified>? = null,
    val nestedAnalysisCase: kotlin.collections.List<Identified>? = null,
    val nestedAttribute: kotlin.collections.List<Identified>? = null,
    val nestedCalculation: kotlin.collections.List<Identified>? = null,
    val nestedCase: kotlin.collections.List<Identified>? = null,
    val nestedConcern: kotlin.collections.List<Identified>? = null,
    val nestedConnection: kotlin.collections.List<Identified>? = null,
    val nestedConstraint: kotlin.collections.List<Identified>? = null,
    val nestedEnumeration: kotlin.collections.List<Identified>? = null,
    val nestedFlow: kotlin.collections.List<Identified>? = null,
    val nestedInterface: kotlin.collections.List<Identified>? = null,
    val nestedItem: kotlin.collections.List<Identified>? = null,
    val nestedMetadata: kotlin.collections.List<Identified>? = null,
    val nestedOccurrence: kotlin.collections.List<Identified>? = null,
    val nestedPart: kotlin.collections.List<Identified>? = null,
    val nestedPort: kotlin.collections.List<Identified>? = null,
    val nestedReference: kotlin.collections.List<Identified>? = null,
    val nestedRendering: kotlin.collections.List<Identified>? = null,
    val nestedRequirement: kotlin.collections.List<Identified>? = null,
    val nestedState: kotlin.collections.List<Identified>? = null,
    val nestedTransition: kotlin.collections.List<Identified>? = null,
    val nestedUsage: kotlin.collections.List<Identified>? = null,
    val nestedUseCase: kotlin.collections.List<Identified>? = null,
    val nestedVerificationCase: kotlin.collections.List<Identified>? = null,
    val nestedView: kotlin.collections.List<Identified>? = null,
    val nestedViewpoint: kotlin.collections.List<Identified>? = null,
    val occurrenceDefinition: kotlin.collections.List<Identified>? = null,
    val owningDefinition: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningUsage: ActionUsageRequestAnyOfMultiplicity? = null,
    val portionKind: AssertConstraintUsageRequestAnyOfPortionKind? = null,
    val usage: kotlin.collections.List<Identified>? = null,
    val variant: kotlin.collections.List<Identified>? = null,
    val variantMembership: kotlin.collections.List<Identified>? = null,
    val actorParameter: kotlin.collections.List<Identified>? = null,
    val assumedConstraint: kotlin.collections.List<Identified>? = null,
    val framedConcern: kotlin.collections.List<Identified>? = null,
    val reqId: kotlin.String? = null,
    val requiredConstraint: kotlin.collections.List<Identified>? = null,
    val requirementDefinition: Identified? = null,
    val satisfiedRequirement: Identified? = null,
    val satisfyingFeature: Identified? = null,
    val stakeholderParameter: kotlin.collections.List<Identified>? = null,
    val subjectParameter: Identified? = null,
    val text: kotlin.collections.List<kotlin.String>? = null,
    val concernDefinition: Identified? = null,
    val viewpointDefinition: Identified? = null,
    val viewpointStakeholder: kotlin.collections.List<Identified>? = null
)
{
    /**
    *
    * Values: AssertConstraintUsage
    */
    enum class AtType(val value: kotlin.String){
        AssertConstraintUsage("AssertConstraintUsage");
    }
}

