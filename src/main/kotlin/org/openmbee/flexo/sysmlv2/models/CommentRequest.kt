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

import org.openmbee.flexo.sysmlv2.models.CommentRequestAnyOf
import org.openmbee.flexo.sysmlv2.models.DocumentationRequest
import org.openmbee.flexo.sysmlv2.models.Identified

/**
 *
 * @param atType
 * @param atId
 * @param aliasIds
 * @param annotatedElement
 * @param `annotation`
 * @param body
 * @param declaredName
 * @param declaredShortName
 * @param documentation
 * @param elementId
 * @param isImpliedIncluded
 * @param isLibraryElement
 * @param locale
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
 * @param documentedElement
 */
@Serializable
data class CommentRequest(
    @SerializedName("@type")
    val atType: CommentRequest.AtType,
    @SerializedName("@id")
    val atId: java.util.UUID? = null,
    val aliasIds: kotlin.collections.List<kotlin.String>? = null,
    val annotatedElement: kotlin.collections.List<Identified>? = null,
    val `annotation`: kotlin.collections.List<Identified>? = null,
    val body: kotlin.String? = null,
    val declaredName: kotlin.String? = null,
    val declaredShortName: kotlin.String? = null,
    val documentation: kotlin.collections.List<Identified>? = null,
    val elementId: kotlin.String? = null,
    val isImpliedIncluded: kotlin.Boolean? = null,
    val isLibraryElement: kotlin.Boolean? = null,
    val locale: kotlin.String? = null,
    val name: kotlin.String? = null,
    val ownedAnnotatingRelationship: kotlin.collections.List<Identified>? = null,
    val ownedAnnotation: kotlin.collections.List<Identified>? = null,
    val ownedElement: kotlin.collections.List<Identified>? = null,
    val ownedRelationship: kotlin.collections.List<Identified>? = null,
    val owner: Identified? = null,
    val owningMembership: Identified? = null,
    val owningNamespace: Identified? = null,
    val owningRelationship: Identified? = null,
    val qualifiedName: kotlin.String? = null,
    val shortName: kotlin.String? = null,
    val textualRepresentation: kotlin.collections.List<Identified>? = null,
    val documentedElement: Identified? = null
)
{
    /**
    *
    * Values: Documentation
    */
    enum class AtType(val value: kotlin.String){
        Documentation("Documentation");
    }
}

