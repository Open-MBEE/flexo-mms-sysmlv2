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

import org.openmbee.flexo.sysmlv2.models.FeatureDirectionKind
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.PortionKind

/**
 *
 * @param atId
 * @param atType
 * @param actionDefinition
 * @param aliasIds
 * @param association
 * @param behavior
 * @param chainingFeature
 * @param connectionDefinition
 * @param connectorEnd
 * @param declaredName
 * @param declaredShortName
 * @param definition
 * @param differencingType
 * @param directedFeature
 * @param directedUsage
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
 * @param flowConnectionDefinition
 * @param guardExpression
 * @param importedMembership
 * @param individualDefinition
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
 * @param isIndividual
 * @param isLibraryElement
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isReference
 * @param isSufficient
 * @param isUnique
 * @param isVariation
 * @param itemDefinition
 * @param itemFeature
 * @param itemFlowEnd
 * @param itemType
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
 * @param ownedRelatedElement
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
 * @param owningRelatedElement
 * @param owningRelationship
 * @param owningType
 * @param owningUsage
 * @param parameter
 * @param partDefinition
 * @param portionKind
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
 * @param usage
 * @param variant
 * @param variantMembership
 */
@Serializable
data class SuccessionFlowConnectionUsage(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: SuccessionFlowConnectionUsage.AtType,
    val actionDefinition: kotlin.collections.List<Identified>,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val association: kotlin.collections.List<Identified>,
    val behavior: kotlin.collections.List<Identified>,
    val chainingFeature: kotlin.collections.List<Identified>,
    val connectionDefinition: kotlin.collections.List<Identified>,
    val connectorEnd: kotlin.collections.List<Identified>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val definition: kotlin.collections.List<Identified>,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val direction: FeatureDirectionKind,
    val documentation: kotlin.collections.List<Identified>,
    val effectStep: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val endFeature: kotlin.collections.List<Identified>,
    val endOwningType: Identified,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val featureTarget: Identified,
    val featuringType: kotlin.collections.List<Identified>,
    val flowConnectionDefinition: kotlin.collections.List<Identified>,
    val guardExpression: kotlin.collections.List<Identified>,
    val importedMembership: kotlin.collections.List<Identified>,
    val individualDefinition: Identified,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val interaction: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: kotlin.Boolean,
    val isComposite: kotlin.Boolean,
    val isConjugated: kotlin.Boolean,
    val isDerived: kotlin.Boolean,
    val isEnd: kotlin.Boolean,
    val isImplied: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isIndividual: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val isOrdered: kotlin.Boolean,
    val isPortion: kotlin.Boolean,
    val isReadOnly: kotlin.Boolean,
    val isReference: kotlin.Boolean,
    val isSufficient: kotlin.Boolean,
    val isUnique: kotlin.Boolean,
    val isVariation: kotlin.Boolean,
    val itemDefinition: kotlin.collections.List<Identified>,
    val itemFeature: Identified,
    val itemFlowEnd: kotlin.collections.List<Identified>,
    val itemType: kotlin.collections.List<Identified>,
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
    val ownedRelatedElement: kotlin.collections.List<Identified>,
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
    val owningRelatedElement: Identified,
    val owningRelationship: Identified,
    val owningType: Identified,
    val owningUsage: Identified,
    val parameter: kotlin.collections.List<Identified>,
    val partDefinition: kotlin.collections.List<Identified>,
    val portionKind: PortionKind,
    val qualifiedName: kotlin.String,
    val relatedElement: kotlin.collections.List<Identified>,
    val relatedFeature: kotlin.collections.List<Identified>,
    val shortName: kotlin.String,
    val source: kotlin.collections.List<Identified>,
    val sourceFeature: Identified,
    val sourceOutputFeature: Identified,
    val target: kotlin.collections.List<Identified>,
    val targetFeature: kotlin.collections.List<Identified>,
    val targetInputFeature: Identified,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val transitionStep: Identified,
    val triggerStep: kotlin.collections.List<Identified>,
    val type: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>
)
{
    /**
    *
    * Values: SuccessionFlowConnectionUsage
    */
    enum class AtType(val value: kotlin.String){
        SuccessionFlowConnectionUsage("SuccessionFlowConnectionUsage");
    }
}

