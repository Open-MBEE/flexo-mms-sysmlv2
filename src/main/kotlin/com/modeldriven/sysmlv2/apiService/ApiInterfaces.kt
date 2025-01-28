package com.modeldriven.com.modeldriven.sysmlv2.apiService

import kotlinx.serialization.json.JsonArray

/**
 * Interface defining basic CRUD operations for interacting with an API service.
 * This interface is typically used for managing resources within the Model graph
 */
interface ApiCrudInterface {
    /**
     * Applies a series of changes to the service. Each change in the provided JSON array of DataVersions represents
     * an update or modification of an element  that should be processed. The method ensures that all transactions
     * are executed within a controlled context, starting and ending a transaction for the batch
     * operation. In case of invalid data, the method logs warnings or errors.
     *
     * @param changes A JSON array containing a list of DataVersions to be applied. Each element is expected
     * to be a JSON object specifying the details of the change.
     * @isNewPayload Payload of changes are new objects, no need to "edit" existing data. However, isNewPayload false means edit OR create.
     */
    fun changeService(changes: JsonArray, isNewPayload:Boolean=false)

    /**
     * Retrieves a JSON array of elements based on the specified unique identifier, or if the uniqueIdentifer is
     * null, returns all elements with the model graph. Since this is API specific, it only queries the model graph.
     *
     * @param uniqueID The unique identifier used to locate and retrieve the elements. If null, the method may
     * return all elements or an empty result, depending on the implementation.
     * @return A JSON array containing the elements associated with the provided unique identifier.
     */
    fun getElements(uniqueID:String?) : JsonArray
}