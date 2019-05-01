package com.jullianm.reciplease.recipes

import android.content.Context
import androidx.room.Room
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeModel

object RecipesDataManager: RecipeData {

    override fun getRecipes(context: Context): List<RecipeModel> {
        val database = Injection.provideAppDatabase(context)
        return database.recipesDao().getAll()
    }

    override fun saveRecipe(context: Context, model: RecipeModel) {
        val database = Injection.provideAppDatabase(context)
        return database.recipesDao().insert(model)
    }

    override fun deleteRecipe(context: Context, model: RecipeModel) {
        val database = Injection.provideAppDatabase(context)
        return database.recipesDao().delete(model)
    }

}