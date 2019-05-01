package com.jullianm.reciplease.ui.tab.favorites

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.TabItem
import com.jullianm.reciplease.recipes.AppDatabase
import com.jullianm.reciplease.ui.tab.search.recipes.RecipesFragment
import com.jullianm.reciplease.ui.tab.search.recipes.RecipesListRecyclerViewAdapter

class FavoritesFragment : RecipesFragment() {

    private lateinit var database: AppDatabase

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tabItem = TabItem.FAVORITES
    }

    override fun fetchData() {
        context?.let { context ->
            AsyncTask.execute {
                run {
                    recipes = recipesViewModel.getFavoritesRecipes(context)
                    activity?.runOnUiThread {
                        recipesList.adapter =  RecipesListRecyclerViewAdapter(recipes, this)
                        progressBar.visibility = View.GONE
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = FavoritesFragment()
    }
}
