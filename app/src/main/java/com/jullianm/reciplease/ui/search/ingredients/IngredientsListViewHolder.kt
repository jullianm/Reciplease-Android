package com.jullianm.reciplease.ui.search.ingredients

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jullianm.reciplease.R
import kotlinx.android.synthetic.main.ingredient_list_view_holder.view.*

class IngredientsListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val ingredient = itemView.findViewById(R.id.ingredientString) as TextView

}