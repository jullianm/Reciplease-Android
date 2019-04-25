package com.jullianm.reciplease.recipes

import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.model.RecipeObject
import com.jullianm.reciplease.model.RecipesObjects
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface YummlyAPI {

    @GET("v1/api/recipes")
    fun getRecipes(
        @Query("_app_id") appId: String,
        @Query("_app_key") appKey: String,
        @Query("q") q: List<String>,
        @Query("maxResult") maxResult: Int
    ): Deferred<Response<RecipesObjects>>

    @GET("v1/api/recipe/{id}")
    fun getRecipe(
        @Path("id") id: String,
        @Query("_app_id") appId: String,
        @Query("_app_key") appKey: String
    ): Deferred<Response<RecipeObject>>
}