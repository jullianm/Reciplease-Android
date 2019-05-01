package com.jullianm.reciplease.ui.tab.search.ingredients

import android.view.LayoutInflater
import android.view.ViewGroup
import com.jullianm.reciplease.R

class IngredientsListRecyclerViewAdapter(val ingredientsList: ArrayList<String>) : androidx.recyclerview.widget.RecyclerView.Adapter<IngredientsListViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): IngredientsListViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.ingredient_list_view_holder, p0, false)
        return IngredientsListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(p0: IngredientsListViewHolder, p1: Int) {
        p0.ingredient.text = " - ${ingredientsList[p1]}"
    }
}

