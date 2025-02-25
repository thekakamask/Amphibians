package com.dcac.amphibians.data

import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.network.AmphibiansApiService

//REPOSITORY IMPLEMENTATION FOR RECOVER DATA FROM API
interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<Amphibian> = amphibiansApiService.getAmphibians()
}