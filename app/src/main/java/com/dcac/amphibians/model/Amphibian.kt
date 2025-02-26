package com.dcac.amphibians.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

sealed class Amphibian {
    abstract val name: String
    abstract val description: String
    abstract val imgSrc: String
}

@Serializable
data class NetworkAmphibian(
    override val name: String,
    val type: String,
    override val description: String,
    @SerialName("img_src")
    override val imgSrc: String
) : Amphibian()


data class LocalAmphibian(
    override val name: String,
    val type: Int,
    override val description: String,
    override val imgSrc: String
) : Amphibian()

