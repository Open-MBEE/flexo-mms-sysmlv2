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

import org.openapitools.server.models.ActionDefinitionRequestAnyOfDeclaredShortName
import org.openapitools.server.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openapitools.server.models.ActionUsageRequestAnyOfMultiplicity
import org.openapitools.server.models.AssertConstraintUsageRequestAnyOfDirection
import org.openapitools.server.models.AssertConstraintUsageRequestAnyOfPortionKind
import org.openapitools.server.models.Identified

/**
 * 
 * @param atType 
 * @param atId 
 * @param aliasIds 
 * @param behavior 
 * @param chainingFeature 
 * @param constraintDefinition 
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
 * @param predicate 
 * @param qualifiedName 
 * @param result 
 * @param shortName 
 * @param textualRepresentation 
 * @param type 
 * @param unioningType 
 * @param usage 
 * @param variant 
 * @param variantMembership 
 */
data class ConstraintUsageRequestAnyOf(
    val atType: ConstraintUsageRequestAnyOf.AtType,
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val chainingFeature: kotlin.collections.List<Identified>? = null,
    val constraintDefinition: ActionUsageRequestAnyOfMultiplicity? = null,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val definition: kotlin.collections.List<Identified>? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val directedUsage: kotlin.collections.List<Identified>? = null,
    val direction: AssertConstraintUsageRequestAnyOfDirection? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val endOwningType: ActionUsageRequestAnyOfMultiplicity? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val featureTarget: Identified? = null,
    val featuringType: kotlin.collections.List<Identified>? = null,
    val function: ActionUsageRequestAnyOfMultiplicity? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val individualDefinition: ActionUsageRequestAnyOfMultiplicity? = null,
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
    val isIndividual: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isModelLevelEvaluable: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isOrdered: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isPortion: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isReadOnly: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isReference: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isSufficient: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isUnique: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isVariation: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val multiplicity: ActionUsageRequestAnyOfMultiplicity? = null,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
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
    val owningDefinition: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningFeatureMembership: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningType: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningUsage: ActionUsageRequestAnyOfMultiplicity? = null,
    val parameter: kotlin.collections.List<Identified>? = null,
    val portionKind: AssertConstraintUsageRequestAnyOfPortionKind? = null,
    val predicate: ActionUsageRequestAnyOfMultiplicity? = null,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val result: Identified? = null,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
    val usage: kotlin.collections.List<Identified>? = null,
    val variant: kotlin.collections.List<Identified>? = null,
    val variantMembership: kotlin.collections.List<Identified>? = null
) 
{
    /**
    * 
    * Values: ConstraintUsage
    */
    enum class AtType(val value: kotlin.String){
        ConstraintUsage("ConstraintUsage");
    }
}

