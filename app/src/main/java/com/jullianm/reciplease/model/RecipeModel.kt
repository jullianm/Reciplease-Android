package com.jullianm.reciplease.model

import android.media.Image
import android.net.Uri
import java.net.URL

data class RecipeModel(
    val name: String,
    val ingredients: String,
    val portions: List<String>?,
    val rating: String?,
    val time: String,
    val image: String?,
    val instructions: String?
)


