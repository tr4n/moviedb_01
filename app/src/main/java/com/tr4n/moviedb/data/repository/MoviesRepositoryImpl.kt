package com.tr4n.moviedb.data.repository

import com.tr4n.moviedb.data.model.MoviesResponse
import com.tr4n.moviedb.data.source.MoviesRepository
import com.tr4n.moviedb.data.source.remote.api.ApiService

class MoviesRepositoryImpl(private val apiService: ApiService) : MoviesRepository {
    override suspend fun getTabMovie(tab: String, page: Int): MoviesResponse {
        return apiService.getTabMovie(tab, page)
    }
}
