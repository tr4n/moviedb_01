package com.tr4n.moviedb.ui.detail

import com.tr4n.moviedb.R

enum class DetailTab(val position : Int, val tabName : Int) {
    ABOUT_MOVIE(0, R.string.text_tab_about_movie),
    REVIEWS(1, R.string.text_tab_reviews),
    SIMILAR(2, R.string.text_tab_similar_movie)
}
