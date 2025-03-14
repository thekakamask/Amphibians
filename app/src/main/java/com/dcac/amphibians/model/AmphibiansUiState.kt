package com.dcac.amphibians.model

import androidx.compose.runtime.Immutable

//HANDLE SUCCESS,ERROR AND LOADING STATES
sealed interface AmphibiansUiState {

    @Immutable
    data class Success(
        val amphibianList: List<Amphibian>,
        val filteredAmphibians: List<Amphibian>? = null,
        val amphibiansTypes: List<String>,
        val currentAmphibian: Amphibian? = null,
        val previousAmphibianType : String,
        val currentAmphibianType: String,
        val isShowingDetailsScreen: Boolean = false
    ) : AmphibiansUiState

    data class Error(val message: String) : AmphibiansUiState
    data object Loading : AmphibiansUiState
}
