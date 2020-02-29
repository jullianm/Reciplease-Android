package com.jullianm.reciplease.recipes

import com.jullianm.reciplease.model.RecipeObject
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EdamamAPI {

    @GET("search")
    fun getRecipes(
        @Query("_app_id") appId: String,
        @Query("_app_key") appKey: String,
        @Query("q") q: List<String>
    ): Deferred<Response<RecipeObject>>
}