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
package org.openmbee.flexo.sysmlv2.models

import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfDeclaredShortName
import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openmbee.flexo.sysmlv2.models.ActionUsageRequestAnyOfMultiplicity
import org.openmbee.flexo.sysmlv2.models.AssertConstraintUsageAnyOfDirection
import org.openmbee.flexo.sysmlv2.models.AssertConstraintUsageAnyOfPortionKind
import org.openmbee.flexo.sysmlv2.models.BooleanExpressionAnyOf
import org.openmbee.flexo.sysmlv2.models.ConstraintUsage
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.Invariant

/**
 *
 * @param atId
 * @param atType
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
data class BooleanExpression(
    val atId: java.util.UUID,
    val atType: BooleanExpression.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val behavior: kotlin.collections.List<Identified>,
    val chainingFeature: kotlin.collections.List<Identified>,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val direction: AssertConstraintUsageAnyOfDirection,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val endFeature: kotlin.collections.List<Identified>,
    val endOwningType: ActionUsageRequestAnyOfMultiplicity,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val featureTarget: Identified,
    val featuringType: kotlin.collections.List<Identified>,
    val function: ActionUsageRequestAnyOfMultiplicity,
    val importedMembership: kotlin.collections.List<Identified>,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: ActionDefinitionRequestAnyOfIsConjugated,
    val isComposite: ActionDefinitionRequestAnyOfIsConjugated,
    val isConjugated: ActionDefinitionRequestAnyOfIsConjugated,
    val isDerived: ActionDefinitionRequestAnyOfIsConjugated,
    val isEnd: ActionDefinitionRequestAnyOfIsConjugated,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
    val isModelLevelEvaluable: ActionDefinitionRequestAnyOfIsConjugated,
    val isOrdered: ActionDefinitionRequestAnyOfIsConjugated,
    val isPortion: ActionDefinitionRequestAnyOfIsConjugated,
    val isReadOnly: ActionDefinitionRequestAnyOfIsConjugated,
    val isSufficient: ActionDefinitionRequestAnyOfIsConjugated,
    val isUnique: ActionDefinitionRequestAnyOfIsConjugated,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val multiplicity: ActionUsageRequestAnyOfMultiplicity,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName,
    val output: kotlin.collections.List<Identified>,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedConjugator: ActionUsageRequestAnyOfMultiplicity,
    val ownedDifferencing: kotlin.collections.List<Identified>,
    val ownedDisjoining: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedEndFeature: kotlin.collections.List<Identified>,
    val ownedFeature: kotlin.collections.List<Identified>,
    val ownedFeatureChaining: kotlin.collections.List<Identified>,
    val ownedFeatureInverting: kotlin.collections.List<Identified>,
    val ownedFeatureMembership: kotlin.collections.List<Identified>,
    val ownedImport: kotlin.collections.List<Identified>,
    val ownedIntersecting: kotlin.collections.List<Identified>,
    val ownedMember: kotlin.collections.List<Identified>,
    val ownedMembership: kotlin.collections.List<Identified>,
    val ownedRedefinition: kotlin.collections.List<Identified>,
    val ownedReferenceSubsetting: ActionUsageRequestAnyOfMultiplicity,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val ownedSpecialization: kotlin.collections.List<Identified>,
    val ownedSubsetting: kotlin.collections.List<Identified>,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>,
    val ownedTyping: kotlin.collections.List<Identified>,
    val ownedUnioning: kotlin.collections.List<Identified>,
    val owner: ActionUsageRequestAnyOfMultiplicity,
    val owningFeatureMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val owningType: ActionUsageRequestAnyOfMultiplicity,
    val parameter: kotlin.collections.List<Identified>,
    val predicate: ActionUsageRequestAnyOfMultiplicity,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val result: Identified,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val type: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val isNegated: ActionDefinitionRequestAnyOfIsConjugated,
    val assertedConstraint: Identified,
    val constraintDefinition: ActionUsageRequestAnyOfMultiplicity,
    val definition: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val individualDefinition: ActionUsageRequestAnyOfMultiplicity,
    val isIndividual: ActionDefinitionRequestAnyOfIsConjugated,
    val isReference: ActionDefinitionRequestAnyOfIsConjugated,
    val isVariation: ActionDefinitionRequestAnyOfIsConjugated,
    val nestedAction: kotlin.collections.List<Identified>,
    val nestedAllocation: kotlin.collections.List<Identified>,
    val nestedAnalysisCase: kotlin.collections.List<Identified>,
    val nestedAttribute: kotlin.collections.List<Identified>,
    val nestedCalculation: kotlin.collections.List<Identified>,
    val nestedCase: kotlin.collections.List<Identified>,
    val nestedConcern: kotlin.collections.List<Identified>,
    val nestedConnection: kotlin.collections.List<Identified>,
    val nestedConstraint: kotlin.collections.List<Identified>,
    val nestedEnumeration: kotlin.collections.List<Identified>,
    val nestedFlow: kotlin.collections.List<Identified>,
    val nestedInterface: kotlin.collections.List<Identified>,
    val nestedItem: kotlin.collections.List<Identified>,
    val nestedMetadata: kotlin.collections.List<Identified>,
    val nestedOccurrence: kotlin.collections.List<Identified>,
    val nestedPart: kotlin.collections.List<Identified>,
    val nestedPort: kotlin.collections.List<Identified>,
    val nestedReference: kotlin.collections.List<Identified>,
    val nestedRendering: kotlin.collections.List<Identified>,
    val nestedRequirement: kotlin.collections.List<Identified>,
    val nestedState: kotlin.collections.List<Identified>,
    val nestedTransition: kotlin.collections.List<Identified>,
    val nestedUsage: kotlin.collections.List<Identified>,
    val nestedUseCase: kotlin.collections.List<Identified>,
    val nestedVerificationCase: kotlin.collections.List<Identified>,
    val nestedView: kotlin.collections.List<Identified>,
    val nestedViewpoint: kotlin.collections.List<Identified>,
    val occurrenceDefinition: kotlin.collections.List<Identified>,
    val owningDefinition: ActionUsageRequestAnyOfMultiplicity,
    val owningUsage: ActionUsageRequestAnyOfMultiplicity,
    val portionKind: AssertConstraintUsageAnyOfPortionKind,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>,
    val actorParameter: kotlin.collections.List<Identified>,
    val assumedConstraint: kotlin.collections.List<Identified>,
    val framedConcern: kotlin.collections.List<Identified>,
    val reqId: kotlin.String,
    val requiredConstraint: kotlin.collections.List<Identified>,
    val requirementDefinition: Identified,
    val satisfiedRequirement: Identified,
    val satisfyingFeature: Identified,
    val stakeholderParameter: kotlin.collections.List<Identified>,
    val subjectParameter: Identified,
    val text: kotlin.collections.List<kotlin.String>,
    val concernDefinition: Identified,
    val viewpointDefinition: Identified,
    val viewpointStakeholder: kotlin.collections.List<Identified>
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
