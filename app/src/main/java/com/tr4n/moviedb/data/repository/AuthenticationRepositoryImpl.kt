package com.tr4n.moviedb.data.repository

import com.tr4n.moviedb.data.source.AuthenticationRepository
import com.tr4n.moviedb.data.source.remote.api.ApiService

class AuthenticationRepositoryImpl(private val apiService: ApiService) : AuthenticationRepository {
    override suspend fun getRequestToken(): String {
        return apiService.getRequestToken().requestToken
    }
}