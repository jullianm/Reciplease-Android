package com.jullianm.reciplease.ui.tab.search.ingredients

import android.view.View
import android.widget.TextView
import com.jullianm.reciplease.R

class IngredientsListViewHolder(itemView: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {

    val ingredient = itemView.findViewById(R.id.ingredientString) as TextView

}