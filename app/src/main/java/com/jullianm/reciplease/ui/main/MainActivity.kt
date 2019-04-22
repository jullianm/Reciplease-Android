package com.jullianm.reciplease.ui.main

import android.graphics.Color
import android.support.v7.app.AppCompatActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.os.Bundle
import android.support.design.widget.TabLayout
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

class MainActivity : AppCompatActivity() {


    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null
    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        title = "Reciplease"

        tabLayout = findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(container)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        private val pageTitles = listOf("Search", "Favorites")

        override fun getItem(position: Int): Fragment {
            val tabItemSelected = if (TabItem.SEARCH.ordinal == position) TabItem.SEARCH else TabItem.FAVORITES
            return when (tabItemSelected) {
                TabItem.SEARCH -> SearchFragment.newInstance()
                TabItem.FAVORITES -> FavoritesFragment.newInstance()
            }
        }

        override fun getCount(): Int {
            return pageTitles.count()
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return pageTitles[position]
        }
    }

}
