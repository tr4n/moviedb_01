package com.tr4n.moviedb.data.source

import com.tr4n.moviedb.data.model.MoviesResponse

interface MoviesRepository {
    suspend fun getTabMovie(tab : String, language : String, page : Int) : MoviesResponse
}