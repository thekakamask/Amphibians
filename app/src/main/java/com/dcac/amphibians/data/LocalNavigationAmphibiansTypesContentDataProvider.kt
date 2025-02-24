package com.dcac.amphibians.data

import androidx.annotation.StringRes
import com.dcac.amphibians.R
import com.dcac.amphibians.model.AmphibiansTypes

object LocalNavigationAmphibiansTypesContentDataProvider {

    val navigationAmphibiansTypesContentList = listOf(
        NavigationAmphibiansTypesContent(
            amphibianType = null,
            text = R.string.all_types
        )
    ) + listOf(
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.HYLIDAE,
            text = R.string.hylidae
        ),
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.ALYTIDAE,
            text = R.string.alytidae
        ),
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.DENDROBATIDAE,
            text = R.string.dendrobatidae
        ),
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.SALAMANDRIDAE,
            text = R.string.salamandridae
        ),
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.AMBYSTOMATIDAE,
            text = R.string.ambystomatidae
        ),
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.RANIDAE,
            text =R.string.ranidae
        ),
        NavigationAmphibiansTypesContent(
            amphibianType = AmphibiansTypes.CRYPTOBRANCHIDAE,
            text = R.string.cryptobranchidae
        )
    )
}

data class NavigationAmphibiansTypesContent(
    val amphibianType: AmphibiansTypes?,
    @StringRes
    val text: Int
)