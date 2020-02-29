package com.jullianm.reciplease.ui.tab.search.recipes

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecyclerViewClickListener
import com.squareup.picasso.Picasso

class RecipesListRecyclerViewAdapter(val recipesList: List<RecipeModel>, val listener: RecyclerViewClickListener): androidx.recyclerview.widget.RecyclerView.Adapter<RecipesListViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecipesListViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.recipes_list_view_holder, p0, false)
        return RecipesListViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return recipesList.size
    }

    override fun onBindViewHolder(p0: RecipesListViewHolder, p1: Int) {
        p0.recipeName.text = recipesList[p1].name
        Picasso.get()
                .load(recipesList[p1].image)
                .fit()
                .into(p0.recipeImage)
    }

}
