package com.jullianm.reciplease.viewmodel

import androidx.lifecycle.ViewModel;
import android.content.Context
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.recipes.RecipesHandler
import java.util.ArrayList

class RecipesViewModel : ViewModel() {
    private val recipesNetworkManager = Injection.provideRecipesNetworkManager()
    private val recipesDataManager = Injection.provideRecipesDataManager()

    fun getRecipes(ingredients: ArrayList<String>, callback: RecipesHandler)= recipesNetworkManager.getRecipes(ingredients, callback)

    fun getFavoritesRecipes(context: Context) = recipesDataManager.getRecipes(context)

    fun saveFavoriteRecipe(context: Context, model: RecipeModel) = recipesDataManager.saveRecipe(context, model)

    fun deleteFavoriteRecipe(context: Context, model: RecipeModel) = recipesDataManager.deleteRecipe(context, model)

}
