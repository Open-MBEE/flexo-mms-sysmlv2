package com.modeldriven.sysmlv2.apiService

import io.ktor.server.application.ApplicationCall
import io.ktor.util.pipeline.PipelineContext
import org.openmbee.flexo.sysmlv2.DEFAULT_PREFIX_MAPPING
import org.openmbee.flexo.sysmlv2.SYSMLV2
import org.openmbee.flexo.sysmlv2.apiService.ApiServiceError
import org.openmbee.flexo.sysmlv2.flexoRequestPut


import java.time.LocalDateTime

class ApiServiceError(message: String): Error(message)

open public class RdfServiceFlexo(modelGraph:GraphConfig, projectGraph:GraphConfig, val projectId:String?, val context : PipelineContext<*, ApplicationCall> ) : RdfService(modelGraph, projectGraph) {


    /**
     * Utilize end transaction to perform a SparQL update to Rdf4J based on the contents of the updater object.
     */
    override suspend fun endTransaction(transactionID:String?){
        for (gc in this.rdfGraphs.values) {
            val updater = gc.updater
            val update = gc.updater.getCombinedUpdate() // << MAY REQUIRE SOME CHANGE
            if (!update.isEmpty()) {
                if (this.exportStream !=null) {// ToDo log service
                    this.exportStream.println("##SparQL Update ${LocalDateTime.now()}")
                    this.exportStream.println(update)
                    this.exportStream.flush()
                }
                try {
                    val flexoResponseLoad = context.flexoRequestPut {
                        orgPath("/repos/$projectId/branches/master/graph")
                        turtle {
                            update
                        }
                    }
                    // forward failures to client
                    if(flexoResponseLoad.isFailure()) {
                        throw ApiServiceError( flexoResponseLoad.toString() )
                    }
                    if (this.exportStream !=null) {// ToDo log service
                        this.exportStream.println("##SparQL Update complete ${LocalDateTime.now()}")
                        this.exportStream.flush()
                    }
                } catch (e: Exception) {
                    this.exportStream.println("##SparQL Update Exception $e")
                    println("##SparQL Update Exception query \n$update")
                    logApiError("SparQL Update Exception $e")

                }
            }
        }
    }


}
