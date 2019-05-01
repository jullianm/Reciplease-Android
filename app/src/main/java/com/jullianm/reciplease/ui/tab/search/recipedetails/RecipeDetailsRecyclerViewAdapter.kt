package com.jullianm.reciplease.ui.tab.search.recipedetails

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jullianm.reciplease.R

class RecipeDetailsRecyclerViewAdapter(val ingredientsDetails: List<String>): androidx.recyclerview.widget.RecyclerView.Adapter<RecipeDetailsViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecipeDetailsViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.recipe_details_view_holder, p0, false)
        return RecipeDetailsViewHolder(view)
    }

    override fun getItemCount(): Int {
        println(ingredientsDetails.count())
        return ingredientsDetails.count()
    }

    override fun onBindViewHolder(p0: RecipeDetailsViewHolder, p1: Int) {
        p0.ingredientDetail.text = "- ${ingredientsDetails[p1]}"
    }

}
