package com.dcac.amphibians.utils

import com.dcac.amphibians.R

object AmphibiansTypeConverter {
    private val typeMap = mapOf(
        R.string.frog to "Frog",
        R.string.toad to "Toad",
        R.string.salamander to "Salamander"
    )

    fun convertType(type: Int): String {
        return typeMap[type] ?: "Unknown Type"
    }
}