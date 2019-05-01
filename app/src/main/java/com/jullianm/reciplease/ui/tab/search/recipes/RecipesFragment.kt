package com.jullianm.reciplease.ui.tab.search.recipes

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecyclerViewClickListener
import com.jullianm.reciplease.model.TabItem
import com.jullianm.reciplease.ui.tab.search.recipedetails.RecipeDetailsActivity
import com.jullianm.reciplease.viewmodel.RecipesViewModel

open class RecipesFragment : androidx.fragment.app.Fragment(), RecyclerViewClickListener {

    lateinit var recipesList: androidx.recyclerview.widget.RecyclerView
    lateinit var recipes: List<RecipeModel>
    lateinit var recipesViewModel: RecipesViewModel
    lateinit var progressBar: ProgressBar
    lateinit var tabItem: TabItem

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.recipes_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)

        recipes = emptyList()

        tabItem = TabItem.SEARCH

        progressBar = view.findViewById(R.id.recipes_progress_bar)
        recipesList = view.findViewById(R.id.recipes_list)
        recipesList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recipesList.adapter = RecipesListRecyclerViewAdapter(recipes, this)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        fetchData()
    }

    open fun fetchData() {
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
        intent.putExtra(TAB_ITEM, tabItem.ordinal)
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
             fetchData()
        }
    }

    companion object {
        fun newInstance() = RecipesFragment()
        const val INTENT_NAME = "RECIPE"
        const val TAB_ITEM = "TAB_ITEM"
        const val REQUEST_CODE = 100
    }
}
