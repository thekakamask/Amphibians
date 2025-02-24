package com.dcac.amphibians.ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dcac.amphibians.R

@Composable
fun truncateDescription(description: String): String {
    val truncatedWords = description.split(" ").take(10).joinToString(" ")
    return "$truncatedWords ${stringResource(R.string.ellipsis)}"
}