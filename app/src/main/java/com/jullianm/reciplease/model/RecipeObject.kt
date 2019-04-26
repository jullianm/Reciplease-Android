package com.jullianm.reciplease.model

import android.net.Uri
import java.net.URL

data class RecipeObject(val images: List<Image>?, val ingredientLines: List<String>, val source: Source) {

    data class Image(
        val hostedLargeUrl: String?,
        val hostedMediumUrl: String?,
        val hostedSmallUrl: String?
    )

    data class Source(
        val sourceRecipeUrl: String
    )
}
