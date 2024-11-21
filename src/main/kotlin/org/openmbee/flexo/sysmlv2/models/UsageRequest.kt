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

import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openmbee.flexo.sysmlv2.models.ActionUsageRequestAnyOfMultiplicity
import org.openmbee.flexo.sysmlv2.models.AttributeUsageRequest
import org.openmbee.flexo.sysmlv2.models.ConnectorAsUsageRequest
import org.openmbee.flexo.sysmlv2.models.FeatureDirectionKindRequest
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.OccurrenceUsageRequest
import org.openmbee.flexo.sysmlv2.models.PortionKindRequest
import org.openmbee.flexo.sysmlv2.models.ReferenceUsageRequest
import org.openmbee.flexo.sysmlv2.models.UsageRequestAnyOf

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param chainingFeature
 * @param declaredName
 * @param declaredShortName
 * @param definition
 * @param differencingType
 * @param directedFeature
 * @param directedUsage
 * @param direction
 * @param documentation
 * @param elementId
 * @param endFeature
 * @param endOwningType
 * @param feature
 * @param featureMembership
 * @param featureTarget
 * @param featuringType
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
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isReference
 * @param isSufficient
 * @param isUnique
 * @param isVariation
 * @param member
 * @param membership
 * @param multiplicity
 * @param name
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
 * @param owningDefinition
 * @param owningFeatureMembership
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelationship
 * @param owningType
 * @param owningUsage
 * @param qualifiedName
 * @param shortName
 * @param textualRepresentation
 * @param type
 * @param unioningType
 * @param usage
 * @param variant
 * @param variantMembership
 * @param individualDefinition
 * @param isIndividual
 * @param occurrenceDefinition
 * @param portionKind
 * @param itemDefinition
 * @param annotatedElement
 * @param `annotation`
 * @param metaclass
 * @param metadataDefinition
 * @param ownedAnnotatingRelationship
 * @param partDefinition
 * @param association
 * @param connectionDefinition
 * @param connectorEnd
 * @param isImplied
 * @param ownedRelatedElement
 * @param owningRelatedElement
 * @param relatedElement
 * @param relatedFeature
 * @param source
 * @param sourceFeature
 * @param target
 * @param targetFeature
 * @param actionDefinition
 * @param behavior
 * @param flowConnectionDefinition
 * @param interaction
 * @param itemFeature
 * @param itemFlowEnd
 * @param itemType
 * @param parameter
 * @param sourceOutputFeature
 * @param targetInputFeature
 * @param effectStep
 * @param guardExpression
 * @param transitionStep
 * @param triggerStep
 * @param allocationDefinition
 * @param interfaceDefinition
 * @param renderingDefinition
 * @param exposedElement
 * @param satisfiedViewpoint
 * @param viewCondition
 * @param viewDefinition
 * @param viewRendering
 * @param eventOccurrence
 * @param performedAction
 * @param doAction
 * @param entryAction
 * @param exhibitedState
 * @param exitAction
 * @param isParallel
 * @param stateDefinition
 * @param actorParameter
 * @param calculationDefinition
 * @param caseDefinition
 * @param function
 * @param includedUseCase
 * @param isModelLevelEvaluable
 * @param objectiveRequirement
 * @param result
 * @param subjectParameter
 * @param useCaseDefinition
 * @param useCaseIncluded
 * @param portDefinition
 * @param constraintDefinition
 * @param predicate
 * @param assumedConstraint
 * @param framedConcern
 * @param reqId
 * @param requiredConstraint
 * @param requirementDefinition
 * @param stakeholderParameter
 * @param text
 * @param concernDefinition
 * @param assertedConstraint
 * @param isNegated
 * @param satisfiedRequirement
 * @param satisfyingFeature
 * @param viewpointDefinition
 * @param viewpointStakeholder
 * @param verificationCaseDefinition
 * @param verifiedRequirement
 * @param analysisCaseDefinition
 * @param resultExpression
 * @param effectAction
 * @param succession
 * @param triggerAction
 * @param bodyAction
 * @param untilArgument
 * @param whileArgument
 * @param loopVariable
 * @param seqArgument
 * @param payloadArgument
 * @param receiverArgument
 * @param senderArgument
 * @param payloadParameter
 * @param elseAction
 * @param ifArgument
 * @param thenAction
 * @param referent
 * @param targetArgument
 * @param valueExpression
 * @param attributeDefinition
 * @param enumerationDefinition
 */
@Serializable
data class UsageRequest(
    @SerialName("@type")
    val atType: UsageRequest.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val chainingFeature: kotlin.collections.List<Identified>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val definition: kotlin.collections.List<Identified>? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val directedUsage: kotlin.collections.List<Identified>? = null,
    val direction: FeatureDirectionKindRequest? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val endOwningType: Identified? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val featureTarget: Identified? = null,
    val featuringType: kotlin.collections.List<Identified>? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val inheritedFeature: kotlin.collections.List<Identified>? = null,
    val inheritedMembership: kotlin.collections.List<Identified>? = null,
    val input: kotlin.collections.List<Identified>? = null,
    val intersectingType: kotlin.collections.List<Identified>? = null,
    val isAbstract: kotlin.Boolean? = null,
    val isComposite: kotlin.Boolean? = null,
    val isConjugated: kotlin.Boolean? = null,
    val isDerived: kotlin.Boolean? = null,
    val isEnd: kotlin.Boolean? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val isOrdered: kotlin.Boolean? = null,
    val isPortion: kotlin.Boolean? = null,
    val isReadOnly: kotlin.Boolean? = null,
    val isReference: kotlin.Boolean? = null,
    val isSufficient: kotlin.Boolean? = null,
    val isUnique: kotlin.Boolean? = null,
    val isVariation: kotlin.Boolean? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val multiplicity: Identified? = null,
    val name: kotlin.String? = null,
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
    val output: kotlin.collections.List<Identified>? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedConjugator: Identified? = null,
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
    val ownedReferenceSubsetting: Identified? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val ownedSpecialization: kotlin.collections.List<Identified>? = null,
    val ownedSubsetting: kotlin.collections.List<Identified>? = null,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>? = null,
    val ownedTyping: kotlin.collections.List<Identified>? = null,
    val ownedUnioning: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningDefinition: Identified? = null,
    val owningFeatureMembership: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelationship: Identified? = null,
    val owningType: Identified? = null,
    val owningUsage: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val shortName: kotlin.String? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
    val usage: kotlin.collections.List<Identified>? = null,
    val variant: kotlin.collections.List<Identified>? = null,
    val variantMembership: kotlin.collections.List<Identified>? = null,
    val individualDefinition: Identified? = null,
    val isIndividual: kotlin.Boolean? = null,
    val occurrenceDefinition: kotlin.collections.List<Identified>? = null,
    val portionKind: PortionKindRequest? = null,
    val itemDefinition: kotlin.collections.List<Identified>? = null,
    val annotatedElement: kotlin.collections.List<Identified>? = null,
    val `annotation`: kotlin.collections.List<Identified>? = null,
    val metaclass: Identified? = null,
    val metadataDefinition: Identified? = null,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>? = null,
    val partDefinition: kotlin.collections.List<Identified>? = null,
    val association: kotlin.collections.List<Identified>? = null,
    val connectionDefinition: kotlin.collections.List<Identified>? = null,
    val connectorEnd: kotlin.collections.List<Identified>? = null,
    val isImplied: kotlin.Boolean? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val owningRelatedElement: Identified? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val relatedFeature: kotlin.collections.List<Identified>? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val sourceFeature: Identified? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val targetFeature: kotlin.collections.List<Identified>? = null,
    val actionDefinition: kotlin.collections.List<Identified>? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val flowConnectionDefinition: kotlin.collections.List<Identified>? = null,
    val interaction: kotlin.collections.List<Identified>? = null,
    val itemFeature: Identified? = null,
    val itemFlowEnd: kotlin.collections.List<Identified>? = null,
    val itemType: kotlin.collections.List<Identified>? = null,
    val parameter: kotlin.collections.List<Identified>? = null,
    val sourceOutputFeature: Identified? = null,
    val targetInputFeature: Identified? = null,
    val effectStep: kotlin.collections.List<Identified>? = null,
    val guardExpression: kotlin.collections.List<Identified>? = null,
    val transitionStep: Identified? = null,
    val triggerStep: kotlin.collections.List<Identified>? = null,
    val allocationDefinition: kotlin.collections.List<Identified>? = null,
    val interfaceDefinition: kotlin.collections.List<Identified>? = null,
    val renderingDefinition: Identified? = null,
    val exposedElement: kotlin.collections.List<Identified>? = null,
    val satisfiedViewpoint: kotlin.collections.List<Identified>? = null,
    val viewCondition: kotlin.collections.List<Identified>? = null,
    val viewDefinition: Identified? = null,
    val viewRendering: Identified? = null,
    val eventOccurrence: Identified? = null,
    val performedAction: Identified? = null,
    val doAction: Identified? = null,
    val entryAction: ActionUsageRequestAnyOfMultiplicity? = null,
    val exhibitedState: Identified? = null,
    val exitAction: ActionUsageRequestAnyOfMultiplicity? = null,
    val isParallel: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val stateDefinition: kotlin.collections.List<Identified>? = null,
    val actorParameter: kotlin.collections.List<Identified>? = null,
    val calculationDefinition: Identified? = null,
    val caseDefinition: Identified? = null,
    val function: Identified? = null,
    val includedUseCase: kotlin.collections.List<Identified>? = null,
    val isModelLevelEvaluable: kotlin.Boolean? = null,
    val objectiveRequirement: Identified? = null,
    val result: Identified? = null,
    val subjectParameter: Identified? = null,
    val useCaseDefinition: Identified? = null,
    val useCaseIncluded: Identified? = null,
    val portDefinition: kotlin.collections.List<Identified>? = null,
    val constraintDefinition: Identified? = null,
    val predicate: ActionUsageRequestAnyOfMultiplicity? = null,
    val assumedConstraint: kotlin.collections.List<Identified>? = null,
    val framedConcern: kotlin.collections.List<Identified>? = null,
    val reqId: kotlin.String? = null,
    val requiredConstraint: kotlin.collections.List<Identified>? = null,
    val requirementDefinition: Identified? = null,
    val stakeholderParameter: kotlin.collections.List<Identified>? = null,
    val text: kotlin.collections.List<kotlin.String>? = null,
    val concernDefinition: Identified? = null,
    val assertedConstraint: Identified? = null,
    val isNegated: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val satisfiedRequirement: Identified? = null,
    val satisfyingFeature: Identified? = null,
    val viewpointDefinition: Identified? = null,
    val viewpointStakeholder: kotlin.collections.List<Identified>? = null,
    val verificationCaseDefinition: Identified? = null,
    val verifiedRequirement: kotlin.collections.List<Identified>? = null,
    val analysisCaseDefinition: Identified? = null,
    val resultExpression: Identified? = null,
    val effectAction: kotlin.collections.List<Identified>? = null,
    val succession: Identified? = null,
    val triggerAction: kotlin.collections.List<Identified>? = null,
    val bodyAction: Identified? = null,
    val untilArgument: Identified? = null,
    val whileArgument: Identified? = null,
    val loopVariable: Identified? = null,
    val seqArgument: Identified? = null,
    val payloadArgument: Identified? = null,
    val receiverArgument: Identified? = null,
    val senderArgument: Identified? = null,
    val payloadParameter: Identified? = null,
    val elseAction: Identified? = null,
    val ifArgument: Identified? = null,
    val thenAction: Identified? = null,
    val referent: Identified? = null,
    val targetArgument: Identified? = null,
    val valueExpression: Identified? = null,
    val attributeDefinition: kotlin.collections.List<Identified>? = null,
    val enumerationDefinition: Identified? = null
)
{
    /**
    *
    * Values: EnumerationUsage
    */
    enum class AtType(val value: kotlin.String){
        EnumerationUsage("EnumerationUsage");
    }
}

