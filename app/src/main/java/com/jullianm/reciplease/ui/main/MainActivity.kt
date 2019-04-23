package com.jullianm.reciplease.ui.main

import android.graphics.Color
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.design.widget.TabLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.TabItem
import com.jullianm.reciplease.ui.favorites.FavoritesFragment
import com.jullianm.reciplease.ui.search.SearchFragment

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*

open class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tabs)

        switchToFragment(SearchFragment.newInstance())

        tabLayout.addOnTabSelectedListener(TabListener())

    }

    open fun switchToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    inner class TabListener: TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab) {
            val tabItemSelected = if (TabItem.SEARCH.ordinal == tab.position) TabItem.SEARCH else TabItem.FAVORITES

            val fragment = when (tabItemSelected) {
                TabItem.SEARCH -> SearchFragment.newInstance()
                TabItem.FAVORITES -> FavoritesFragment.newInstance()
            }

            switchToFragment(fragment)

        }

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }
        override fun onTabReselected(tab: TabLayout.Tab) {

        }

    }

}




