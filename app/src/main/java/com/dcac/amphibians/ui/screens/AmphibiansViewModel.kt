package com.dcac.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import com.dcac.amphibians.data.LocalDataProviderAmphibians
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

object AmphibiansViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AmphibiansUiState())
    val uiState: StateFlow<AmphibiansUiState> = _uiState.asStateFlow()

    init {
        initialiseUiState()
    }

    private fun initialiseUiState() {

        val amphibianList = LocalDataProviderAmphibians.amphibians
        val currentAmphibian = amphibianList.first()

        _uiState.value = AmphibiansUiState(
            amphibianList = amphibianList,
            currentAmphibian = currentAmphibian
        )
    }

    fun updateCurrentAmphibian(amphibian: Amphibian) {
        _uiState.value = _uiState.value.copy(currentAmphibian = amphibian)
    }



}