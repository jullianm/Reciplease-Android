package com.jullianm.reciplease.app

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jullianm.reciplease.recipes.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Injection {

    fun provideRecipesNetworkManager(): RecipeNetwork = RecipesNetworkManager

    private fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.edamam.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }

    fun provideEdamamAPI(): EdamamAPI = provideRetrofit().create(EdamamAPI::class.java)

    fun provideRecipesDataManager(): RecipeData = RecipesDataManager

    fun provideAppDatabase(context: Context): AppDatabase {
        return Room
            .databaseBuilder(context, AppDatabase::class.java, "recipes-database")
            .build()
    }

}