package com.jullianm.reciplease.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.jullianm.reciplease.R
import com.jullianm.reciplease.model.TabItem
import com.jullianm.reciplease.ui.favorites.FavoritesFragment

open class TabActivity(private val fragmentContainer: Int): AppCompatActivity() {

    lateinit var searchTabFragment: Fragment
    private var favoritesTabFragment = FavoritesFragment.newInstance()
    lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabLayout = findViewById(R.id.tabs)
        tabLayout.addOnTabSelectedListener(TabListener())
    }

    open fun switchToFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(fragmentContainer, fragment)
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

        override fun onTabUnselected(tab: TabLayout.Tab) {

        }
        override fun onTabReselected(tab: TabLayout.Tab) {

        }

    }
}