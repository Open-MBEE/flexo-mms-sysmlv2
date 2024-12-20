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

import org.openmbee.flexo.sysmlv2.models.AnnotatingElementAnyOf
import org.openmbee.flexo.sysmlv2.models.Comment
import org.openmbee.flexo.sysmlv2.models.FeatureDirectionKind
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.MetadataFeature
import org.openmbee.flexo.sysmlv2.models.PortionKind
import org.openmbee.flexo.sysmlv2.models.TextualRepresentation

/**
 *
 * @param atId
 * @param atType
 * @param aliasIds
 * @param annotatedElement
 * @param `annotation`
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param name
 * @param ownedAnnotatingRelationship
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
 * @param chainingFeature
 * @param differencingType
 * @param directedFeature
 * @param direction
 * @param endFeature
 * @param endOwningType
 * @param feature
 * @param featureMembership
 * @param featureTarget
 * @param featuringType
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
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isSufficient
 * @param isUnique
 * @param member
 * @param membership
 * @param metaclass
 * @param multiplicity
 * @param output
 * @param ownedConjugator
 * @param ownedDifferencing
 * @param ownedDisjoining
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
 * @param ownedSpecialization
 * @param ownedSubsetting
 * @param ownedTypeFeaturing
 * @param ownedTyping
 * @param ownedUnioning
 * @param owningFeatureMembership
 * @param owningType
 * @param type
 * @param unioningType
 * @param definition
 * @param directedUsage
 * @param individualDefinition
 * @param isIndividual
 * @param isReference
 * @param isVariation
 * @param itemDefinition
 * @param metadataDefinition
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
 * @param portionKind
 * @param usage
 * @param variant
 * @param variantMembership
 * @param body
 * @param locale
 * @param documentedElement
 * @param language
 * @param representedElement
 */
@Serializable
data class AnnotatingElement(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: AnnotatingElement.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val annotatedElement: kotlin.collections.List<Identified>,
    val `annotation`: kotlin.collections.List<Identified>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
    val isImpliedIncluded: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val name: kotlin.String,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>,
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
    val chainingFeature: kotlin.collections.List<Identified>,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val direction: FeatureDirectionKind,
    val endFeature: kotlin.collections.List<Identified>,
    val endOwningType: Identified,
    val feature: kotlin.collections.List<Identified>,
    val featureMembership: kotlin.collections.List<Identified>,
    val featureTarget: Identified,
    val featuringType: kotlin.collections.List<Identified>,
    val importedMembership: kotlin.collections.List<Identified>,
    val inheritedFeature: kotlin.collections.List<Identified>,
    val inheritedMembership: kotlin.collections.List<Identified>,
    val input: kotlin.collections.List<Identified>,
    val intersectingType: kotlin.collections.List<Identified>,
    val isAbstract: kotlin.Boolean,
    val isComposite: kotlin.Boolean,
    val isConjugated: kotlin.Boolean,
    val isDerived: kotlin.Boolean,
    val isEnd: kotlin.Boolean,
    val isOrdered: kotlin.Boolean,
    val isPortion: kotlin.Boolean,
    val isReadOnly: kotlin.Boolean,
    val isSufficient: kotlin.Boolean,
    val isUnique: kotlin.Boolean,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val metaclass: Identified,
    val multiplicity: Identified,
    val output: kotlin.collections.List<Identified>,
    val ownedConjugator: Identified,
    val ownedDifferencing: kotlin.collections.List<Identified>,
    val ownedDisjoining: kotlin.collections.List<Identified>,
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
    val ownedSpecialization: kotlin.collections.List<Identified>,
    val ownedSubsetting: kotlin.collections.List<Identified>,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>,
    val ownedTyping: kotlin.collections.List<Identified>,
    val ownedUnioning: kotlin.collections.List<Identified>,
    val owningFeatureMembership: Identified,
    val owningType: Identified,
    val type: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val definition: kotlin.collections.List<Identified>,
    val directedUsage: kotlin.collections.List<Identified>,
    val individualDefinition: Identified,
    val isIndividual: kotlin.Boolean,
    val isReference: kotlin.Boolean,
    val isVariation: kotlin.Boolean,
    val itemDefinition: kotlin.collections.List<Identified>,
    val metadataDefinition: Identified,
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
    val portionKind: PortionKind,
    val usage: kotlin.collections.List<Identified>,
    val variant: kotlin.collections.List<Identified>,
    val variantMembership: kotlin.collections.List<Identified>,
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

