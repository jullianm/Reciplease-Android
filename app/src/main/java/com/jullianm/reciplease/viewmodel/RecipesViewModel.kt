package com.jullianm.reciplease.viewmodel

import android.arch.lifecycle.ViewModel;
import com.jullianm.reciplease.app.Injection

class RecipesViewModel : ViewModel() {
    private val recipesManager = Injection.provideRecipesManager()

    fun getRecipes(ingredients: List<String>) = recipesManager.getRecipes(ingredients)
}
