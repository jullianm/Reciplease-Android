package com.jullianm.reciplease.ui.search.recipedetails

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.RecipeModel
import com.jullianm.reciplease.ui.search.recipes.RecipesActivity
import com.jullianm.reciplease.ui.search.recipes.RecipesFragment
import com.jullianm.reciplease.ui.tab.TabActivity

class RecipeDetailsActivity : TabActivity(R.id.recipes_details_container) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipes_details)
        super.onCreate(savedInstanceState)

        val fragment = RecipeDetailsFragment()
        val extra = intent.getParcelableExtra<RecipeModel>(RecipesFragment.INTENT_NAME)
        val args = Bundle()
        args.putParcelable(RecipesFragment.INTENT_NAME, extra)
        fragment.arguments = args

        searchTabFragment = fragment

        switchToFragment(fragment)
    }
}
