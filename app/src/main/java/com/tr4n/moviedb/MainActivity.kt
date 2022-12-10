package com.tr4n.moviedb

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tr4n.moviedb.ui.NavTab
import com.tr4n.moviedb.ui.favorite.FavoriteFragment
import com.tr4n.moviedb.ui.home.HomeFragment
import com.tr4n.moviedb.ui.search.SearchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val homeFragment = HomeFragment()
    private  val searchFragment = SearchFragment()
    private val favoriteFragment = FavoriteFragment()
    private var currentTab = NavTab.Home.position

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navButtonHome.isChecked = true
        navView.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                NavTab.Home.checkId -> {
                    currentTab = NavTab.Home.position
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container, homeFragment)
                        .addToBackStack(null)
                        .commit()
                }
                NavTab.Search.checkId -> {
                    currentTab = NavTab.Search.position
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container, searchFragment)
                        .addToBackStack(null)
                        .commit()
                }
                NavTab.Favorite.checkId -> {
                    currentTab = NavTab.Favorite.position
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_container, favoriteFragment)
                        .addToBackStack(null)
                        .commit()
                }
            }
        }

    }
}
