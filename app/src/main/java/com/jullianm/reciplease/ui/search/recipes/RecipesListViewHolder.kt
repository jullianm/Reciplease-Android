package com.jullianm.reciplease.ui.search.recipes

import android.media.Image
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.jullianm.reciplease.R
import org.w3c.dom.Text

class RecipesListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val recipeImage = itemView.findViewById<ImageView>(R.id.recipe_image)
    val recipeName = itemView.findViewById<TextView>(R.id.recipe_name)
    val recipeIngredients = itemView.findViewById<TextView>(R.id.recipe_ingredient)

}