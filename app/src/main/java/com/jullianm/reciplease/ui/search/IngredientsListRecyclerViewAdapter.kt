package com.jullianm.reciplease.ui.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jullianm.reciplease.R

class IngredientsListRecyclerViewAdapter(val ingredientsList: ArrayList<String>) : RecyclerView.Adapter<IngredientsListViewHolder>() {

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

