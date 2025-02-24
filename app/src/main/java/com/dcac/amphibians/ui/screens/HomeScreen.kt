package com.dcac.amphibians.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.dcac.amphibians.R
import com.dcac.amphibians.data.LocalDataProviderAmphibians
import com.dcac.amphibians.data.LocalNavigationAmphibiansTypesContentDataProvider
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansUiState

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    onAmphibiansClick: (Amphibian) -> Unit,
    onDetailScreenAndroidBackPressed: () -> Unit,
    modifier: Modifier
){
    if (amphibiansUiState.isShowingDetailsScreen) {
        AmphibiansDetailsScreen(
            amphibiansUiState = amphibiansUiState,
            onDetailScreenAndroidBackPressed = onDetailScreenAndroidBackPressed,
            modifier = modifier
        )
    } else {
        AmphibiansGridScreen(
            amphibiansUiState = amphibiansUiState,
            onAmphibiansClick = onAmphibiansClick,
            modifier = modifier
        )
    }
}

@Composable
fun AmphibiansGridScreen(
    amphibiansUiState: AmphibiansUiState,
    onAmphibiansClick: (Amphibian) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(dimensionResource(R.dimen.grid_cells_size)),
            modifier = Modifier.padding(horizontal = dimensionResource(R.dimen.padding_small)),
        ) {
            items(items = amphibiansUiState.amphibianList, key = { it.id }) { amphibian ->
                AmphibianCard(
                    amphibian = amphibian,
                    onAmphibianClick = onAmphibiansClick
                )
            }
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    onAmphibianClick: (Amphibian) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .clickable { onAmphibianClick(amphibian) }
    ) {
        Column(
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_medium))

        ) {

            Text(
                text = amphibian.name,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.padding_small))
            )
            if (amphibian.name.length < 19)
                Text(
                    text = ""
                )

            // Image
            Image(
                painter = painterResource(id = LocalDataProviderAmphibians.getDrawableRes(amphibian.imgSrc)),
                contentDescription = amphibian.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(MaterialTheme.shapes.large)
            )

            Text(
                text = amphibian.description,
                style = MaterialTheme.typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_small))
            )
        }
    }
}

@Composable
fun AmphibiansDetailsScreen(
    amphibiansUiState: AmphibiansUiState,
    onDetailScreenAndroidBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onDetailScreenAndroidBackPressed()
    }

    val currentAmphibian = amphibiansUiState.currentAmphibian

    if (currentAmphibian != null) {
        val typeTextRes = LocalNavigationAmphibiansTypesContentDataProvider.navigationAmphibiansTypesContentList
            .find { it.amphibianType == currentAmphibian.type }?.text

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = dimensionResource(R.dimen.padding_large))
        ) {
            // Titre
            Text(
                text = currentAmphibian.name,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.padding_small))
            )
            if (typeTextRes != null) {
                Text(
                    text = stringResource(id = typeTextRes),
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = dimensionResource(R.dimen.padding_small))
                )
            }
            Image(
                painter = painterResource(id = LocalDataProviderAmphibians.getDrawableRes(currentAmphibian.imgSrc)),
                contentDescription = currentAmphibian.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f)
                    .clip(MaterialTheme.shapes.medium)
            )

            // Description complète
            Text(
                text = currentAmphibian.description,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(top = dimensionResource(R.dimen.padding_medium))
            )
        }
    }
}