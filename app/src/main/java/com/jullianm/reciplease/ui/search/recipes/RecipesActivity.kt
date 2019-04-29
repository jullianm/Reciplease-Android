package com.jullianm.reciplease.ui.search.recipes

import android.os.Bundle
import com.jullianm.reciplease.R
import com.jullianm.reciplease.ui.tab.TabActivity

class RecipesActivity : TabActivity(R.id.recipes_container) {

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_recipes)
        super.onCreate(savedInstanceState)

        val fragment = RecipesFragment.newInstance()
        val extras = intent.getStringArrayListExtra("INGREDIENTS_EXTRA")
        val args = Bundle()
        args.putStringArrayList("INGREDIENTS_EXTRA", extras)
        fragment.arguments =  args

        searchTabFragment = fragment

        switchToFragment(searchTabFragment)
    }


}
