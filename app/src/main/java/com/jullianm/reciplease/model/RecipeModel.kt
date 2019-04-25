package com.jullianm.reciplease.model

import android.media.Image
import java.net.URL

data class RecipeModel(
    val name: String,
    val ingredients: String,
    val portions: List<String>?,
    val rating: String?,
    val time: String,
    val image: Image?,
    val instructions: URL?
)


