package com.dcac.amphibians.network

import com.dcac.amphibians.model.NetworkAmphibian
import retrofit2.http.GET

// INTERFACE RETROFIT FOR API CALLS
interface AmphibiansApiService {
    @GET("amphibians")
    suspend fun getNetworkAmphibians(): List<NetworkAmphibian>

}