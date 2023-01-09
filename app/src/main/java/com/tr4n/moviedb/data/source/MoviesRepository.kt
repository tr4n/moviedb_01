package com.tr4n.moviedb.data.source

import com.tr4n.moviedb.data.model.GenresResponse
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.model.MovieReviewsResponse
import com.tr4n.moviedb.data.model.MoviesResponse

interface MoviesRepository {
    suspend fun getTabMovie(tab : String, page : Int) : MoviesResponse
    suspend fun getMovieSearchResults(query: String, page: Int) : MoviesResponse
    suspend fun getMovieDetails(movieId : Long) : Movie
    suspend fun getGenres() : GenresResponse
    suspend fun getMovieReviews(movieId: Long, page: Int) : MovieReviewsResponse
    suspend fun getMovieSimilar(movieId: Long, page: Int) : MoviesResponse
    suspend fun getAllWatchList() : List<Movie>
    suspend fun insertWatchList(movie: Movie)
    suspend fun deleteWatchListById(id: Long)
    suspend fun getWatchListById(id: Long) : Movie
}
