package com.dcac.amphibians.data

import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.LocalAmphibian
import com.dcac.amphibians.model.NetworkAmphibian
import com.dcac.amphibians.network.AmphibiansApiService

//REPOSITORY IMPLEMENTATION FOR RECOVER DATA FROM API
interface AmphibiansRepository {
    suspend fun getAmphibians(): List<Amphibian>
}
class NetworkAmphibiansRepository(
    private val amphibiansApiService: AmphibiansApiService
) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<NetworkAmphibian> = amphibiansApiService.getNetworkAmphibians()
}

class LocalAmphibiansRepository : AmphibiansRepository {
    override suspend fun getAmphibians(): List<LocalAmphibian> = LocalDataProviderAmphibians.localAmphibians
}

class DefaultAmphibiansRepository(
    private val networkRepository: NetworkAmphibiansRepository,
    private val localRepository: LocalAmphibiansRepository
) : AmphibiansRepository {

    override suspend fun getAmphibians(): List<Amphibian> {
        val serverAmphibians = networkRepository.getAmphibians()
        val localAmphibians = localRepository.getAmphibians()

        return (serverAmphibians + localAmphibians)
            .distinctBy { it.name }
            .sortedBy { it.name }
    }
}