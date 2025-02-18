package com.dcac.amphibians.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansUiState

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    onAmphibiansClick: (Amphibian) -> Unit,
    modifier: Modifier
){
    if (amphibiansUiState.isShowingDetailsScreen) {
        AmphibiansDetailsScreen(
            amphibiansUiState = amphibiansUiState,
        )
    } else {
        AmphibiansGridScreen(
            amphibiansUiState = amphibiansUiState,
            onAmphibiansClick = onAmphibiansClick,
        )
    }
}

@Composable
fun AmphibiansGridScreen(
    amphibiansUiState: AmphibiansUiState,
    onAmphibiansClick: (Amphibian) -> Unit
) {

}

@Composable
fun AmphibiansDetailsScreen(
    amphibiansUiState: AmphibiansUiState
) {

}