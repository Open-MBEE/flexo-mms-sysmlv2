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

import org.openmbee.flexo.sysmlv2.models.AttributeDefinitionRequest
import org.openmbee.flexo.sysmlv2.models.DataTypeRequestAnyOf
import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param declaredName
 * @param declaredShortName
 * @param differencingType
 * @param directedFeature
 * @param documentation
 * @param elementId
 * @param endFeature
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
 * @param isLibraryElement
 * @param isSufficient
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
 * @param ownedFeatureMembership
 * @param ownedImport
 * @param ownedIntersecting
 * @param ownedMember
 * @param ownedMembership
 * @param ownedRelationship
 * @param ownedSpecialization
 * @param ownedSubclassification
 * @param ownedUnioning
 * @param owner
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelationship
 * @param qualifiedName
 * @param shortName
 * @param textualRepresentation
 * @param unioningType
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
 */
@Serializable
data class DataTypeRequest(
    @SerialName("@type")
    val atType: DataTypeRequest.AtType,
    @SerialName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val inheritedFeature: kotlin.collections.List<Identified>? = null,
    val inheritedMembership: kotlin.collections.List<Identified>? = null,
    val input: kotlin.collections.List<Identified>? = null,
    val intersectingType: kotlin.collections.List<Identified>? = null,
    val isAbstract: kotlin.Boolean? = null,
    val isConjugated: kotlin.Boolean? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val isSufficient: kotlin.Boolean? = null,
    val member: kotlin.collections.List<Identified>? = null,
    val membership: kotlin.collections.List<Identified>? = null,
    val multiplicity: Identified? = null,
    val name: kotlin.String? = null,
    val output: kotlin.collections.List<Identified>? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedConjugator: Identified? = null,
    val ownedDifferencing: kotlin.collections.List<Identified>? = null,
    val ownedDisjoining: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedEndFeature: kotlin.collections.List<Identified>? = null,
    val ownedFeature: kotlin.collections.List<Identified>? = null,
    val ownedFeatureMembership: kotlin.collections.List<Identified>? = null,
    val ownedImport: kotlin.collections.List<Identified>? = null,
    val ownedIntersecting: kotlin.collections.List<Identified>? = null,
    val ownedMember: kotlin.collections.List<Identified>? = null,
    val ownedMembership: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val ownedSpecialization: kotlin.collections.List<Identified>? = null,
    val ownedSubclassification: kotlin.collections.List<Identified>? = null,
    val ownedUnioning: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelationship: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val shortName: kotlin.String? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
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
    val enumeratedValue: kotlin.collections.List<Identified>? = null
)
{
    /**
    *
    * Values: EnumerationDefinition
    */
    enum class AtType(val value: kotlin.String){
        EnumerationDefinition("EnumerationDefinition");
    }
}

