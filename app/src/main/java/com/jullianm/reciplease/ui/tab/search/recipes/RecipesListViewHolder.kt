package com.jullianm.reciplease.ui.tab.search.recipes

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecyclerViewClickListener

class RecipesListViewHolder(itemView: View, val itemListener: RecyclerViewClickListener): RecyclerView.ViewHolder(itemView), View.OnClickListener {

    val recipeImage = itemView.findViewById<ImageView>(R.id.recipe_image)
    val recipeName = itemView.findViewById<TextView>(R.id.recipe_name)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) itemListener.recyclerViewListClicked(v, this.layoutPosition)
    }

}