package com.dcac.amphibians.network

import com.dcac.amphibians.model.Amphibian
import retrofit2.http.GET

// INTERFACE RETROFIT FOR API CALLS
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getAmphibians(): List<Amphibian>

}