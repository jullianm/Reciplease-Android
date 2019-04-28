package com.jullianm.reciplease.ui.search.recipes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.recipes.RecipesManager
import com.jullianm.reciplease.viewmodel.RecipesViewModel

class RecipesFragment : Fragment() {

    private lateinit var recipesList: RecyclerView
    private lateinit var recipesViewModel: RecipesViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)

        view?.let {
            progressBar = it.findViewById(R.id.recipes_progress_bar)
            recipesList = it.findViewById(R.id.recipes_list)
            recipesList.layoutManager = LinearLayoutManager(activity)
            recipesList.adapter = RecipesListRecyclerViewAdapter(ArrayList())
        }
        val ingredients = arguments!!.getStringArrayList("INGREDIENTS_EXTRA")!!

        recipesViewModel.getRecipes(ingredients) { recipes ->
            if (recipes != null) recipesList.adapter =  RecipesListRecyclerViewAdapter(recipes)
            progressBar.visibility = View.GONE
        }

    }

    companion object {
        fun newInstance() = RecipesFragment()
    }
}
