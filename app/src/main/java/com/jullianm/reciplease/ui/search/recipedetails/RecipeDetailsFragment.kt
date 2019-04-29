package com.jullianm.reciplease.ui.search.recipedetails

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.RecyclerViewClickListener
import com.jullianm.reciplease.ui.search.recipes.RecipesFragment
import com.squareup.picasso.Picasso

class RecipeDetailsFragment : Fragment() {

    lateinit var recipeIngredientsRecyclerView: RecyclerView
    lateinit var recipeImage: ImageView
    lateinit var recipeTitle: TextView
    lateinit var directionsButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeImage = view.findViewById(R.id.recipe_image_view)
        recipeTitle = view.findViewById(R.id.recipe_title)
        directionsButton = view.findViewById(R.id.direction_button)

        val recipe = arguments!!.getParcelable<RecipeModel>(RecipesFragment.INTENT_NAME)

        recipeIngredientsRecyclerView = view.findViewById<RecyclerView>(R.id.recipe_ingredients_recycler_view)
        recipeIngredientsRecyclerView.layoutManager = LinearLayoutManager(activity)
        recipeIngredientsRecyclerView.adapter = RecipeDetailsRecyclerViewAdapter(recipe.portions!!)

        Picasso.get()
            .load(recipe.image)
            .fit()
            .into(recipeImage)

        recipeTitle.text = recipe.name

        directionsButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.instructions))
            startActivity(browserIntent)
        }

    }


    companion object {
        fun newInstance() = RecipeDetailsFragment()
    }
}
