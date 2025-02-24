package com.dcac.amphibians.data

import com.dcac.amphibians.R
import com.dcac.amphibians.model.Amphibian
import com.dcac.amphibians.model.AmphibiansTypes

object LocalDataProviderAmphibians {

    val amphibians = listOf(
        Amphibian(
            id = 1L,
            name = "Hyla arborea",
            type = AmphibiansTypes.HYLIDAE,
            description = "a small tree-dwelling amphibian with a slender body and smooth, generally green skin, although its coloring can vary according to temperature and humidity (from brown to intense green). It has suction cups on its fingers, enabling it to adhere to vertical surfaces such as branches or leaves.",
            imgSrc = "hyla_arborea"
        ),
        Amphibian(
            id = 2L,
            name = "Alytes obstetrican",
            type = AmphibiansTypes.ALYTIDAE,
            description ="A small, stocky toad with grainy skin, often grayish-brown with dark spots. It has vertical pupils and short but sturdy limbs.",
            imgSrc = "alytes_obstetrican"
        ),
        Amphibian(
            id = 3L,
            name = "Dendrobate",
            type = AmphibiansTypes.DENDROBATIDAE,
            description = "These little frogs are famous for their bright, varied colors (blue, yellow, red, black, green), which serve as a warning to predators. Their skin secretes powerful toxins, used by certain Amazonian tribes to poison their arrows.",
            imgSrc = "dendrobate_frog"
        ),
        Amphibian(
            id = 4L,
            name = "Salamandra salamandra",
            type = AmphibiansTypes.SALAMANDRIDAE,
            description = "The fire salamander is black with bright yellow spots or stripes. It is known for its secretions that contain toxic alkaloids to deter predators.",
            imgSrc = "fire_salamander"
        ),
        Amphibian(
            id = 5L,
            name = "Ambystoma mexicanum",
            type = AmphibiansTypes.AMBYSTOMATIDAE,
            description = "Commonly known as the axolotl, this amphibian remains in its larval form throughout its life. It has external gills and can regenerate lost body parts.",
            imgSrc = "axolotl"
        ),
        Amphibian(
            id = 6L,
            name = "Rana temporaria",
            type = AmphibiansTypes.RANIDAE,
            description = "The common frog is found in Europe and has smooth skin that varies in color from brown to green. It is highly adaptable to different environments.",
            imgSrc = "rana_temporaria"
        ),
        Amphibian(
            id = 7L,
            name = "Cryptobranchus alleganiensis",
            type = AmphibiansTypes.CRYPTOBRANCHIDAE,
            description = "Also called the hellbender, this is one of the largest amphibians in North America. It has a flat body, wrinkled skin, and prefers fast-flowing rivers.",
            imgSrc = "hellbender"
        )
    )

    // Mapping `imgSrc` (String) to a drawable resource ID
    private val amphibianImages = mapOf(
        "hyla_arborea" to R.drawable.image_hyla_arborea,
        "alytes_obstetrican" to R.drawable.image_alytes_obstetrican,
        "dendrobate_frog" to R.drawable.image_dendrobate,
        "fire_salamander" to R.drawable.image_salamandra,
        "axolotl" to R.drawable.image_ambystoma_mexicanum,
        "rana_temporaria" to R.drawable.image_rana_temporaria,
        "hellbender" to R.drawable.image_cryptobranchus_alleganiensis
    )

    // Function for recover img from Amphibians model
    fun getDrawableRes(imgSrc: String?): Int {
        return amphibianImages[imgSrc] ?: R.drawable.broken_image_48  // Icône Material par défaut
    }
}