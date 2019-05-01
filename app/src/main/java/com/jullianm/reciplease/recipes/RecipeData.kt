package com.jullianm.reciplease.recipes

import android.content.Context
import com.jullianm.reciplease.model.RecipeModel

interface RecipeData {
    fun getRecipes(context: Context): List<RecipeModel>
    fun saveRecipe(context: Context, model: RecipeModel)
    fun deleteRecipe(context: Context, model: RecipeModel)
}