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

import org.openmbee.flexo.sysmlv2.models.FeatureDirectionKindRequest
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.IncludeUseCaseUsageRequest
import org.openmbee.flexo.sysmlv2.models.PortionKindRequest
import org.openmbee.flexo.sysmlv2.models.UseCaseUsageRequestAnyOf

/**
 *
 * @param atType
 * @param atId
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
 * @param includedUseCase
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
 * @param useCaseDefinition
 * @param variant
 * @param variantMembership
 * @param eventOccurrence
 * @param performedAction
 * @param useCaseIncluded
 */
@Serializable
data class UseCaseUsageRequest(
    @SerializedName("@type")
    val atType: UseCaseUsageRequest.AtType,
    @SerializedName("@id")
    val atId: java.util.UUID? = null,
    val actionDefinition: kotlin.collections.List<Identified>? = null,
    val actorParameter: kotlin.collections.List<Identified>? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val calculationDefinition: Identified? = null,
    val caseDefinition: Identified? = null,
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
    val function: Identified? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val includedUseCase: kotlin.collections.List<Identified>? = null,
    val individualDefinition: Identified? = null,
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
    val isIndividual: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val isModelLevelEvaluable: kotlin.Boolean? = null,
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
    val objectiveRequirement: Identified? = null,
    val occurrenceDefinition: kotlin.collections.List<Identified>? = null,
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
    val parameter: kotlin.collections.List<Identified>? = null,
    val portionKind: PortionKindRequest? = null,
    val qualifiedName: kotlin.String? = null,
    val result: Identified? = null,
    val shortName: kotlin.String? = null,
    val subjectParameter: Identified? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
    val usage: kotlin.collections.List<Identified>? = null,
    val useCaseDefinition: Identified? = null,
    val variant: kotlin.collections.List<Identified>? = null,
    val variantMembership: kotlin.collections.List<Identified>? = null,
    val eventOccurrence: Identified? = null,
    val performedAction: Identified? = null,
    val useCaseIncluded: Identified? = null
)
{
    /**
    *
    * Values: IncludeUseCaseUsage
    */
    enum class AtType(val value: kotlin.String){
        IncludeUseCaseUsage("IncludeUseCaseUsage");
    }
}

