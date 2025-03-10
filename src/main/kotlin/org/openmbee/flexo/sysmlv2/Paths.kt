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
@file:UseSerializers(UUIDSerializer::class, URISerializer::class)

package org.openmbee.flexo.sysmlv2

import io.ktor.resources.*
import kotlinx.serialization.*
import kotlinx.serialization.json.JsonObject
import org.openmbee.flexo.sysmlv2.infrastructure.URISerializer
import org.openmbee.flexo.sysmlv2.infrastructure.UUIDSerializer
import org.openmbee.flexo.sysmlv2.models.*
object Paths {
    /**
     * Delete branch by project and ID
     *
     * @param projectId ID of the project
     * @param branchId ID of the branch
     */
    @Serializable @Resource("/projects/{projectId}/branches/{branchId}") class deleteBranchByProjectAndId(val projectId: java.util.UUID, val branchId: java.util.UUID)

    /**
     * Get branches by project
     *
     * @param projectId ID of the project
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/branches") class getBranchesByProject(val projectId: java.util.UUID, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get branch by project and ID
     *
     * @param projectId ID of the project
     * @param branchId ID of the branch
     */
    @Serializable @Resource("/projects/{projectId}/branches/{branchId}") class getBranchesByProjectAndId(val projectId: java.util.UUID, val branchId: java.util.UUID)

    /**
     * Create branch by project
     *
     * @param projectId ID of the project
     * @param branchRequest
     */
    @Serializable @Resource("/projects/{projectId}/branches") class postBranchByProject(val projectId: java.util.UUID, val branchRequest: BranchRequest)

    /**
     * Get change by project, commit and ID
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param changeId ID of the change (DataVersion)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/changes/{changeId}") class getChangeByProjectCommitId(val projectId: java.util.UUID, val commitId: java.util.UUID, val changeId: java.util.UUID)

    /**
     * Get changes by project and commit
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/changes") class getChangesByProjectCommit(val projectId: java.util.UUID, val commitId: java.util.UUID, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get commit by project and ID
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}") class getCommitByProjectAndId(val projectId: java.util.UUID, val commitId: java.util.UUID)

    /**
     * Get commits by project
     *
     * @param projectId ID of the project
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits") class getCommitsByProject(val projectId: java.util.UUID, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Create commit by project
     *
     * @param projectId ID of the project
     * @param commitRequest
     * @param branchId ID of the branch - project&#39;s default branch if unspecified (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits") class postCommitByProject(val projectId: java.util.UUID, val commitRequest: CommitRequest, val branchId: java.util.UUID? = null)

    /**
     * Diff a base commit and compare commit
     *
     * @param projectId ID of the project
     * @param baseCommitId ID of the base commit
     * @param compareCommitId ID of the compare commit
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{compareCommitId}/diff") class diff(val projectId: java.util.UUID, val baseCommitId: java.util.UUID, val compareCommitId: java.util.UUID, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Merge source commit(s) into a target branch
     *
     * @param projectId ID of the project
     * @param sourceCommitId ID(s) of the source commit(s) - comma separated
     * @param targetBranchId ID of the target branch
     * @param description Description of merge commit - in case one is created (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     * @param &#x60;data&#x60;  (optional)
     */
    @Serializable @Resource("/projects/{projectId}/branches/{targetBranchId}/merge") class merge(val projectId: java.util.UUID, val sourceCommitId: kotlin.collections.List<java.util.UUID>, val targetBranchId: java.util.UUID, val description: kotlin.String? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null, val `data`: kotlin.collections.List<JsonObject>? = null)

    /**
     * Get element by project, commit and ID
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param elementId ID of the element
     * @param excludeUsed Exclude Data from ProjectUsages (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/elements/{elementId}") class getElementByProjectCommitId(val projectId: java.util.UUID, val commitId: java.util.UUID, val elementId: java.util.UUID, val excludeUsed: kotlin.Boolean? = null)

    /**
     * Get elements by project and commit
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param excludeUsed Exclude Data from ProjectUsages (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/elements") class getElementsByProjectCommit(val projectId: java.util.UUID, val commitId: java.util.UUID, val excludeUsed: kotlin.Boolean? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get ProjectUsage that originates the provided element
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param elementId ID of the element
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/elements/{elementId}/projectUsage") class getProjectUsageByProjectCommitElement(val projectId: java.util.UUID, val commitId: java.util.UUID, val elementId: java.util.UUID)

    /**
     * Get root elements by project and commit
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param excludeUsed Exclude Data from ProjectUsages (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/roots") class getRootsByProjectCommit(val projectId: java.util.UUID, val commitId: java.util.UUID, val excludeUsed: kotlin.Boolean? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get datatype by ID
     *
     * @param datatypeId ID of the datatype
     */
    @Serializable @Resource("/meta/datatypes/{datatypeId}") class getDatatypeById(val datatypeId: java.net.URI)

    /**
     * Get datatypes
     *
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/meta/datatypes") class getDatatypes(val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Delete project by ID
     *
     * @param projectId ID of the project
     */
    @Serializable @Resource("/projects/{projectId}") class deleteProjectById(val projectId: java.util.UUID)

    /**
     * Get project by ID
     *
     * @param projectId ID of the project
     */
    @Serializable @Resource("/projects/{projectId}") class getProjectById(val projectId: java.util.UUID)

    /**
     * Get projects
     *
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects") class getProjects(val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Create project
     *
     * @param projectRequest  (optional)
     */
    @Serializable @Resource("/projects") class postProject(val projectRequest: ProjectRequest? = null)

    /**
     * Update project by ID
     *
     * @param projectId ID of the project
     * @param projectRequest  (optional)
     */
    @Serializable @Resource("/projects/{projectId}") class putProjectById(val projectId: java.util.UUID, val projectRequest: ProjectRequest? = null)

    /**
     * Delete query by project and ID
     *
     * @param projectId ID of the project
     * @param queryId ID of the query
     */
    @Serializable @Resource("/projects/{projectId}/queries/{queryId}") class deleteQueryByProjectAndId(val projectId: java.util.UUID, val queryId: java.util.UUID)

    /**
     * Get queries by project
     *
     * @param projectId ID of the project
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/queries") class getQueriesByProject(val projectId: java.util.UUID, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get query by project and ID
     *
     * @param projectId ID of the project
     * @param queryId ID of the query
     */
    @Serializable @Resource("/projects/{projectId}/queries/{queryId}") class getQueryByProjectAndId(val projectId: java.util.UUID, val queryId: java.util.UUID)

    /**
     * Get query results by project and query definition
     *
     * @param projectId ID of the project
     * @param queryRequest
     * @param commitId ID of the commit - defaults to head of project (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/query-results") class getQueryResultsByProjectIdQuery(val projectId: java.util.UUID, val queryRequest: QueryRequest, val commitId: java.util.UUID? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get query results by project and query
     *
     * @param projectId ID of the project
     * @param queryId ID of the query
     * @param commitId ID of the commit - defaults to head of project (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/queries/{queryId}/results") class getQueryResultsByProjectIdQueryId(val projectId: java.util.UUID, val queryId: java.util.UUID, val commitId: java.util.UUID? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Get query results by project and query definition via POST
     * For compatibility with clients that don&#39;t support GET requests with a body
     * @param projectId ID of the project
     * @param queryRequest
     * @param commitId ID of the commit - defaults to head of project (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/query-results") class getQueryResultsByProjectIdQueryPost(val projectId: java.util.UUID, val queryRequest: QueryRequest, val commitId: java.util.UUID? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Create query by project
     *
     * @param projectId ID of the project
     * @param queryRequest
     */
    @Serializable @Resource("/projects/{projectId}/queries") class postQueryByProject(val projectId: java.util.UUID, val queryRequest: QueryRequest)

    /**
     * Update project by project and ID
     *
     * @param projectId ID of the project
     * @param queryId ID of the query
     * @param queryRequest
     */
    @Serializable @Resource("/projects/{projectId}/queries/{queryId}") class putQueryByProjectAndId(val projectId: java.util.UUID, val queryId: java.util.UUID, val queryRequest: QueryRequest)

    /**
     * Get relationships by project, commit, and related element
     *
     * @param projectId ID of the project
     * @param commitId ID of the commit
     * @param relatedElementId ID of the related element
     * @param direction Filter for relationships that are incoming (in), outgoing (out), or both relative to the related element (optional, default to both)
     * @param excludeUsed Exclude Data from ProjectUsages (optional)
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/commits/{commitId}/elements/{relatedElementId}/relationships") class getRelationshipsByProjectCommitRelatedElement(val projectId: java.util.UUID, val commitId: java.util.UUID, val relatedElementId: java.util.UUID, val direction: kotlin.String? = null, val excludeUsed: kotlin.Boolean? = null, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Delete tag by project and ID
     *
     * @param projectId ID of the project
     * @param tagId ID of the tag
     */
    @Serializable @Resource("/projects/{projectId}/tags/{tagId}") class deleteTagByProjectAndId(val projectId: java.util.UUID, val tagId: java.util.UUID)

    /**
     * Get tag by project and ID
     *
     * @param projectId ID of the project
     * @param tagId ID of the tag
     */
    @Serializable @Resource("/projects/{projectId}/tags/{tagId}") class getTagByProjectAndId(val projectId: java.util.UUID, val tagId: java.util.UUID)

    /**
     * Get tags by project
     *
     * @param projectId ID of the project
     * @param pageAfter Page after (optional)
     * @param pageBefore Page before (optional)
     * @param pageSize Page size (optional)
     */
    @Serializable @Resource("/projects/{projectId}/tags") class getTagsByProject(val projectId: java.util.UUID, val pageAfter: kotlin.String? = null, val pageBefore: kotlin.String? = null, val pageSize: kotlin.Int? = null)

    /**
     * Create tag by project
     *
     * @param projectId ID of the project
     * @param tagRequest
     */
    @Serializable @Resource("/projects/{projectId}/tags") class postTagByProject(val projectId: java.util.UUID, val tagRequest: TagRequest)

}
