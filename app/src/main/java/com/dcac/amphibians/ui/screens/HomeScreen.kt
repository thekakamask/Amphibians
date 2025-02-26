package com.dcac.amphibians.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.dcac.amphibians.R
import com.dcac.amphibians.data.LocalDataProviderAmphibians
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansUiState
import com.dcac.amphibians.model.NetworkAmphibian

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState.Success,
    onAmphibiansClick: (Amphibian) -> Unit,
    onDetailScreenAndroidBackPressed: () -> Unit,
    modifier: Modifier = Modifier
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
    amphibiansUiState: AmphibiansUiState.Success,
    onAmphibiansClick: (Amphibian) -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(dimensionResource(R.dimen.grid_cells_size)),
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.padding_small))
        ) {
            items(items = amphibiansUiState.filteredAmphibians ?: emptyList(), key = { it.name }) { amphibian ->
                AmphibianCard(
                    amphibian = amphibian,
                    onAmphibianClick = onAmphibiansClick,
                )
            }
        }
    }
}

@Composable
fun AmphibianCard(
    amphibian: Amphibian,
    onAmphibianClick: (Amphibian) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .clickable { onAmphibianClick(amphibian) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.padding_medium))
                    .height(dimensionResource(R.dimen.fixed_text_height)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = amphibian.name,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Center
                )
            }
            AsyncImage(
                model = amphibian.imgSrc,
                contentDescription = amphibian.name,
                placeholder = painterResource(R.drawable.loading_img),
                error = painterResource(R.drawable.broken_image_48),
                contentScale = ContentScale.Crop,
                modifier = Modifier
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
    amphibiansUiState: AmphibiansUiState.Success,
    onDetailScreenAndroidBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    BackHandler {
        onDetailScreenAndroidBackPressed()
    }

    val currentAmphibian = amphibiansUiState.currentAmphibian as NetworkAmphibian

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

        Text(
            text = currentAmphibian.type,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(R.dimen.padding_small))
        )
        AsyncImage(
            model = currentAmphibian.imgSrc,
            contentDescription = currentAmphibian.name,
            placeholder = painterResource(R.drawable.loading_img),
            error = painterResource(R.drawable.broken_image_48),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
                .clip(MaterialTheme.shapes.large)
        )
        Text(
            text = currentAmphibian.description,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(top = dimensionResource(R.dimen.padding_medium))
        )
    }
}