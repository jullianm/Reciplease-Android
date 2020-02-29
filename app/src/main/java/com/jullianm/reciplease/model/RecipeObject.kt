package com.jullianm.reciplease.model

data class RecipeObject(val hits: List<Hits>) {
    data class Hits(
        val recipe: Recipe
    )

    data class Recipe (
            val uri: String,
            val label: String,
            val image: String,
            val source: String,
            val url: String,
            val ingredientLines: List<String>,
            val totalTime: Long
    )
}

