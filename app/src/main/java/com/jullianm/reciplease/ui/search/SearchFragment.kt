package com.jullianm.reciplease.ui.search

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

import com.jullianm.reciplease.R


class SearchFragment : Fragment() {

    private lateinit var ingredientsList: RecyclerView
    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var ingredientInput: EditText
    private lateinit var searchButton: Button

    val lists = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            ingredientsList = it.findViewById(R.id.ingredients_list)
            ingredientsList.layoutManager = LinearLayoutManager(activity)
            ingredientsList.adapter = IngredientsListRecyclerViewAdapter(lists)

            addButton = it.findViewById(R.id.add_button)
            clearButton = it.findViewById(R.id.clear_button)
            ingredientInput = it.findViewById(R.id.ingredient_input)
            searchButton = it.findViewById(R.id.search_button)

            addButton.setOnClickListener {
                lists.add(ingredientInput.text.toString())
                ingredientInput.text.clear()
                ingredientsList.adapter = IngredientsListRecyclerViewAdapter(lists)
            }

            clearButton.setOnClickListener {
                lists.clear()
                ingredientsList.adapter = IngredientsListRecyclerViewAdapter(lists)
            }

            searchButton.setOnClickListener {
                val intent = Intent(context, RecipesActivity::class.java)
                startActivity(intent)
            }

        }
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}
