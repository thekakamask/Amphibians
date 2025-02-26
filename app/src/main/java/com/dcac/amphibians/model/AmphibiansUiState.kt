package com.dcac.amphibians.model

//HANDLE SUCCESS,ERROR AND LOADING STATES
sealed interface AmphibiansUiState {
    data class Success(
        val amphibianList: List<Amphibian>,
        val filteredAmphibians: List<Amphibian>? = null,
        val amphibiansTypes: List<String>,
        val currentAmphibian: Amphibian? = null,
        val currentAmphibianType: String,
        val isShowingDetailsScreen: Boolean = false
    ) : AmphibiansUiState

    data class Error(val message: String) : AmphibiansUiState
    data object Loading : AmphibiansUiState
}
