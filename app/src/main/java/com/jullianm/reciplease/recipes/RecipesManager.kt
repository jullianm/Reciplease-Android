package com.jullianm.reciplease.recipes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.net.Uri
import android.util.Log
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecipeObject
import com.jullianm.reciplease.model.RecipesObjects
import com.jullianm.reciplease.ui.search.recipes.RecipesFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.URI
import java.net.URL
import kotlin.coroutines.CoroutineContext

typealias RecipesHandler = (ArrayList<RecipeModel>) -> Unit

object RecipesManager: Recipe {

    private const val appId = "dda042d2"
    private const val appKey = "6b4afceb278126620adba7ff792f8b86"
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