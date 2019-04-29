package com.jullianm.reciplease.ui.search.recipes

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
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
import com.jullianm.reciplease.model.RecyclerViewClickListener
import com.jullianm.reciplease.recipes.RecipesManager
import com.jullianm.reciplease.ui.search.recipedetails.RecipeDetailsActivity
import com.jullianm.reciplease.viewmodel.RecipesViewModel

class RecipesFragment : Fragment(), RecyclerViewClickListener {

    private lateinit var recipesList: RecyclerView
    private var recipes = ArrayList<RecipeModel>()
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
            recipesList.adapter = RecipesListRecyclerViewAdapter(ArrayList(), this)
        }
        val ingredients = arguments!!.getStringArrayList("INGREDIENTS_EXTRA")!!

        recipesViewModel.getRecipes(ingredients) { recipes ->
            if (recipes != null) recipesList.adapter =  RecipesListRecyclerViewAdapter(recipes, this)
            this.recipes = recipes
            progressBar.visibility = View.GONE
        }

    }

    override fun recyclerViewListClicked(v: View, position: Int) {
        val intent = Intent(context, RecipeDetailsActivity::class.java)
        intent.putExtra(INTENT_NAME, recipes[position])
        startActivity(intent)
    }

    companion object {
        fun newInstance() = RecipesFragment()
        const val INTENT_NAME = "RECIPE"
    }
}
