package com.jullianm.reciplease.app

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jullianm.reciplease.recipes.Recipe
import com.jullianm.reciplease.recipes.RecipesManager
import com.jullianm.reciplease.recipes.YummlyAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    fun provideRecipesManager(): Recipe = RecipesManager

    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.yummly.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun provideYummlyAPI(): YummlyAPI = provideRetrofit().create(YummlyAPI::class.java)

}