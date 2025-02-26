package com.dcac.amphibians.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dcac.amphibians.R
import com.dcac.amphibians.model.AmphibiansUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansPhotosApp() {
    val amphibianViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)
    val amphibianUiState = amphibianViewModel.uiState.collectAsState().value

    val scrollBehavior = if (amphibianUiState is AmphibiansUiState.Success && amphibianUiState.isShowingDetailsScreen) {
        null
    } else {
        TopAppBarDefaults.enterAlwaysScrollBehavior()
    }

    Scaffold(
        modifier = scrollBehavior?.let { Modifier.nestedScroll(it.nestedScrollConnection) } ?: Modifier,
        topBar = {
            AmphibiansTopAppBar(
                scrollBehavior = scrollBehavior,
                amphibiansUiState = amphibianUiState,
                onBackArrowClick = { amphibianViewModel.resetHomeScreenStates() },
                onTypeSelected = { amphibianViewModel.updateCurrentAmphibianType(it) }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            when (amphibianUiState) {
                is AmphibiansUiState.Loading -> {
                    LoadingHomeScreen()
                }

                is AmphibiansUiState.Error -> {
                    ErrorHomeScreen(
                        amphibiansUiState = amphibianUiState,
                        onRetryClick = { amphibianViewModel.retryLoading() }
                    )
                }

                is AmphibiansUiState.Success -> {
                    HomeScreen(
                        amphibiansUiState = amphibianUiState,
                        onAmphibiansClick = { amphibianViewModel.updateCurrentAmphibian(it) },
                        onDetailScreenAndroidBackPressed = { amphibianViewModel.resetHomeScreenStates() }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar(
    amphibiansUiState: AmphibiansUiState,
    scrollBehavior: TopAppBarScrollBehavior?,
    onBackArrowClick: () -> Unit,
    onTypeSelected:(String) -> Unit
) {
    Column {
        CenterAlignedTopAppBar(
            scrollBehavior = scrollBehavior,
            title = {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            navigationIcon = {
                if (amphibiansUiState is AmphibiansUiState.Success && amphibiansUiState.isShowingDetailsScreen) {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
        when (amphibiansUiState) {
            is AmphibiansUiState.Loading -> {
                Text(
                    text = stringResource(R.string.loading),
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)),
                    textAlign = TextAlign.Center
                )
            }

            is AmphibiansUiState.Error -> {
                Text(
                    text = amphibiansUiState.message,
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(dimensionResource(R.dimen.padding_small)),
                    textAlign = TextAlign.Center
                )
            }

            is AmphibiansUiState.Success -> {
                if (!amphibiansUiState.isShowingDetailsScreen) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = dimensionResource(R.dimen.padding_small))
                    ) {
                        items(amphibiansUiState.amphibiansTypes) { type ->
                            NavigationAmphibiansTypesListContent(
                                type = type,
                                isSelected = type == amphibiansUiState.currentAmphibianType,
                                onNavigationAmphibiansTypesContentClick = { onTypeSelected(it) }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NavigationAmphibiansTypesListContent(
    type: String,
    isSelected : Boolean,
    onNavigationAmphibiansTypesContentClick: (String) -> Unit
) {

    Card(modifier = Modifier.padding(
        vertical = dimensionResource(R.dimen.padding_medium),
        horizontal = dimensionResource(R.dimen.padding_small))
        .clickable { onNavigationAmphibiansTypesContentClick(type) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.secondary
            else CardDefaults.cardColors().containerColor
        )
        ){
        Text(
            text = type,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
    }
}



