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

import org.openmbee.flexo.sysmlv2.models.BindingConnectorRequest
import org.openmbee.flexo.sysmlv2.models.ConnectorAsUsageRequest
import org.openmbee.flexo.sysmlv2.models.ConnectorRequestAnyOf
import org.openmbee.flexo.sysmlv2.models.FeatureDirectionKindRequest
import org.openmbee.flexo.sysmlv2.models.Identified
import org.openmbee.flexo.sysmlv2.models.ItemFlowRequest
import org.openmbee.flexo.sysmlv2.models.PortionKindRequest
import org.openmbee.flexo.sysmlv2.models.SuccessionRequest

/**
 *
 * @param atType
 * @param atId
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
 * @param elementId
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
 * @param type
 * @param unioningType
 * @param behavior
 * @param interaction
 * @param itemFeature
 * @param itemFlowEnd
 * @param itemType
 * @param parameter
 * @param sourceOutputFeature
 * @param targetInputFeature
 * @param effectStep
 * @param guardExpression
 * @param transitionStep
 * @param triggerStep
 * @param actionDefinition
 * @param connectionDefinition
 * @param definition
 * @param directedUsage
 * @param flowConnectionDefinition
 * @param individualDefinition
 * @param isIndividual
 * @param isReference
 * @param isVariation
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
 * @param usage
 * @param variant
 * @param variantMembership
 * @param allocationDefinition
 * @param interfaceDefinition
 */
@Serializable
data class ConnectorRequest(
    @SerializedName("@type")
    val atType: ConnectorRequest.AtType,
    @SerializedName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val association: kotlin.collections.List<Identified>? = null,
    val chainingFeature: kotlin.collections.List<Identified>? = null,
    val connectorEnd: kotlin.collections.List<Identified>? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val differencingType: kotlin.collections.List<Identified>? = null,
    val directedFeature: kotlin.collections.List<Identified>? = null,
    val direction: FeatureDirectionKindRequest? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val endFeature: kotlin.collections.List<Identified>? = null,
    val endOwningType: Identified? = null,
    val feature: kotlin.collections.List<Identified>? = null,
    val featureMembership: kotlin.collections.List<Identified>? = null,
    val featureTarget: Identified? = null,
    val featuringType: kotlin.collections.List<Identified>? = null,
    val importedMembership: kotlin.collections.List<Identified>? = null,
    val inheritedFeature: kotlin.collections.List<Identified>? = null,
    val inheritedMembership: kotlin.collections.List<Identified>? = null,
    val input: kotlin.collections.List<Identified>? = null,
    val intersectingType: kotlin.collections.List<Identified>? = null,
    val isAbstract: kotlin.Boolean? = null,
    val isComposite: kotlin.Boolean? = null,
    val isConjugated: kotlin.Boolean? = null,
    val isDerived: kotlin.Boolean? = null,
    val isEnd: kotlin.Boolean? = null,
    val isImplied: kotlin.Boolean? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val isOrdered: kotlin.Boolean? = null,
    val isPortion: kotlin.Boolean? = null,
    val isReadOnly: kotlin.Boolean? = null,
    val isSufficient: kotlin.Boolean? = null,
    val isUnique: kotlin.Boolean? = null,
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
    val ownedFeatureChaining: kotlin.collections.List<Identified>? = null,
    val ownedFeatureInverting: kotlin.collections.List<Identified>? = null,
    val ownedFeatureMembership: kotlin.collections.List<Identified>? = null,
    val ownedImport: kotlin.collections.List<Identified>? = null,
    val ownedIntersecting: kotlin.collections.List<Identified>? = null,
    val ownedMember: kotlin.collections.List<Identified>? = null,
    val ownedMembership: kotlin.collections.List<Identified>? = null,
    val ownedRedefinition: kotlin.collections.List<Identified>? = null,
    val ownedReferenceSubsetting: Identified? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val ownedSpecialization: kotlin.collections.List<Identified>? = null,
    val ownedSubsetting: kotlin.collections.List<Identified>? = null,
    val ownedTypeFeaturing: kotlin.collections.List<Identified>? = null,
    val ownedTyping: kotlin.collections.List<Identified>? = null,
    val ownedUnioning: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningFeatureMembership: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelatedElement: Identified? = null,
    val owningRelationship: Identified? = null,
    val owningType: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val relatedFeature: kotlin.collections.List<Identified>? = null,
    val shortName: kotlin.String? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val sourceFeature: Identified? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val targetFeature: kotlin.collections.List<Identified>? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val type: kotlin.collections.List<Identified>? = null,
    val unioningType: kotlin.collections.List<Identified>? = null,
    val behavior: kotlin.collections.List<Identified>? = null,
    val interaction: kotlin.collections.List<Identified>? = null,
    val itemFeature: Identified? = null,
    val itemFlowEnd: kotlin.collections.List<Identified>? = null,
    val itemType: kotlin.collections.List<Identified>? = null,
    val parameter: kotlin.collections.List<Identified>? = null,
    val sourceOutputFeature: Identified? = null,
    val targetInputFeature: Identified? = null,
    val effectStep: kotlin.collections.List<Identified>? = null,
    val guardExpression: kotlin.collections.List<Identified>? = null,
    val transitionStep: Identified? = null,
    val triggerStep: kotlin.collections.List<Identified>? = null,
    val actionDefinition: kotlin.collections.List<Identified>? = null,
    val connectionDefinition: kotlin.collections.List<Identified>? = null,
    val definition: kotlin.collections.List<Identified>? = null,
    val directedUsage: kotlin.collections.List<Identified>? = null,
    val flowConnectionDefinition: kotlin.collections.List<Identified>? = null,
    val individualDefinition: Identified? = null,
    val isIndividual: kotlin.Boolean? = null,
    val isReference: kotlin.Boolean? = null,
    val isVariation: kotlin.Boolean? = null,
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
    val usage: kotlin.collections.List<Identified>? = null,
    val variant: kotlin.collections.List<Identified>? = null,
    val variantMembership: kotlin.collections.List<Identified>? = null,
    val allocationDefinition: kotlin.collections.List<Identified>? = null,
    val interfaceDefinition: kotlin.collections.List<Identified>? = null
)
{
    /**
    *
    * Values: BindingConnectorAsUsage
    */
    enum class AtType(val value: kotlin.String){
        BindingConnectorAsUsage("BindingConnectorAsUsage");
    }
}

