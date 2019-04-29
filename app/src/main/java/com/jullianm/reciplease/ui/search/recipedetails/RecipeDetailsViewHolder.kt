package com.jullianm.reciplease.ui.search.recipedetails

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.jullianm.reciplease.R

class RecipeDetailsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val ingredientDetail = itemView.findViewById<TextView>(R.id.ingredient_detail)
}