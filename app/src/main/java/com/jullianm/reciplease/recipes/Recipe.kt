package com.jullianm.reciplease.recipes

import android.arch.lifecycle.LiveData
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecipeObject

interface Recipe {
    fun getRecipes(ingredients: List<String>, callback: RecipesHandler)
}