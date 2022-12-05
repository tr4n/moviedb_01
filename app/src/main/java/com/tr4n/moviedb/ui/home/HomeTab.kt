package com.tr4n.moviedb.ui.home

import com.tr4n.moviedb.R

enum class HomeTab(val position : Int, val tabNameRes : String, val tabName : Int) {
    NOW_PLAYING(0, "now_playing", R.string.name_tab_now_playing),
    UPCOMING(1, "upcoming", R.string.name_tab_upcoming),
    TOP_RATED(2, "top_rated", R.string.name_tab_top_rated),
    POPULAR(3, "popular", R.string.name_tab_popular)
}
