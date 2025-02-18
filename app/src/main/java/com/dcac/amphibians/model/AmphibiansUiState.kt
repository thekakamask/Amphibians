package com.dcac.amphibians.model

data class AmphibiansUiState(
    val amphibianList: List<Amphibian> = emptyList(),
    val currentAmphibian: Amphibian? = null,
    val isShowingDetailsScreen: Boolean = false
)
