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
import org.openapitools.server.models.Identified

/**
 * 
 * @param atId 
 * @param atType 
 * @param aliasIds 
 * @param declaredName 
 * @param declaredShortName 
 * @param differencingType 
 * @param directedFeature 
 * @param directedUsage 
 * @param documentation 
 * @param elementId 
 * @param endFeature 
 * @param expression 
 * @param feature 
 * @param featureMembership 
 * @param importedMembership 
 * @param inheritedFeature 
 * @param inheritedMembership 
 * @param input 
 * @param intersectingType 
 * @param isAbstract 
 * @param isConjugated 
 * @param isImpliedIncluded 
 * @param isIndividual 
 * @param isLibraryElement 
 * @param isModelLevelEvaluable 
 * @param isSufficient 
 * @param isVariation 
 * @param lifeClass 
 * @param member 
 * @param membership 
 * @param multiplicity 
 * @param name 
 * @param output 
 * @param ownedAction 
 * @param ownedAllocation 
 * @param ownedAnalysisCase 
 * @param ownedAnnotation 
 * @param ownedAttribute 
 * @param ownedCalculation 
 * @param ownedCase 
 * @param ownedConcern 
 * @param ownedConjugator 
 * @param ownedConnection 
 * @param ownedConstraint 
 * @param ownedDifferencing 
 * @param ownedDisjoining 
 * @param ownedElement 
 * @param ownedEndFeature 
 * @param ownedEnumeration 
 * @param ownedFeature 
 * @param ownedFeatureMembership 
 * @param ownedFlow 
 * @param ownedImport 
 * @param ownedInterface 
 * @param ownedIntersecting 
 * @param ownedItem 
 * @param ownedMember 
 * @param ownedMembership 
 * @param ownedMetadata 
 * @param ownedOccurrence 
 * @param ownedPart 
 * @param ownedPort 
 * @param ownedReference 
 * @param ownedRelationship 
 * @param ownedRendering 
 * @param ownedRequirement 
 * @param ownedSpecialization 
 * @param ownedState 
 * @param ownedSubclassification 
 * @param ownedTransition 
 * @param ownedUnioning 
 * @param ownedUsage 
 * @param ownedUseCase 
 * @param ownedVerificationCase 
 * @param ownedView 
 * @param ownedViewpoint 
 * @param owner 
 * @param owningMembership 
 * @param owningNamespace 
 * @param owningRelationship 
 * @param parameter 
 * @param qualifiedName 
 * @param result 
 * @param shortName 
 * @param step 
 * @param textualRepresentation 
 * @param unioningType 
 * @param usage 
 * @param variant 
 * @param variantMembership 
 */
data class ConstraintDefinitionAnyOf(
    val atId: java.util.UUID,
    val atType: ConstraintDefinitionAnyOf.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val endFeature: kotlin.collections.List<Identified>,
    val expression: kotlin.collections.List<Identified>,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val importedMembership: kotlin.collections.List<Identified>,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: ActionDefinitionRequestAnyOfIsConjugated,
    val isConjugated: ActionDefinitionRequestAnyOfIsConjugated,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isIndividual: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
    val isModelLevelEvaluable: ActionDefinitionRequestAnyOfIsConjugated,
    val isSufficient: ActionDefinitionRequestAnyOfIsConjugated,
    val isVariation: ActionDefinitionRequestAnyOfIsConjugated,
    val lifeClass: ActionUsageRequestAnyOfMultiplicity,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val multiplicity: ActionUsageRequestAnyOfMultiplicity,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName,
    val output: kotlin.collections.List<Identified>,
    val ownedAction: kotlin.collections.List<Identified>,
    val ownedAllocation: kotlin.collections.List<Identified>,
    val ownedAnalysisCase: kotlin.collections.List<Identified>,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedAttribute: kotlin.collections.List<Identified>,
    val ownedCalculation: kotlin.collections.List<Identified>,
    val ownedCase: kotlin.collections.List<Identified>,
    val ownedConcern: kotlin.collections.List<Identified>,
    val ownedConjugator: ActionUsageRequestAnyOfMultiplicity,
    val ownedConnection: kotlin.collections.List<Identified>,
    val ownedConstraint: kotlin.collections.List<Identified>,
    val ownedDifferencing: kotlin.collections.List<Identified>,
    val ownedDisjoining: kotlin.collections.List<Identified>,
    val ownedElement: kotlin.collections.List<Identified>,
    val ownedEndFeature: kotlin.collections.List<Identified>,
    val ownedEnumeration: kotlin.collections.List<Identified>,
    val ownedFeature: kotlin.collections.List<Identified>,
    val ownedFeatureMembership: kotlin.collections.List<Identified>,
    val ownedFlow: kotlin.collections.List<Identified>,
    val ownedImport: kotlin.collections.List<Identified>,
    val ownedInterface: kotlin.collections.List<Identified>,
    val ownedIntersecting: kotlin.collections.List<Identified>,
    val ownedItem: kotlin.collections.List<Identified>,
    val ownedMember: kotlin.collections.List<Identified>,
    val ownedMembership: kotlin.collections.List<Identified>,
    val ownedMetadata: kotlin.collections.List<Identified>,
    val ownedOccurrence: kotlin.collections.List<Identified>,
    val ownedPart: kotlin.collections.List<Identified>,
    val ownedPort: kotlin.collections.List<Identified>,
    val ownedReference: kotlin.collections.List<Identified>,
    val ownedRelationship: kotlin.collections.List<Identified>,
    val ownedRendering: kotlin.collections.List<Identified>,
    val ownedRequirement: kotlin.collections.List<Identified>,
    val ownedSpecialization: kotlin.collections.List<Identified>,
    val ownedState: kotlin.collections.List<Identified>,
    val ownedSubclassification: kotlin.collections.List<Identified>,
    val ownedTransition: kotlin.collections.List<Identified>,
    val ownedUnioning: kotlin.collections.List<Identified>,
    val ownedUsage: kotlin.collections.List<Identified>,
    val ownedUseCase: kotlin.collections.List<Identified>,
    val ownedVerificationCase: kotlin.collections.List<Identified>,
    val ownedView: kotlin.collections.List<Identified>,
    val ownedViewpoint: kotlin.collections.List<Identified>,
    val owner: ActionUsageRequestAnyOfMultiplicity,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val parameter: kotlin.collections.List<Identified>,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val result: Identified,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val step: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>
) 
{
    /**
    * 
    * Values: ConstraintDefinition
    */
    enum class AtType(val value: kotlin.String){
        ConstraintDefinition("ConstraintDefinition");
    }
}

