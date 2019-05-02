package com.jullianm.reciplease.ui.tab.search.recipedetails

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.widget.ImageViewCompat

import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.model.TabItem
import com.jullianm.reciplease.ui.tab.search.recipes.RecipesFragment
import com.jullianm.reciplease.viewmodel.RecipesViewModel
import com.squareup.picasso.Picasso

interface OnItemRemovedListener {
    fun itemIsRemoved()
}

class RecipeDetailsFragment : androidx.fragment.app.Fragment() {

    lateinit var recipeIngredientsRecyclerView: androidx.recyclerview.widget.RecyclerView
    lateinit var recipeImage: ImageView
    lateinit var recipeTitle: TextView
    lateinit var directionsButton: Button
    lateinit var favoritesButton: ImageButton
    lateinit var recipesViewModel: RecipesViewModel
    lateinit var recipe: RecipeModel
    lateinit var tabItem: TabItem
    private var isFirstOccurrence = true
    private lateinit var itemRemovalListener: OnItemRemovedListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        itemRemovalListener = context as OnItemRemovedListener
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_recipes_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel::class.java)

        setupViews(view)

        setupActions()

    }

    private fun setupViews(view: View) {
        recipeImage = view.findViewById(R.id.recipe_image_view)
        recipeTitle = view.findViewById(R.id.recipe_title)
        directionsButton = view.findViewById(R.id.direction_button)
        favoritesButton = view.findViewById(R.id.favorites_button)

        recipe = arguments!!.getParcelable<RecipeModel>(RecipesFragment.INTENT_NAME)

        val tabItemOrdinal = arguments!!.getInt(RecipesFragment.TAB_ITEM)
        tabItem = when (tabItemOrdinal) {
            0 -> TabItem.SEARCH
            else -> TabItem.FAVORITES
        }

        val color = if (tabItem == TabItem.SEARCH) android.R.color.white else android.R.color.holo_green_light
        DrawableCompat.setTint(favoritesButton.drawable, ContextCompat.getColor(context!!, color))

        recipeIngredientsRecyclerView = view.findViewById(R.id.recipe_ingredients_recycler_view)
        recipeIngredientsRecyclerView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(activity)
        recipeIngredientsRecyclerView.adapter = RecipeDetailsRecyclerViewAdapter(recipe.portions!!)

        Picasso.get().load(recipe.image).fit().into(recipeImage)

        recipeTitle.text = recipe.name
    }

    private fun setupActions() {
        directionsButton.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(recipe.instructions))
            startActivity(browserIntent)
        }

        favoritesButton.setOnClickListener {
            if (isFirstOccurrence) {
                val color = if (tabItem == TabItem.SEARCH) android.R.color.holo_green_light else android.R.color.white
                DrawableCompat.setTint(favoritesButton.drawable, ContextCompat.getColor(context!!, color))
                val text = if (tabItem == TabItem.SEARCH) "${resources.getString(R.string.recipe_added)}" else "${resources.getString(R.string.recipe_deleted)}"
                Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                if (tabItem == TabItem.SEARCH) saveRecipe() else deleteRecipe()
            }
        }
    }

    private fun saveRecipe() {
        context?.let { context ->
            AsyncTask.execute {
                run {
                    recipesViewModel.saveFavoriteRecipe(context, recipe)
                    isFirstOccurrence = false
                }
            }
        }
    }

    private fun deleteRecipe() {
        context?.let { context ->
            AsyncTask.execute {
                run {
                    recipesViewModel.deleteFavoriteRecipe(context, recipe)
                    isFirstOccurrence = false
                    itemRemovalListener.itemIsRemoved()
                }
            }
        }
    }

    companion object {
        fun newInstance() = RecipeDetailsFragment()
    }
}

