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

/**
 *
 * @param atId
 * @param atType
 * @param aliasIds
 * @param bound
 * @param chainingFeature
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
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param isOrdered
 * @param isPortion
 * @param isReadOnly
 * @param isSufficient
 * @param isUnique
 * @param lowerBound
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
 * @param owningRelationship
 * @param owningType
 * @param qualifiedName
 * @param shortName
 * @param textualRepresentation
 * @param type
 * @param unioningType
 * @param upperBound
 */
@Serializable
data class MultiplicityRange(
    @SerialName("@id")
    val atId: java.util.UUID,
    @SerialName("@type")
    val atType: MultiplicityRange.AtType,
    val aliasIds: kotlin.collections.List<kotlin.String>,
    val bound: kotlin.collections.List<Identified>,
    val chainingFeature: kotlin.collections.List<Identified>,
    val declaredName: kotlin.String,
    val declaredShortName: kotlin.String,
    val differencingType: kotlin.collections.List<Identified>,
    val directedFeature: kotlin.collections.List<Identified>,
    val direction: FeatureDirectionKind,
    val documentation: kotlin.collections.List<Identified>,
    val elementId: kotlin.String,
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
    val isImpliedIncluded: kotlin.Boolean,
    val isLibraryElement: kotlin.Boolean,
    val isOrdered: kotlin.Boolean,
    val isPortion: kotlin.Boolean,
    val isReadOnly: kotlin.Boolean,
    val isSufficient: kotlin.Boolean,
    val isUnique: kotlin.Boolean,
    val lowerBound: Identified,
    val member: kotlin.collections.List<Identified>,
    val membership: kotlin.collections.List<Identified>,
    val multiplicity: Identified,
    val name: kotlin.String,
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
    val owningFeatureMembership: Identified,
    val owningMembership: Identified,
    val owningNamespace: Identified,
    val owningRelationship: Identified,
    val owningType: Identified,
    val qualifiedName: kotlin.String,
    val shortName: kotlin.String,
    val textualRepresentation: kotlin.collections.List<Identified>,
    val type: kotlin.collections.List<Identified>,
    val unioningType: kotlin.collections.List<Identified>,
    val upperBound: Identified
)
{
    /**
    *
    * Values: MultiplicityRange
    */
    enum class AtType(val value: kotlin.String){
        MultiplicityRange("MultiplicityRange");
    }
}

