package com.jullianm.reciplease.recipes

import android.util.Log
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecipeObject
import kotlinx.coroutines.*
import java.lang.Exception

typealias RecipesHandler = (ArrayList<RecipeModel>) -> Unit

object RecipesNetworkManager: RecipeNetwork {

    private const val appId = "YOUR_APP_ID"
    private const val appKey = "YOUR_APP_KEY"
    private const val defaultUrl = "https://www.edamam.com/web-img/e42/e42f9119813e890af34c259785ae1cfb.jpg"
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    private val api = Injection.provideEdamamAPI()

    override fun getRecipes(ingredients: List<String>, callback: RecipesHandler) {

        scope.launch {

            try {
                val results = getRecipesDetails(ingredients)
                val recipes = ArrayList<RecipeModel>()

                if (results != null)
                    for (hit in results.hits) {
                        recipes.add(getRecipeModel(hit))
                    }

                callback(recipes)

             } catch (exception: Exception) {
                Log.e("ERROR", exception.toString())
            }
        }
    }

    private suspend fun getRecipesDetails(ingredients: List<String>): RecipeObject? {
        return api.getRecipes(appId, appKey, ingredients).await().body()
    }

    private suspend fun getRecipeModel(hit: RecipeObject.Hits): RecipeModel {
        return RecipeModel(
                name = hit.recipe.label,
                ingredients = hit.recipe.ingredientLines,
                time = hit.recipe.totalTime.toString(),
                image = hit.recipe.image,
                instructions = hit.recipe.url
        )
    }
}
