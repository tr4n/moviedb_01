package com.tr4n.moviedb.ui

import com.tr4n.moviedb.R

enum class NavTab(val checkId: Int, val position : Int) {
    Home(R.id.navButtonHome, 1),
    Search(R.id.navButtonSearch, 2),
    Favorite(R.id.navButtonFavorite, 3)
}
