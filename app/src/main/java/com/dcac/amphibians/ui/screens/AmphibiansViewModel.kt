package com.dcac.amphibians.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.dcac.amphibians.AmphibiansApplication
import com.dcac.amphibians.data.AmphibiansRepository
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansUiState
import com.dcac.amphibians.model.LocalAmphibian
import com.dcac.amphibians.model.NetworkAmphibian
import com.dcac.amphibians.utils.AmphibiansTypeConverter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

// HANDLE UI STATE AND EXPOSE DATA VIA STATEFLOW
class AmphibiansViewModel(private val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<AmphibiansUiState>(AmphibiansUiState.Loading)
    val uiState: StateFlow<AmphibiansUiState> = _uiState.asStateFlow()

    init {
        getAmphibians()
    }

    private fun getAmphibians() {
        viewModelScope.launch {
            _uiState.value = AmphibiansUiState.Loading
            try {
                val currentAmphibiansType = ALL_TYPES
                val amphibiansList = amphibiansRepository.getAmphibians()

                val formattedAmphibians = amphibiansList.map { amphibian ->
                    when (amphibian) {
                        is LocalAmphibian -> NetworkAmphibian(
                            name = amphibian.name,
                            type = AmphibiansTypeConverter.convertType(amphibian.type),
                            description = amphibian.description,
                            imgSrc = "android.resource://com.dcac.amphibians/drawable/${amphibian.imgSrc}"
                        )
                        is NetworkAmphibian -> amphibian
                    }
                }
                // Extract distinct types after conversion
                val amphibianTypes = (listOf(ALL_TYPES) + formattedAmphibians.map { it.type })
                    .distinct()
                    .sorted()

                println("🔍 Types extracted: $amphibianTypes")

                val newState = AmphibiansUiState.Success(
                    currentAmphibianType = currentAmphibiansType,
                    amphibianList = formattedAmphibians,
                    filteredAmphibians = formattedAmphibians,
                    amphibiansTypes = amphibianTypes
                )

                // Check if new state is different before emit it
                if (_uiState.value != newState) {
                    _uiState.value = newState
                }

                println("✅ Amphibians loaded successfully: ${amphibiansList.size} items")
            } catch (e: IOException) {
                _uiState.value = AmphibiansUiState.Error("Network error, please check your connection.")
                println("❌ Network error: ${e.message}")
            } catch (e: HttpException) {
                _uiState.value = AmphibiansUiState.Error("Server error: ${e.code()}")
                println("❌ HTTP error: ${e.code()}")
            } catch (e: Exception) {
                _uiState.value = AmphibiansUiState.Error("Unexpected error: ${e.message}")
                println("❌ Unexpected error: ${e.message}")
            }
        }
    }

    fun updateCurrentAmphibian(amphibian: Amphibian) {
        if (_uiState.value is AmphibiansUiState.Success) {
            _uiState.update {
                (it as AmphibiansUiState.Success).copy(
                    isShowingDetailsScreen = true,
                    currentAmphibian = amphibian
                )
            }
        }
    }

    fun updateCurrentAmphibianType(selectedType: String) {
        if (_uiState.value is AmphibiansUiState.Success) {
            _uiState.update {
                val successState = it as AmphibiansUiState.Success

                val filteredList =
                    if (selectedType == ALL_TYPES) {
                        successState.amphibianList
                    } else {
                        successState.amphibianList.filter { amphibian ->
                            (amphibian as NetworkAmphibian).type == selectedType
                    }
                }
                successState.copy(
                    currentAmphibianType = selectedType,
                    filteredAmphibians = filteredList
                )
            }
        }
    }

    fun resetHomeScreenStates() {
        if (_uiState.value is AmphibiansUiState.Success) {
            _uiState.update {
                (it as AmphibiansUiState.Success).copy(
                    isShowingDetailsScreen = false,
                    currentAmphibian = null
                )
            }
        }
    }

    fun retryLoading() {
        getAmphibians()
    }

    companion object {
        private const val ALL_TYPES = "All Types"
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = application.container.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}
