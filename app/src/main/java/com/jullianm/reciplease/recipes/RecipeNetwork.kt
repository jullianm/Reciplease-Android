package com.jullianm.reciplease.recipes

interface RecipeNetwork {
    fun getRecipes(ingredients: List<String>, callback: RecipesHandler)
}