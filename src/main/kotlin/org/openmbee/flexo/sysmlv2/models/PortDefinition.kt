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

import org.openmbee.flexo.sysmlv2.models.ConjugatedPortDefinition
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.PortDefinitionAnyOf

/**
 *
 * @param atId
 * @param atType
 * @param aliasIds
 * @param conjugatedPortDefinition
 * @param declaredName
 * @param declaredShortName
 * @param differencingType
 * @param directedFeature
 * @param directedUsage
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
 * @param isIndividual
 * @param isLibraryElement
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
 * @param qualifiedName
 * @param shortName
 * @param textualRepresentation
 * @param unioningType
 * @param usage
 * @param variant
 * @param variantMembership
 * @param originalPortDefinition
 * @param ownedPortConjugator
 */
@Serializable
data class PortDefinition(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: PortDefinition.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val conjugatedPortDefinition: Identified,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val endFeature: kotlin.collections.List<Identified>,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val importedMembership: kotlin.collections.List<Identified>,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: kotlin.Boolean,
    val isConjugated: kotlin.Boolean,
    val isImpliedIncluded: kotlin.Boolean,
    val isIndividual: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val isSufficient: kotlin.Boolean,
    val isVariation: kotlin.Boolean,
    val lifeClass: Identified,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val multiplicity: Identified,
    val name: kotlin.String,
    val output: kotlin.collections.List<Identified>,
    val ownedAction: kotlin.collections.List<Identified>,
    val ownedAllocation: kotlin.collections.List<Identified>,
    val ownedAnalysisCase: kotlin.collections.List<Identified>,
    val ownedAnnotation: kotlin.collections.List<Identified>,
    val ownedAttribute: kotlin.collections.List<Identified>,
    val ownedCalculation: kotlin.collections.List<Identified>,
    val ownedCase: kotlin.collections.List<Identified>,
    val ownedConcern: kotlin.collections.List<Identified>,
    val ownedConjugator: Identified,
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
    val owner: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelationship: Identified,
    val qualifiedName: kotlin.String,
    val shortName: kotlin.String,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>,
    val originalPortDefinition: Identified,
    val ownedPortConjugator: Identified
)
{
    /**
    *
    * Values: ConjugatedPortDefinition
    */
    enum class AtType(val value: kotlin.String){
        ConjugatedPortDefinition("ConjugatedPortDefinition");
    }
}

