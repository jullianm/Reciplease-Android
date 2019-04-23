package com.jullianm.reciplease.ui.search

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

    }
}
