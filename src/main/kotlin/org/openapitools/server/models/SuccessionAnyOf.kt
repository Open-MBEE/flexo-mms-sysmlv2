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
import org.openapitools.server.models.AssertConstraintUsageAnyOfDirection
import org.openapitools.server.models.Identified

/**
 * 
 * @param atId 
 * @param atType 
 * @param aliasIds 
 * @param association 
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
 * @param qualifiedName 
 * @param relatedElement 
 * @param relatedFeature 
 * @param shortName 
 * @param source 
 * @param sourceFeature 
 * @param target 
 * @param targetFeature 
 * @param textualRepresentation 
 * @param transitionStep 
 * @param triggerStep 
 * @param type 
 * @param unioningType 
 */
data class SuccessionAnyOf(
    val atId: java.util.UUID,
    val atType: SuccessionAnyOf.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val association: kotlin.collections.List<Identified>,
    val chainingFeature: kotlin.collections.List<Identified>,
    val connectorEnd: kotlin.collections.List<Identified>,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val direction: AssertConstraintUsageAnyOfDirection,
    val documentation: kotlin.collections.List<Identified>,
    val effectStep: kotlin.collections.List<Identified>,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName,
    val endFeature: kotlin.collections.List<Identified>,
    val endOwningType: ActionUsageRequestAnyOfMultiplicity,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val featureTarget: Identified,
    val featuringType: kotlin.collections.List<Identified>,
    val guardExpression: kotlin.collections.List<Identified>,
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
    val isImplied: ActionDefinitionRequestAnyOfIsConjugated,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated,
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
    val ownedRelatedElement: kotlin.collections.List<Identified>,
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
    val owningRelatedElement: ActionUsageRequestAnyOfMultiplicity,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity,
    val owningType: ActionUsageRequestAnyOfMultiplicity,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val relatedElement: kotlin.collections.List<Identified>,
    val relatedFeature: kotlin.collections.List<Identified>,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName,
    val source: kotlin.collections.List<Identified>,
    val sourceFeature: ActionUsageRequestAnyOfMultiplicity,
    val target: kotlin.collections.List<Identified>,
    val targetFeature: kotlin.collections.List<Identified>,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val transitionStep: ActionUsageRequestAnyOfMultiplicity,
    val triggerStep: kotlin.collections.List<Identified>,
    val type: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>
) 
{
    /**
    * 
    * Values: Succession
    */
    enum class AtType(val value: kotlin.String){
        Succession("Succession");
    }
}

