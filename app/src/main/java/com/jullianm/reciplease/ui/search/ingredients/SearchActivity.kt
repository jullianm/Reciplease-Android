package com.jullianm.reciplease.ui.search.ingredients

import android.os.Bundle
import android.view.Menu
import com.jullianm.reciplease.R
import com.jullianm.reciplease.ui.TabActivity

class SearchActivity : TabActivity(R.id.container) {

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)

        super.onCreate(savedInstanceState)

        searchTabFragment = SearchFragment.newInstance()

        switchToFragment(searchTabFragment)

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


}




