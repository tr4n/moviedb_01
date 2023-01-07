package com.tr4n.moviedb.data.repository

import com.tr4n.moviedb.data.model.GenresResponse
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.model.MovieReviewsResponse
import com.tr4n.moviedb.data.model.MoviesResponse
import com.tr4n.moviedb.data.source.MoviesRepository
import com.tr4n.moviedb.data.source.local.room.AppDatabase
import com.tr4n.moviedb.data.source.remote.api.ApiService

class MoviesRepositoryImpl(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
    ) : MoviesRepository {
    override suspend fun getTabMovie(tab: String, page: Int): MoviesResponse {
        return apiService.getTabMovie(tab, page)
    }

    override suspend fun getMovieSearchResults(query: String, page: Int): MoviesResponse {
        return apiService.getMovieSearchResults(query, page)
    }

    override suspend fun getMovieDetails(movieId: Long): Movie {
        return apiService.getMovieDetails(movieId)
    }

    override suspend fun getGenres(): GenresResponse {
        return apiService.getGenres()
    }

    override suspend fun getMovieReviews(movieId: Long, page: Int): MovieReviewsResponse {
        return apiService.getMovieReviews(movieId, page)
    }

    override suspend fun getMovieSimilar(movieId: Long, page: Int): MoviesResponse {
        return apiService.getMovieSimilar(movieId, page)
    }

    override suspend fun getAllWatchList(): List<Movie> {
        return appDatabase.movieDao().getAll()
    }

    override suspend fun insertWatchList(movie: Movie) {
        appDatabase.movieDao().insert(movie)
    }

    override suspend fun deleteWatchListById(id: Long) {
        return appDatabase.movieDao().deleteById(id)
    }

    override suspend fun getWatchListById(id: Long): Movie {
        return appDatabase.movieDao().getById(id)
    }

}
