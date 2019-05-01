package com.jullianm.reciplease.recipes

import androidx.lifecycle.LiveData
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecipeObject

interface RecipeNetwork {
    fun getRecipes(ingredients: List<String>, callback: RecipesHandler)
}