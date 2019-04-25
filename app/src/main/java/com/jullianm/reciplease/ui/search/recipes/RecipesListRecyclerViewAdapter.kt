package com.jullianm.reciplease.ui.search.recipes

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.jullianm.reciplease.R
import kotlinx.android.synthetic.main.recipes_list_view_holder.view.*
import java.util.zip.Inflater

class RecipesListRecyclerViewAdapter(val recipesList: ArrayList<String>): RecyclerView.Adapter<RecipesListViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecipesListViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.recipes_list_view_holder, p0, false)
        return RecipesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 6
    }

    override fun onBindViewHolder(p0: RecipesListViewHolder, p1: Int) {
        p0.recipeName.text = ""
    }
}
