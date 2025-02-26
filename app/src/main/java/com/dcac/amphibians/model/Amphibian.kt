package com.dcac.amphibians.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Immutable
sealed class Amphibian {
    abstract val name: String
    abstract val description: String
    abstract val imgSrc: String
}

@Serializable
@Immutable
data class NetworkAmphibian(
    override val name: String,
    val type: String,
    override val description: String,
    @SerialName("img_src")
    override val imgSrc: String
) : Amphibian()

@Immutable
data class LocalAmphibian(
    override val name: String,
    val type: Int,
    override val description: String,
    override val imgSrc: String
) : Amphibian()

