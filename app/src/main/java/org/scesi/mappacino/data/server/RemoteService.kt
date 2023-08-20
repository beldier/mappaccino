package org.scesi.mappacino.data.server

import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    // TODO receive career as an argument and use only this endpoint
    @GET("data/FCyT/2023-01/134111.json")
    suspend fun getComputerScienceSubjects(): RemoteResult

}