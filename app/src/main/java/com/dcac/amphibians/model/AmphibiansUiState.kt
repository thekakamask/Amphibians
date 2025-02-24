package com.dcac.amphibians.model

import com.dcac.amphibians.data.NavigationAmphibiansTypesContent

data class AmphibiansUiState(
    val amphibianList: List<Amphibian> = emptyList(),
    val navigationAmphibiansTypesContent: List<NavigationAmphibiansTypesContent> = emptyList(),
    val currentAmphibian: Amphibian? = null,
    val currentAmphibianType: AmphibiansTypes? = null,
    val isShowingDetailsScreen: Boolean = false
)
