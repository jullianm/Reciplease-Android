package com.jullianm.reciplease.ui.tab.search.ingredients

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText

import com.jullianm.reciplease.R
import com.jullianm.reciplease.ui.tab.search.recipes.RecipesActivity

class SearchFragment : androidx.fragment.app.Fragment() {

    private lateinit var ingredientsList: androidx.recyclerview.widget.RecyclerView
    private lateinit var addButton: Button
    private lateinit var clearButton: Button
    private lateinit var ingredientInput: EditText
    private lateinit var searchButton: Button

    private val lists = ArrayList<String>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            ingredientsList = it.findViewById(R.id.ingredients_list)
            ingredientsList.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
            ingredientsList.adapter =
                IngredientsListRecyclerViewAdapter(lists)

            addButton = it.findViewById(R.id.add_button)
            clearButton = it.findViewById(R.id.clear_button)
            ingredientInput = it.findViewById(R.id.ingredient_input)
            searchButton = it.findViewById(R.id.direction_button)

            addButton.setOnClickListener {
                addIngredient()
            }

            clearButton.setOnClickListener {
                clear()
            }

            ingredientInput.setOnEditorActionListener { v, actionId, event ->
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    addIngredient()
                    return@setOnEditorActionListener true
                }
                 false
            }

            searchButton.setOnClickListener {
                val intent = Intent(context, RecipesActivity::class.java)
                intent.putExtra("INGREDIENTS_EXTRA", lists)
                startActivity(intent)
            }

        }
    }

    private fun addIngredient() {
        lists.add(ingredientInput.text.toString())
        ingredientInput.text.clear()
        ingredientsList.adapter = IngredientsListRecyclerViewAdapter(lists)
        ingredientInput.hideKeyboard()
    }

    private fun clear() {
        lists.clear()
        ingredientsList.adapter = IngredientsListRecyclerViewAdapter(lists)
    }

    companion object {
        fun newInstance() = SearchFragment()
    }
}

fun View.hideKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}