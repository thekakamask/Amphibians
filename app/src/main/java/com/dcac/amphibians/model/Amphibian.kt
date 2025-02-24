package com.dcac.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Amphibian(
    val id: Long,
    val name: String,
    val type: AmphibiansTypes,
    val description: String,
    @SerialName (value = "img_src" )
    val imgSrc: String,
)