package com.tr4n.moviedb.data.source.remote.api

import com.tr4n.moviedb.data.model.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{tab}")
    suspend fun getTabMovie(
        @Path("tab") tab : String,
        @Query("page") page : Int
        ) : MoviesResponse
    @GET("search/movie")
    suspend fun getMovieSearchResults(
        @Query("query") query : String,
        @Query("page") page: Int
    ) : MoviesResponse
}
