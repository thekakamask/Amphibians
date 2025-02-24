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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dcac.amphibians.R
import com.dcac.amphibians.data.NavigationAmphibiansTypesContent
import com.dcac.amphibians.model.AmphibiansTypes
import com.dcac.amphibians.model.AmphibiansUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansPhotosApp() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val amphibianViewModel: AmphibiansViewModel = viewModel()
    val amphibianUiState = amphibianViewModel.uiState.collectAsState().value

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            AmphibiansTopAppBar(
                scrollBehavior = scrollBehavior,
                amphibiansUiState = amphibianUiState,
                onBackArrowClick = { amphibianViewModel.resetHomeScreenStates() },
                onTypeSelected = { amphibianViewModel.updateCurrentAmphibianType(it) }
            )
        }
    ) { paddingValues ->
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen(
                amphibiansUiState = amphibianUiState,
                onAmphibiansClick = { amphibianViewModel.updateCurrentAmphibian(it) },
                onDetailScreenAndroidBackPressed = { amphibianViewModel.resetHomeScreenStates() },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AmphibiansTopAppBar(
    amphibiansUiState: AmphibiansUiState,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackArrowClick: () -> Unit,
    onTypeSelected:(AmphibiansTypes?) -> Unit
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
                if (amphibiansUiState.isShowingDetailsScreen) {
                    IconButton(onClick = onBackArrowClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                }
            }
        )
        if (!amphibiansUiState.isShowingDetailsScreen) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_small))
            ) {
                items(amphibiansUiState.navigationAmphibiansTypesContent) { navigationAmphibiansTypesContent ->
                    NavigationAmphibiansTypesContentList(
                        navigationAmphibiansTypesContent = navigationAmphibiansTypesContent,
                        isSelected = navigationAmphibiansTypesContent.amphibianType == amphibiansUiState.currentAmphibianType,
                        onNavigationAmphibiansTypesContentClick = { onTypeSelected(it.amphibianType) }
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationAmphibiansTypesContentList(
    navigationAmphibiansTypesContent: NavigationAmphibiansTypesContent,
    isSelected : Boolean,
    onNavigationAmphibiansTypesContentClick: (NavigationAmphibiansTypesContent) -> Unit
) {
    Card(modifier = Modifier.padding(
        vertical = dimensionResource(R.dimen.padding_medium),
        horizontal = dimensionResource(R.dimen.padding_small))
        .clickable { onNavigationAmphibiansTypesContentClick(navigationAmphibiansTypesContent) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.secondary
            else CardDefaults.cardColors().containerColor
        )
        ){
        Text(
            text = stringResource(id = navigationAmphibiansTypesContent.text),
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_medium))
        )
    }
}



