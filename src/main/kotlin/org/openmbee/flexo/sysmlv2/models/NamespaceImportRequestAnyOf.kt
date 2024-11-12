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

import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfDeclaredShortName
import org.openmbee.flexo.sysmlv2.models.ActionDefinitionRequestAnyOfIsConjugated
import org.openmbee.flexo.sysmlv2.models.ActionUsageRequestAnyOfMultiplicity
import org.openmbee.flexo.sysmlv2.models.FeatureMembershipRequestAnyOfVisibility
import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param importOwningNamespace
 * @param importedElement
 * @param importedNamespace
 * @param isImplied
 * @param isImpliedIncluded
 * @param isImportAll
 * @param isLibraryElement
 * @param isRecursive
 * @param name
 * @param ownedAnnotation
 * @param ownedElement
 * @param ownedRelatedElement
 * @param ownedRelationship
 * @param owner
 * @param owningMembership
 * @param owningNamespace
 * @param owningRelatedElement
 * @param owningRelationship
 * @param qualifiedName
 * @param relatedElement
 * @param shortName
 * @param source
 * @param target
 * @param textualRepresentation
 * @param visibility
 */
@Serializable
data class NamespaceImportRequestAnyOf(
    @SerializedName("@type")
    val atType: NamespaceImportRequestAnyOf.AtType,
    @SerializedName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val declaredName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val declaredShortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val importOwningNamespace: Identified? = null,
    val importedElement: Identified? = null,
    val importedNamespace: Identified? = null,
    val isImplied: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isImpliedIncluded: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isImportAll: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isLibraryElement: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val isRecursive: ActionDefinitionRequestAnyOfIsConjugated? = null,
    val name: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelatedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningMembership: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningNamespace: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningRelatedElement: ActionUsageRequestAnyOfMultiplicity? = null,
    val owningRelationship: ActionUsageRequestAnyOfMultiplicity? = null,
    val qualifiedName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val relatedElement: kotlin.collections.List<Identified>? = null,
    val shortName: ActionDefinitionRequestAnyOfDeclaredShortName? = null,
    val source: kotlin.collections.List<Identified>? = null,
    val target: kotlin.collections.List<Identified>? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val visibility: FeatureMembershipRequestAnyOfVisibility? = null
)
{
    /**
    *
    * Values: NamespaceImport
    */
    enum class AtType(val value: kotlin.String){
        NamespaceImport("NamespaceImport");
    }
}

