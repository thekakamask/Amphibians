package com.dcac.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import com.dcac.amphibians.data.LocalDataProviderAmphibians
import com.dcac.amphibians.data.LocalNavigationAmphibiansTypesContentDataProvider
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansTypes
import com.dcac.amphibians.model.AmphibiansUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

object AmphibiansViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AmphibiansUiState())
    val uiState: StateFlow<AmphibiansUiState> = _uiState.asStateFlow()

    init {
        initialiseUiState()
    }

    private fun initialiseUiState() {

        val amphibianList = LocalDataProviderAmphibians.amphibians
        val navigationAmphibiansTypesContent = LocalNavigationAmphibiansTypesContentDataProvider.navigationAmphibiansTypesContentList

        val currentAmphibian = amphibianList.first()

        _uiState.value = AmphibiansUiState(
            amphibianList = amphibianList,
            navigationAmphibiansTypesContent = navigationAmphibiansTypesContent,
            currentAmphibian = currentAmphibian
        )
    }

    fun updateCurrentAmphibian(amphibian: Amphibian) {
        _uiState.update {
            it.copy(
                isShowingDetailsScreen = true,
                currentAmphibian = amphibian
            )
        }
    }

    fun updateCurrentAmphibianType(amphibianType: AmphibiansTypes?) {
        _uiState.update {
            val filteredList = if (amphibianType == null) {
                LocalDataProviderAmphibians.amphibians
            } else {
                LocalDataProviderAmphibians.amphibians.filter { it.type == amphibianType }
            }
            it.copy(
                currentAmphibianType = amphibianType,
                amphibianList = filteredList
            )
        }
    }

    fun resetHomeScreenStates() {
        _uiState.update {
            it.copy(
                isShowingDetailsScreen = false,
                currentAmphibian = null
            )
        }
    }



}