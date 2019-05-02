package com.jullianm.reciplease.ui.tab

import android.os.Bundle
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.TabItem
import com.jullianm.reciplease.ui.tab.favorites.FavoritesFragment
import com.jullianm.reciplease.ui.tab.search.ingredients.SearchFragment

// Base class for search and favorites activities
open class TabActivity: AppCompatActivity() {

    var searchTabFragment: Fragment = SearchFragment.newInstance()
    var favoritesTabFragment: Fragment = FavoritesFragment.newInstance()
    lateinit var tabLayout: TabLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabs)
        tabLayout.addOnTabSelectedListener(TabListener())

        switchToFragment()
    }

    open fun switchToFragment(fragment: androidx.fragment.app.Fragment = searchTabFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    inner class TabListener: TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab) {
            val tabItemSelected = if (TabItem.SEARCH.ordinal == tab.position) TabItem.SEARCH else TabItem.FAVORITES

            val fragment = when (tabItemSelected) {
                TabItem.SEARCH -> searchTabFragment
                TabItem.FAVORITES -> favoritesTabFragment
            }

            switchToFragment(fragment)

        }

        override fun onTabUnselected(tab: TabLayout.Tab) {}

        override fun onTabReselected(tab: TabLayout.Tab) {}

    }
}