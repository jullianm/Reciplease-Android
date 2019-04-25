package com.jullianm.reciplease.ui.search.recipes

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Window
import android.view.WindowManager
import com.jullianm.reciplease.R
import com.jullianm.reciplease.ui.main.MainActivity

class RecipesActivity : MainActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
    }

    override fun switchToFragment(fragment: Fragment) {
        val fragment = RecipesFragment.newInstance()
        val extras = intent.getStringArrayListExtra("INGREDIENTS_EXTRA")
        val args = Bundle()
        args.putStringArrayList("INGREDIENTS_EXTRA", extras)
        fragment.arguments =  args

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.recipes_container, fragment)
            .commit()
    }
}
