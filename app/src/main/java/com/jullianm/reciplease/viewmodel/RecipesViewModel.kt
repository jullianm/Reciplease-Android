package com.jullianm.reciplease.viewmodel

import android.arch.lifecycle.ViewModel;
import com.jullianm.reciplease.app.Injection
import com.jullianm.reciplease.recipes.RecipesHandler
import java.util.ArrayList

class RecipesViewModel : ViewModel() {
    private val recipesManager = Injection.provideRecipesManager()

    fun getRecipes(ingredients: ArrayList<String>, callback: RecipesHandler)= recipesManager.getRecipes(ingredients, callback)
}
