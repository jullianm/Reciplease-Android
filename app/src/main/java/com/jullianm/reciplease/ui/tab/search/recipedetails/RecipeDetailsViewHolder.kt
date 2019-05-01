package com.jullianm.reciplease.ui.tab.search.recipedetails

import android.view.View
import android.widget.TextView
import com.jullianm.reciplease.R

class RecipeDetailsViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    val ingredientDetail = itemView.findViewById<TextView>(R.id.ingredient_detail)
}