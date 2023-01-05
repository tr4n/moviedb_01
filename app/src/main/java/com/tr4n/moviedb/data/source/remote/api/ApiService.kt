package com.tr4n.moviedb.data.source.remote.api

import com.tr4n.moviedb.data.model.GenresResponse
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.model.MovieReviewsResponse
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

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long
    ) : Movie

    @GET("genre/movie/list")
    suspend fun getGenres() : GenresResponse

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Long,
        @Query("page") page : Int
    ) : MovieReviewsResponse

    @GET("movie/{movie_id}/similar")
    suspend fun getMovieSimilar(
        @Path("movie_id") movieId: Long,
        @Query("page") page: Int
    ) : MoviesResponse

}
