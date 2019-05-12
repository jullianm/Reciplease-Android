package com.jullianm.reciplease.recipes

import android.util.Log
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecipeObject
import com.jullianm.reciplease.model.RecipesObjects
import kotlinx.coroutines.*
import java.lang.Exception

typealias RecipesHandler = (ArrayList<RecipeModel>) -> Unit

object RecipesNetworkManager: RecipeNetwork {

    private const val appId = "YOUR_APP_ID"
    private const val appKey = "YOUR_APP_KEY"
    private const val defaultUrl = "http://i2.yummly.com/Hot-Turkey-Salad-Sandwiches-Allrecipes.l.png"
    private val scope = CoroutineScope(Dispatchers.Main + Job())

    private val api = Injection.provideYummlyAPI()

    override fun getRecipes(ingredients: List<String>, callback: RecipesHandler) {

        scope.launch {

            try {
                val results = getRecipesDetails(ingredients)
                val recipes = ArrayList<RecipeModel>()

                if (results != null)
                    for (recipe in results.matches) {
                        recipes.add(getMoreDetailsForRecipe(recipe))
                    }

                callback(recipes)

             } catch (exception: Exception) {
                Log.e("ERROR", exception.toString())
            }
        }
    }

    private suspend fun getRecipesDetails(ingredients: List<String>): RecipesObjects? {
        return api.getRecipes(appId, appKey, ingredients, 20).await().body()
    }

    private suspend fun getMoreDetailsForRecipe(recipe: RecipesObjects.Matches): RecipeModel {
        api.getRecipe(recipe.id, appId, appKey).await().body().let { moreDetails ->
            return RecipeModel(
                recipe.recipeName,
                recipe.ingredients.joinToString(),
                moreDetails?.ingredientLines,
                recipe.rating.toString(),
                recipe.totalTimeInSeconds.toString(),
                getValidImageURL(moreDetails?.images),
                moreDetails?.source?.sourceRecipeUrl
            )
        }
    }

    private fun getValidImageURL(images: List<RecipeObject.Image>?): String {
        return if (images != null) {
            for (image in images) {
                if (image.hostedLargeUrl != null) return image.hostedLargeUrl
                else if (image.hostedMediumUrl != null) return image.hostedMediumUrl
            }
            return defaultUrl
        } else {
            return defaultUrl
        }
    }

}
