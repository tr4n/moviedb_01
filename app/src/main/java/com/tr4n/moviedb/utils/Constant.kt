package com.tr4n.moviedb.utils

import com.tr4n.moviedb.BuildConfig

object Constant {
    const val BASE_URL = "https://api.themoviedb.org/"
    const val BASE_URL_IMAGE = "https://image.tmdb.org/t/p/original/"
    const val API_KEY = BuildConfig.API_KEY
    const val TAB_NOW_PLAYING = "now_playing"
    const val TAB_UPCOMING = "upcoming"
    const val TAB_TOP_RATED = "top_rated"
    const val TAB_POPULAR = "popular"
    const val LANGUAGE = "en-US"
    const val ARGUMENT_KEY_TAB = "ARGUMENT_KEY_TAB"
}
