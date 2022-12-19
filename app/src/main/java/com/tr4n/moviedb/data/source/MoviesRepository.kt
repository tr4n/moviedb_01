package com.tr4n.moviedb.data.source

import com.tr4n.moviedb.data.model.MoviesResponse

interface MoviesRepository {
    suspend fun getTabMovie(tab : String, page : Int) : MoviesResponse
    suspend fun getMovieSearchResults(query: String, page: Int) : MoviesResponse
}
