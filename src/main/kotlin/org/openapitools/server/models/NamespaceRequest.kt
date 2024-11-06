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

import org.openapitools.server.models.ActionUsageRequestAnyOfMultiplicity
import org.openapitools.server.models.FeatureDirectionKindRequest
import org.openapitools.server.models.Identified
import org.openapitools.server.models.NamespaceRequestAnyOf
import org.openapitools.server.models.PackageRequest
import org.openapitools.server.models.PortionKindRequest
import org.openapitools.server.models.TriggerKindRequest
import org.openapitools.server.models.TypeRequest

/**
 * 
 * @param atType 
 * @param atId 
 * @param aliasIds 
 * @param declaredName 
 * @param declaredShortName 
 * @param documentation 
 * @param elementId 
 * @param importedMembership 
 * @param isImpliedIncluded 
 * @param isLibraryElement 
 * @param member 
 * @param membership 
 * @param name 
 * @param ownedAnnotation 
 * @param ownedElement 
 * @param ownedImport 
 * @param ownedMember 
 * @param ownedMembership 
 * @param ownedRelationship 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelationship 
 * @param qualifiedName 
 * @param shortName 
 * @param textualRepresentation 
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
 */
data class NamespaceRequest(
    val atType: NamespaceRequest.AtType,
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val name: kotlin.String? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedImport: kotlin.collections.List<Identified>? = null,
    val ownedMember: kotlin.collections.List<Identified>? = null,
    val ownedMembership: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelationship: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val shortName: kotlin.String? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val inheritedFeature: kotlin.collections.List<Identified>? = null,
    val inheritedMembership: kotlin.collections.List<Identified>? = null,
    val input: kotlin.collections.List<Identified>? = null,
    val intersectingType: kotlin.collections.List<Identified>? = null,
    val isAbstract: kotlin.Boolean? = null,
    val isConjugated: kotlin.Boolean? = null,
    val isSufficient: kotlin.Boolean? = null,
    val multiplicity: Identified? = null,
    val output: kotlin.collections.List<Identified>? = null,
    val ownedConjugator: Identified? = null,
    val ownedDifferencing: kotlin.collections.List<Identified>? = null,
    val ownedDisjoining: kotlin.collections.List<Identified>? = null,
    val ownedEndFeature: kotlin.collections.List<Identified>? = null,
    val ownedFeature: kotlin.collections.List<Identified>? = null,
    val ownedFeatureMembership: kotlin.collections.List<Identified>? = null,
    val ownedIntersecting: kotlin.collections.List<Identified>? = null,
    val ownedSpecialization: kotlin.collections.List<Identified>? = null,
    val ownedUnioning: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
    val ownedSubclassification: kotlin.collections.List<Identified>? = null,
    val directedUsage: kotlin.collections.List<Identified>? = null,
    val isVariation: kotlin.Boolean? = null,
    val ownedAction: kotlin.collections.List<Identified>? = null,
    val ownedAllocation: kotlin.collections.List<Identified>? = null,
    val ownedAnalysisCase: kotlin.collections.List<Identified>? = null,
    val ownedAttribute: kotlin.collections.List<Identified>? = null,
    val ownedCalculation: kotlin.collections.List<Identified>? = null,
    val ownedCase: kotlin.collections.List<Identified>? = null,
    val ownedConcern: kotlin.collections.List<Identified>? = null,
    val ownedConnection: kotlin.collections.List<Identified>? = null,
    val ownedConstraint: kotlin.collections.List<Identified>? = null,
    val ownedEnumeration: kotlin.collections.List<Identified>? = null,
    val ownedFlow: kotlin.collections.List<Identified>? = null,
    val ownedInterface: kotlin.collections.List<Identified>? = null,
    val ownedItem: kotlin.collections.List<Identified>? = null,
    val ownedMetadata: kotlin.collections.List<Identified>? = null,
    val ownedOccurrence: kotlin.collections.List<Identified>? = null,
    val ownedPart: kotlin.collections.List<Identified>? = null,
    val ownedPort: kotlin.collections.List<Identified>? = null,
    val ownedReference: kotlin.collections.List<Identified>? = null,
    val ownedRendering: kotlin.collections.List<Identified>? = null,
    val ownedRequirement: kotlin.collections.List<Identified>? = null,
    val ownedState: kotlin.collections.List<Identified>? = null,
    val ownedTransition: kotlin.collections.List<Identified>? = null,
    val ownedUsage: kotlin.collections.List<Identified>? = null,
    val ownedUseCase: kotlin.collections.List<Identified>? = null,
    val ownedVerificationCase: kotlin.collections.List<Identified>? = null,
    val ownedView: kotlin.collections.List<Identified>? = null,
    val ownedViewpoint: kotlin.collections.List<Identified>? = null,
    val usage: kotlin.collections.List<Identified>? = null,
    val variant: kotlin.collections.List<Identified>? = null,
    val variantMembership: kotlin.collections.List<Identified>? = null,
    val enumeratedValue: kotlin.collections.List<Identified>? = null,
    val isIndividual: kotlin.Boolean? = null,
    val lifeClass: ActionUsageRequestAnyOfMultiplicity? = null,
    val associationEnd: kotlin.collections.List<Identified>? = null,
    val isImplied: kotlin.Boolean? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val owningRelatedElement: Identified? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val relatedType: kotlin.collections.List<Identified>? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val sourceType: ActionUsageRequestAnyOfMultiplicity? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val targetType: kotlin.collections.List<Identified>? = null,
    val connectionEnd: kotlin.collections.List<Identified>? = null,
    val action: kotlin.collections.List<Identified>? = null,
    val parameter: kotlin.collections.List<Identified>? = null,
    val step: kotlin.collections.List<Identified>? = null,
    val allocation: kotlin.collections.List<Identified>? = null,
    val interfaceEnd: kotlin.collections.List<Identified>? = null,
    val satisfiedViewpoint: kotlin.collections.List<Identified>? = null,
    val view: kotlin.collections.List<Identified>? = null,
    val viewCondition: kotlin.collections.List<Identified>? = null,
    val viewRendering: Identified? = null,
    val rendering: kotlin.collections.List<Identified>? = null,
    val conjugatedPortDefinition: Identified? = null,
    val originalPortDefinition: Identified? = null,
    val ownedPortConjugator: Identified? = null,
    val expression: kotlin.collections.List<Identified>? = null,
    val isModelLevelEvaluable: kotlin.Boolean? = null,
    val result: Identified? = null,
    val actorParameter: kotlin.collections.List<Identified>? = null,
    val assumedConstraint: kotlin.collections.List<Identified>? = null,
    val framedConcern: kotlin.collections.List<Identified>? = null,
    val reqId: kotlin.String? = null,
    val requiredConstraint: kotlin.collections.List<Identified>? = null,
    val stakeholderParameter: kotlin.collections.List<Identified>? = null,
    val subjectParameter: Identified? = null,
    val text: kotlin.collections.List<kotlin.String>? = null,
    val viewpointStakeholder: kotlin.collections.List<Identified>? = null,
    val calculation: kotlin.collections.List<Identified>? = null,
    val objectiveRequirement: Identified? = null,
    val verifiedRequirement: kotlin.collections.List<Identified>? = null,
    val includedUseCase: kotlin.collections.List<Identified>? = null,
    val resultExpression: Identified? = null,
    val doAction: Identified? = null,
    val entryAction: Identified? = null,
    val exitAction: Identified? = null,
    val isParallel: kotlin.Boolean? = null,
    val state: kotlin.collections.List<Identified>? = null,
    val chainingFeature: kotlin.collections.List<Identified>? = null,
    val direction: FeatureDirectionKindRequest? = null,
    val endOwningType: Identified? = null,
    val featureTarget: Identified? = null,
    val featuringType: kotlin.collections.List<Identified>? = null,
    val isComposite: kotlin.Boolean? = null,
    val isDerived: kotlin.Boolean? = null,
    val isEnd: kotlin.Boolean? = null,
    val isOrdered: kotlin.Boolean? = null,
    val isPortion: kotlin.Boolean? = null,
    val isReadOnly: kotlin.Boolean? = null,
    val isUnique: kotlin.Boolean? = null,
    val ownedFeatureChaining: kotlin.collections.List<Identified>? = null,
    val ownedFeatureInverting: kotlin.collections.List<Identified>? = null,
    val ownedRedefinition: kotlin.collections.List<Identified>? = null,
    val ownedReferenceSubsetting: Identified? = null,
    val ownedSubsetting: kotlin.collections.List<Identified>? = null,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>? = null,
    val ownedTyping: kotlin.collections.List<Identified>? = null,
    val owningFeatureMembership: Identified? = null,
    val owningType: Identified? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val bound: kotlin.collections.List<Identified>? = null,
    val lowerBound: Identified? = null,
    val upperBound: Identified? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val association: kotlin.collections.List<Identified>? = null,
    val connectorEnd: kotlin.collections.List<Identified>? = null,
    val interaction: kotlin.collections.List<Identified>? = null,
    val itemFeature: Identified? = null,
    val itemFlowEnd: kotlin.collections.List<Identified>? = null,
    val itemType: kotlin.collections.List<Identified>? = null,
    val relatedFeature: kotlin.collections.List<Identified>? = null,
    val sourceFeature: Identified? = null,
    val sourceOutputFeature: ActionUsageRequestAnyOfMultiplicity? = null,
    val targetFeature: kotlin.collections.List<Identified>? = null,
    val targetInputFeature: ActionUsageRequestAnyOfMultiplicity? = null,
    val effectStep: kotlin.collections.List<Identified>? = null,
    val guardExpression: kotlin.collections.List<Identified>? = null,
    val transitionStep: Identified? = null,
    val triggerStep: kotlin.collections.List<Identified>? = null,
    val actionDefinition: kotlin.collections.List<Identified>? = null,
    val connectionDefinition: kotlin.collections.List<Identified>? = null,
    val definition: kotlin.collections.List<Identified>? = null,
    val flowConnectionDefinition: kotlin.collections.List<Identified>? = null,
    val individualDefinition: Identified? = null,
    val isReference: kotlin.Boolean? = null,
    val itemDefinition: kotlin.collections.List<Identified>? = null,
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
    val owningDefinition: Identified? = null,
    val owningUsage: Identified? = null,
    val partDefinition: kotlin.collections.List<Identified>? = null,
    val portionKind: PortionKindRequest? = null,
    val function: Identified? = null,
    val referencedElement: Identified? = null,
    val referent: Identified? = null,
    val `value`: kotlin.Boolean? = null,
    val argument: kotlin.collections.List<Identified>? = null,
    val `operator`: kotlin.String? = null,
    val kind: TriggerKindRequest? = null,
    val predicate: Identified? = null,
    val isNegated: kotlin.Boolean? = null,
    val assertedConstraint: Identified? = null,
    val constraintDefinition: Identified? = null,
    val requirementDefinition: Identified? = null,
    val satisfiedRequirement: Identified? = null,
    val satisfyingFeature: Identified? = null,
    val concernDefinition: Identified? = null,
    val viewpointDefinition: Identified? = null,
    val calculationDefinition: Identified? = null,
    val caseDefinition: Identified? = null,
    val verificationCaseDefinition: Identified? = null,
    val useCaseDefinition: Identified? = null,
    val eventOccurrence: Identified? = null,
    val performedAction: Identified? = null,
    val useCaseIncluded: Identified? = null,
    val analysisCaseDefinition: Identified? = null,
    val effectAction: kotlin.collections.List<Identified>? = null,
    val succession: Identified? = null,
    val triggerAction: kotlin.collections.List<Identified>? = null,
    val stateDefinition: kotlin.collections.List<Identified>? = null,
    val exhibitedState: Identified? = null,
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
    val targetArgument: Identified? = null,
    val valueExpression: Identified? = null,
    val allocationDefinition: kotlin.collections.List<Identified>? = null,
    val interfaceDefinition: kotlin.collections.List<Identified>? = null,
    val annotatedElement: kotlin.collections.List<Identified>? = null,
    val `annotation`: kotlin.collections.List<Identified>? = null,
    val metaclass: Identified? = null,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>? = null,
    val metadataDefinition: Identified? = null,
    val renderingDefinition: Identified? = null,
    val exposedElement: kotlin.collections.List<Identified>? = null,
    val viewDefinition: Identified? = null,
    val portDefinition: kotlin.collections.List<Identified>? = null,
    val attributeDefinition: kotlin.collections.List<Identified>? = null,
    val enumerationDefinition: Identified? = null,
    val filterCondition: kotlin.collections.List<Identified>? = null,
    val isStandard: kotlin.Boolean? = null
) 
{
    /**
    * 
    * Values: LibraryPackage
    */
    enum class AtType(val value: kotlin.String){
        LibraryPackage("LibraryPackage");
    }
}
