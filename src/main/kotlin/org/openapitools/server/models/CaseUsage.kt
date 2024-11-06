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

import org.openapitools.server.models.AnalysisCaseUsage
import org.openapitools.server.models.CaseUsageAnyOf
import org.openapitools.server.models.FeatureDirectionKind
import org.openapitools.server.models.Identified
import org.openapitools.server.models.PortionKind
import org.openapitools.server.models.UseCaseUsage
import org.openapitools.server.models.VerificationCaseUsage

/**
 * 
 * @param atId 
 * @param atType 
 * @param actionDefinition 
 * @param actorParameter 
 * @param aliasIds 
 * @param behavior 
 * @param calculationDefinition 
 * @param caseDefinition 
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
 * @param function 
 * @param importedMembership 
 * @param individualDefinition 
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
 * @param isIndividual 
 * @param isLibraryElement 
 * @param isModelLevelEvaluable 
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
 * @param objectiveRequirement 
 * @param occurrenceDefinition 
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
 * @param parameter 
 * @param portionKind 
 * @param qualifiedName 
 * @param result 
 * @param shortName 
 * @param subjectParameter 
 * @param textualRepresentation 
 * @param type 
 * @param unioningType 
 * @param usage 
 * @param variant 
 * @param variantMembership 
 * @param verificationCaseDefinition 
 * @param verifiedRequirement 
 * @param includedUseCase 
 * @param useCaseDefinition 
 * @param eventOccurrence 
 * @param performedAction 
 * @param useCaseIncluded 
 * @param analysisCaseDefinition 
 * @param resultExpression 
 */
data class CaseUsage(
    val atId: java.util.UUID,
    val atType: CaseUsage.AtType,
    val actionDefinition: kotlin.collections.List<Identified>,
    val actorParameter: kotlin.collections.List<Identified>,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val behavior: kotlin.collections.List<Identified>,
    val calculationDefinition: Identified,
    val caseDefinition: Identified,
    val chainingFeature: kotlin.collections.List<Identified>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val definition: kotlin.collections.List<Identified>,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val direction: FeatureDirectionKind,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val endFeature: kotlin.collections.List<Identified>,
    val endOwningType: Identified,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val featureTarget: Identified,
    val featuringType: kotlin.collections.List<Identified>,
    val function: Identified,
    val importedMembership: kotlin.collections.List<Identified>,
    val individualDefinition: Identified,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: kotlin.Boolean,
    val isComposite: kotlin.Boolean,
    val isConjugated: kotlin.Boolean,
    val isDerived: kotlin.Boolean,
    val isEnd: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isIndividual: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val isModelLevelEvaluable: kotlin.Boolean,
    val isOrdered: kotlin.Boolean,
    val isPortion: kotlin.Boolean,
    val isReadOnly: kotlin.Boolean,
    val isReference: kotlin.Boolean,
    val isSufficient: kotlin.Boolean,
    val isUnique: kotlin.Boolean,
    val isVariation: kotlin.Boolean,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val multiplicity: Identified,
    val name: kotlin.String,
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
    val objectiveRequirement: Identified,
    val occurrenceDefinition: kotlin.collections.List<Identified>,
    val output: kotlin.collections.List<Identified>,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedConjugator: Identified,
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
    val ownedReferenceSubsetting: Identified,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val ownedSpecialization: kotlin.collections.List<Identified>,
    val ownedSubsetting: kotlin.collections.List<Identified>,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>,
    val ownedTyping: kotlin.collections.List<Identified>,
    val ownedUnioning: kotlin.collections.List<Identified>,
    val owner: Identified,
    val owningDefinition: Identified,
    val owningFeatureMembership: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelationship: Identified,
    val owningType: Identified,
    val owningUsage: Identified,
    val parameter: kotlin.collections.List<Identified>,
    val portionKind: PortionKind,
    val qualifiedName: kotlin.String,
    val result: Identified,
    val shortName: kotlin.String,
    val subjectParameter: Identified,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val type: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>,
    val verificationCaseDefinition: Identified,
    val verifiedRequirement: kotlin.collections.List<Identified>,
    val includedUseCase: kotlin.collections.List<Identified>,
    val useCaseDefinition: Identified,
    val eventOccurrence: Identified,
    val performedAction: Identified,
    val useCaseIncluded: Identified,
    val analysisCaseDefinition: Identified,
    val resultExpression: Identified
) 
{
    /**
    * 
    * Values: AnalysisCaseUsage
    */
    enum class AtType(val value: kotlin.String){
        AnalysisCaseUsage("AnalysisCaseUsage");
    }
}

