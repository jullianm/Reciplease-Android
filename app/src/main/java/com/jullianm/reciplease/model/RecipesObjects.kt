package com.jullianm.reciplease.model

data class RecipesObjects(val matches: List<Matches>) {
    data class Matches(
        val ingredients: List<String>,
        val rating: Int?,
        val recipeName: String,
        val id: String,
        val totalTimeInSeconds: Int?
    )
}

