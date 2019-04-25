package com.jullianm.reciplease.model

import java.net.URL

data class RecipeObject(val images: List<Image>?, val ingredientLines: List<String>, val source: Source) {

    data class Image(
        val hostedLargeUrl: URL?,
        val hostedMediumUrl: URL?,
        val hostedSmallUrl: URL?
    )

    data class Source(
        val sourceRecipeUrl: URL
    )
}
