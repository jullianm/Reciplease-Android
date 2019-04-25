package com.jullianm.reciplease.recipes

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecipeObject
import com.jullianm.reciplease.model.RecipesObjects
import com.jullianm.reciplease.ui.search.recipes.RecipesFragment
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

object RecipesManager: Recipe, CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + Job()

    private const val appId = "dda042d2"
    private const val appKey = "6b4afceb278126620adba7ff792f8b86"

    private val api = Injection.provideYummlyAPI()

    override fun getRecipes(ingredients: List<String>): LiveData<List<RecipeModel>> {
        val liveData = MutableLiveData<List<RecipeModel>>()

        launch {

            try {
                api.getRecipes(appId, appKey, ingredients, 20).await().body().let {
                    val all = ArrayList<RecipeModel>()

                    for (match in it!!.matches) {

                        api.getRecipe(match.id, appId, appKey).await().body().let {

                            all.add(
                                RecipeModel(
                                    match.recipeName,
                                    match.ingredients.toString(),
                                    it?.ingredientLines,
                                    match.rating.toString(),
                                    match.totalTimeInSeconds.toString(),
                                    null,
                                    it?.source?.sourceRecipeUrl

                                )
                            )

                        }
                    }

                    liveData.value = all
                    coroutineContext.cancel()
                }

            } catch (exception: Exception) {
                Log.e("ERROR", exception.toString())
            }

        }
        return liveData
    }


}