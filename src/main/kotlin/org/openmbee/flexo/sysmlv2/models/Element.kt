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
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openmbee.flexo.sysmlv2.models.ActionUsageRequestAnyOfMultiplicity
import org.openmbee.flexo.sysmlv2.models.AnnotatingElement
import org.openmbee.flexo.sysmlv2.models.ElementAnyOf
import org.openmbee.flexo.sysmlv2.models.FeatureDirectionKind
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.Namespace
import org.openmbee.flexo.sysmlv2.models.PortionKind
import org.openmbee.flexo.sysmlv2.models.Relationship
import org.openmbee.flexo.sysmlv2.models.TransitionFeatureKind
import org.openmbee.flexo.sysmlv2.models.VisibilityKind

/**
 *
 * @param atId
 * @param atType
 * @param aliasIds
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param name
 * @param ownedAnnotation
 * @param ownedElement
 * @param ownedRelationship
 * @param owner
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelationship
 * @param qualifiedName
 * @param shortName
 * @param textualRepresentation
 * @param importedMembership
 * @param member
 * @param membership
 * @param ownedImport
 * @param ownedMember
 * @param ownedMembership
 * @param differencingType
 * @param directedFeature
 * @param endFeature
 * @param feature
 * @param featureMembership
 * @param inheritedFeature
 * @param inheritedMembership
 * @param input
 * @param intersectingType
 * @param isAbstract
 * @param isConjugated
 * @param isSufficient
 * @param multiplicity
 * @param output
 * @param ownedConjugator
 * @param ownedDifferencing
 * @param ownedDisjoining
 * @param ownedEndFeature
 * @param ownedFeature
 * @param ownedFeatureMembership
 * @param ownedIntersecting
 * @param ownedSpecialization
 * @param ownedUnioning
 * @param unioningType
 * @param ownedSubclassification
 * @param directedUsage
 * @param isVariation
 * @param ownedAction
 * @param ownedAllocation
 * @param ownedAnalysisCase
 * @param ownedAttribute
 * @param ownedCalculation
 * @param ownedCase
 * @param ownedConcern
 * @param ownedConnection
 * @param ownedConstraint
 * @param ownedEnumeration
 * @param ownedFlow
 * @param ownedInterface
 * @param ownedItem
 * @param ownedMetadata
 * @param ownedOccurrence
 * @param ownedPart
 * @param ownedPort
 * @param ownedReference
 * @param ownedRendering
 * @param ownedRequirement
 * @param ownedState
 * @param ownedTransition
 * @param ownedUsage
 * @param ownedUseCase
 * @param ownedVerificationCase
 * @param ownedView
 * @param ownedViewpoint
 * @param usage
 * @param variant
 * @param variantMembership
 * @param enumeratedValue
 * @param isIndividual
 * @param lifeClass
 * @param associationEnd
 * @param isImplied
 * @param ownedRelatedElement
 * @param owningRelatedElement
 * @param relatedElement
 * @param relatedType
 * @param source
 * @param sourceType
 * @param target
 * @param targetType
 * @param connectionEnd
 * @param action
 * @param parameter
 * @param step
 * @param allocation
 * @param interfaceEnd
 * @param satisfiedViewpoint
 * @param view
 * @param viewCondition
 * @param viewRendering
 * @param rendering
 * @param conjugatedPortDefinition
 * @param originalPortDefinition
 * @param ownedPortConjugator
 * @param expression
 * @param isModelLevelEvaluable
 * @param result
 * @param actorParameter
 * @param assumedConstraint
 * @param framedConcern
 * @param reqId
 * @param requiredConstraint
 * @param stakeholderParameter
 * @param subjectParameter
 * @param text
 * @param viewpointStakeholder
 * @param calculation
 * @param objectiveRequirement
 * @param verifiedRequirement
 * @param includedUseCase
 * @param resultExpression
 * @param doAction
 * @param entryAction
 * @param exitAction
 * @param isParallel
 * @param state
 * @param chainingFeature
 * @param direction
 * @param endOwningType
 * @param featureTarget
 * @param featuringType
 * @param isComposite
 * @param isDerived
 * @param isEnd
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isUnique
 * @param ownedFeatureChaining
 * @param ownedFeatureInverting
 * @param ownedRedefinition
 * @param ownedReferenceSubsetting
 * @param ownedSubsetting
 * @param ownedTypeFeaturing
 * @param ownedTyping
 * @param owningFeatureMembership
 * @param owningType
 * @param type
 * @param bound
 * @param lowerBound
 * @param upperBound
 * @param behavior
 * @param association
 * @param connectorEnd
 * @param interaction
 * @param itemFeature
 * @param itemFlowEnd
 * @param itemType
 * @param relatedFeature
 * @param sourceFeature
 * @param sourceOutputFeature
 * @param targetFeature
 * @param targetInputFeature
 * @param effectStep
 * @param guardExpression
 * @param transitionStep
 * @param triggerStep
 * @param actionDefinition
 * @param connectionDefinition
 * @param definition
 * @param flowConnectionDefinition
 * @param individualDefinition
 * @param isReference
 * @param itemDefinition
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
 * @param partDefinition
 * @param portionKind
 * @param function
 * @param referencedElement
 * @param referent
 * @param `value`
 * @param argument
 * @param `operator`
 * @param kind
 * @param predicate
 * @param isNegated
 * @param assertedConstraint
 * @param constraintDefinition
 * @param requirementDefinition
 * @param satisfiedRequirement
 * @param satisfyingFeature
 * @param concernDefinition
 * @param viewpointDefinition
 * @param calculationDefinition
 * @param caseDefinition
 * @param verificationCaseDefinition
 * @param useCaseDefinition
 * @param eventOccurrence
 * @param performedAction
 * @param useCaseIncluded
 * @param analysisCaseDefinition
 * @param effectAction
 * @param succession
 * @param triggerAction
 * @param stateDefinition
 * @param exhibitedState
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
 * @param targetArgument
 * @param valueExpression
 * @param allocationDefinition
 * @param interfaceDefinition
 * @param annotatedElement
 * @param `annotation`
 * @param metaclass
 * @param ownedAnnotatingRelationship
 * @param metadataDefinition
 * @param renderingDefinition
 * @param exposedElement
 * @param viewDefinition
 * @param portDefinition
 * @param attributeDefinition
 * @param enumerationDefinition
 * @param filterCondition
 * @param isStandard
 * @param featureOfType
 * @param owningFeatureOfType
 * @param memberElement
 * @param memberElementId
 * @param memberName
 * @param memberShortName
 * @param membershipOwningNamespace
 * @param ownedMemberElement
 * @param ownedMemberElementId
 * @param ownedMemberFeature
 * @param ownedMemberName
 * @param ownedMemberShortName
 * @param visibility
 * @param ownedMemberParameter
 * @param ownedSubjectParameter
 * @param ownedStakeholderParameter
 * @param ownedActorParameter
 * @param ownedResultExpression
 * @param referencedConstraint
 * @param referencedConcern
 * @param ownedObjectiveRequirement
 * @param transitionFeature
 * @param referencedRendering
 * @param featureChained
 * @param featureInverted
 * @param invertingFeature
 * @param owningFeature
 * @param conjugatedType
 * @param originalType
 * @param typeIntersected
 * @param general
 * @param specific
 * @param owningClassifier
 * @param subclassifier
 * @param superclassifier
 * @param subsettedFeature
 * @param subsettingFeature
 * @param redefinedFeature
 * @param redefiningFeature
 * @param referencedFeature
 * @param referencingFeature
 * @param typedFeature
 * @param disjoiningType
 * @param typeDisjoined
 * @param typeUnioned
 * @param typeDifferenced
 * @param condition
 * @param featureWithValue
 * @param isDefault
 * @param isInitial
 * @param ownedVariantUsage
 * @param importOwningNamespace
 * @param importedElement
 * @param isImportAll
 * @param isRecursive
 * @param importedNamespace
 * @param client
 * @param supplier
 * @param annotatingElement
 * @param owningAnnotatedElement
 * @param owningAnnotatingElement
 * @param body
 * @param locale
 * @param documentedElement
 * @param language
 * @param representedElement
 */
@Serializable
data class Element(
    @SerializedName("@id")
    val atId: java.util.UUID,
    @SerializedName("@type")
    val atType: Element.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val isImpliedIncluded: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val name: kotlin.String,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val owner: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelationship: Identified,
    val qualifiedName: kotlin.String,
    val shortName: kotlin.String,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val importedMembership: kotlin.collections.List<Identified>,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val ownedImport: kotlin.collections.List<Identified>,
    val ownedMember: kotlin.collections.List<Identified>,
    val ownedMembership: kotlin.collections.List<Identified>,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val endFeature: kotlin.collections.List<Identified>,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: kotlin.Boolean,
    val isConjugated: kotlin.Boolean,
    val isSufficient: kotlin.Boolean,
    val multiplicity: Identified,
    val output: kotlin.collections.List<Identified>,
    val ownedConjugator: Identified,
    val ownedDifferencing: kotlin.collections.List<Identified>,
    val ownedDisjoining: kotlin.collections.List<Identified>,
    val ownedEndFeature: kotlin.collections.List<Identified>,
    val ownedFeature: kotlin.collections.List<Identified>,
    val ownedFeatureMembership: kotlin.collections.List<Identified>,
    val ownedIntersecting: kotlin.collections.List<Identified>,
    val ownedSpecialization: kotlin.collections.List<Identified>,
    val ownedUnioning: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val ownedSubclassification: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val isVariation: kotlin.Boolean,
    val ownedAction: kotlin.collections.List<Identified>,
    val ownedAllocation: kotlin.collections.List<Identified>,
    val ownedAnalysisCase: kotlin.collections.List<Identified>,
    val ownedAttribute: kotlin.collections.List<Identified>,
    val ownedCalculation: kotlin.collections.List<Identified>,
    val ownedCase: kotlin.collections.List<Identified>,
    val ownedConcern: kotlin.collections.List<Identified>,
    val ownedConnection: kotlin.collections.List<Identified>,
    val ownedConstraint: kotlin.collections.List<Identified>,
    val ownedEnumeration: kotlin.collections.List<Identified>,
    val ownedFlow: kotlin.collections.List<Identified>,
    val ownedInterface: kotlin.collections.List<Identified>,
    val ownedItem: kotlin.collections.List<Identified>,
    val ownedMetadata: kotlin.collections.List<Identified>,
    val ownedOccurrence: kotlin.collections.List<Identified>,
    val ownedPart: kotlin.collections.List<Identified>,
    val ownedPort: kotlin.collections.List<Identified>,
    val ownedReference: kotlin.collections.List<Identified>,
    val ownedRendering: kotlin.collections.List<Identified>,
    val ownedRequirement: kotlin.collections.List<Identified>,
    val ownedState: kotlin.collections.List<Identified>,
    val ownedTransition: kotlin.collections.List<Identified>,
    val ownedUsage: kotlin.collections.List<Identified>,
    val ownedUseCase: kotlin.collections.List<Identified>,
    val ownedVerificationCase: kotlin.collections.List<Identified>,
    val ownedView: kotlin.collections.List<Identified>,
    val ownedViewpoint: kotlin.collections.List<Identified>,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>,
    val enumeratedValue: kotlin.collections.List<Identified>,
    val isIndividual: kotlin.Boolean,
    val lifeClass: Identified,
    val associationEnd: kotlin.collections.List<Identified>,
    val isImplied: kotlin.Boolean,
    val ownedRelatedElement: kotlin.collections.List<Identified>,
    val owningRelatedElement: Identified,
    val relatedElement: kotlin.collections.List<Identified>,
    val relatedType: kotlin.collections.List<Identified>,
    val source: kotlin.collections.List<Identified>,
    val sourceType: Identified,
    val target: kotlin.collections.List<Identified>,
    val targetType: kotlin.collections.List<Identified>,
    val connectionEnd: kotlin.collections.List<Identified>,
    val action: kotlin.collections.List<Identified>,
    val parameter: kotlin.collections.List<Identified>,
    val step: kotlin.collections.List<Identified>,
    val allocation: kotlin.collections.List<Identified>,
    val interfaceEnd: kotlin.collections.List<Identified>,
    val satisfiedViewpoint: kotlin.collections.List<Identified>,
    val view: kotlin.collections.List<Identified>,
    val viewCondition: kotlin.collections.List<Identified>,
    val viewRendering: Identified,
    val rendering: kotlin.collections.List<Identified>,
    val conjugatedPortDefinition: Identified,
    val originalPortDefinition: Identified,
    val ownedPortConjugator: Identified,
    val expression: kotlin.collections.List<Identified>,
    val isModelLevelEvaluable: kotlin.Boolean,
    val result: Identified,
    val actorParameter: kotlin.collections.List<Identified>,
    val assumedConstraint: kotlin.collections.List<Identified>,
    val framedConcern: kotlin.collections.List<Identified>,
    val reqId: kotlin.String,
    val requiredConstraint: kotlin.collections.List<Identified>,
    val stakeholderParameter: kotlin.collections.List<Identified>,
    val subjectParameter: Identified,
    val text: kotlin.collections.List<kotlin.String>,
    val viewpointStakeholder: kotlin.collections.List<Identified>,
    val calculation: kotlin.collections.List<Identified>,
    val objectiveRequirement: Identified,
    val verifiedRequirement: Identified,
    val includedUseCase: kotlin.collections.List<Identified>,
    val resultExpression: Identified,
    val doAction: Identified,
    val entryAction: Identified,
    val exitAction: Identified,
    val isParallel: kotlin.Boolean,
    val state: kotlin.collections.List<Identified>,
    val chainingFeature: kotlin.collections.List<Identified>,
    val direction: FeatureDirectionKind,
    val endOwningType: Identified,
    val featureTarget: Identified,
    val featuringType: kotlin.collections.List<Identified>,
    val isComposite: kotlin.Boolean,
    val isDerived: kotlin.Boolean,
    val isEnd: kotlin.Boolean,
    val isOrdered: kotlin.Boolean,
    val isPortion: kotlin.Boolean,
    val isReadOnly: kotlin.Boolean,
    val isUnique: kotlin.Boolean,
    val ownedFeatureChaining: kotlin.collections.List<Identified>,
    val ownedFeatureInverting: kotlin.collections.List<Identified>,
    val ownedRedefinition: kotlin.collections.List<Identified>,
    val ownedReferenceSubsetting: Identified,
    val ownedSubsetting: kotlin.collections.List<Identified>,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>,
    val ownedTyping: kotlin.collections.List<Identified>,
    val owningFeatureMembership: Identified,
    val owningType: Identified,
    val type: kotlin.collections.List<Identified>,
    val bound: kotlin.collections.List<Identified>,
    val lowerBound: Identified,
    val upperBound: Identified,
    val behavior: kotlin.collections.List<Identified>,
    val association: kotlin.collections.List<Identified>,
    val connectorEnd: kotlin.collections.List<Identified>,
    val interaction: kotlin.collections.List<Identified>,
    val itemFeature: ActionUsageRequestAnyOfMultiplicity,
    val itemFlowEnd: kotlin.collections.List<Identified>,
    val itemType: kotlin.collections.List<Identified>,
    val relatedFeature: kotlin.collections.List<Identified>,
    val sourceFeature: Identified,
    val sourceOutputFeature: ActionUsageRequestAnyOfMultiplicity,
    val targetFeature: kotlin.collections.List<Identified>,
    val targetInputFeature: ActionUsageRequestAnyOfMultiplicity,
    val effectStep: kotlin.collections.List<Identified>,
    val guardExpression: kotlin.collections.List<Identified>,
    val transitionStep: Identified,
    val triggerStep: kotlin.collections.List<Identified>,
    val actionDefinition: kotlin.collections.List<Identified>,
    val connectionDefinition: kotlin.collections.List<Identified>,
    val definition: kotlin.collections.List<Identified>,
    val flowConnectionDefinition: kotlin.collections.List<Identified>,
    val individualDefinition: Identified,
    val isReference: kotlin.Boolean,
    val itemDefinition: kotlin.collections.List<Identified>,
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
    val owningDefinition: Identified,
    val owningUsage: Identified,
    val partDefinition: kotlin.collections.List<Identified>,
    val portionKind: PortionKind,
    val function: Identified,
    val referencedElement: Identified,
    val referent: Identified,
    val `value`: Identified,
    val argument: kotlin.collections.List<Identified>,
    val `operator`: kotlin.String,
    val kind: TransitionFeatureKind,
    val predicate: Identified,
    val isNegated: kotlin.Boolean,
    val assertedConstraint: Identified,
    val constraintDefinition: Identified,
    val requirementDefinition: Identified,
    val satisfiedRequirement: Identified,
    val satisfyingFeature: Identified,
    val concernDefinition: Identified,
    val viewpointDefinition: Identified,
    val calculationDefinition: Identified,
    val caseDefinition: Identified,
    val verificationCaseDefinition: Identified,
    val useCaseDefinition: Identified,
    val eventOccurrence: Identified,
    val performedAction: Identified,
    val useCaseIncluded: Identified,
    val analysisCaseDefinition: Identified,
    val effectAction: kotlin.collections.List<Identified>,
    val succession: Identified,
    val triggerAction: kotlin.collections.List<Identified>,
    val stateDefinition: kotlin.collections.List<Identified>,
    val exhibitedState: Identified,
    val bodyAction: Identified,
    val untilArgument: Identified,
    val whileArgument: Identified,
    val loopVariable: Identified,
    val seqArgument: Identified,
    val payloadArgument: Identified,
    val receiverArgument: Identified,
    val senderArgument: Identified,
    val payloadParameter: Identified,
    val elseAction: Identified,
    val ifArgument: Identified,
    val thenAction: Identified,
    val targetArgument: Identified,
    val valueExpression: Identified,
    val allocationDefinition: kotlin.collections.List<Identified>,
    val interfaceDefinition: kotlin.collections.List<Identified>,
    val annotatedElement: kotlin.collections.List<Identified>,
    val `annotation`: kotlin.collections.List<Identified>,
    val metaclass: Identified,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>,
    val metadataDefinition: Identified,
    val renderingDefinition: Identified,
    val exposedElement: kotlin.collections.List<Identified>,
    val viewDefinition: Identified,
    val portDefinition: Identified,
    val attributeDefinition: kotlin.collections.List<Identified>,
    val enumerationDefinition: Identified,
    val filterCondition: kotlin.collections.List<Identified>,
    val isStandard: kotlin.Boolean,
    val featureOfType: Identified,
    val owningFeatureOfType: Identified,
    val memberElement: Identified,
    val memberElementId: kotlin.String,
    val memberName: kotlin.String,
    val memberShortName: kotlin.String,
    val membershipOwningNamespace: Identified,
    val ownedMemberElement: Identified,
    val ownedMemberElementId: kotlin.String,
    val ownedMemberFeature: Identified,
    val ownedMemberName: kotlin.String,
    val ownedMemberShortName: kotlin.String,
    val visibility: VisibilityKind,
    val ownedMemberParameter: Identified,
    val ownedSubjectParameter: Identified,
    val ownedStakeholderParameter: Identified,
    val ownedActorParameter: Identified,
    val ownedResultExpression: Identified,
    val referencedConstraint: Identified,
    val referencedConcern: Identified,
    val ownedObjectiveRequirement: Identified,
    val transitionFeature: Identified,
    val referencedRendering: Identified,
    val featureChained: Identified,
    val featureInverted: Identified,
    val invertingFeature: Identified,
    val owningFeature: Identified,
    val conjugatedType: Identified,
    val originalType: Identified,
    val typeIntersected: Identified,
    val general: Identified,
    val specific: Identified,
    val owningClassifier: Identified,
    val subclassifier: Identified,
    val superclassifier: Identified,
    val subsettedFeature: Identified,
    val subsettingFeature: Identified,
    val redefinedFeature: Identified,
    val redefiningFeature: Identified,
    val referencedFeature: Identified,
    val referencingFeature: Identified,
    val typedFeature: Identified,
    val disjoiningType: Identified,
    val typeDisjoined: Identified,
    val typeUnioned: Identified,
    val typeDifferenced: Identified,
    val condition: Identified,
    val featureWithValue: Identified,
    val isDefault: kotlin.Boolean,
    val isInitial: kotlin.Boolean,
    val ownedVariantUsage: Identified,
    val importOwningNamespace: Identified,
    val importedElement: Identified,
    val isImportAll: ActionDefinitionRequestAnyOfIsConjugated,
    val isRecursive: ActionDefinitionRequestAnyOfIsConjugated,
    val importedNamespace: Identified,
    val client: kotlin.collections.List<Identified>,
    val supplier: kotlin.collections.List<Identified>,
    val annotatingElement: Identified,
    val owningAnnotatedElement: Identified,
    val owningAnnotatingElement: Identified,
    val body: kotlin.String,
    val locale: kotlin.String,
    val documentedElement: Identified,
    val language: kotlin.String,
    val representedElement: Identified
)
{
    /**
    *
    * Values: TextualRepresentation
    */
    enum class AtType(val value: kotlin.String){
        TextualRepresentation("TextualRepresentation");
    }
}

