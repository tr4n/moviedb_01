package com.tr4n.moviedb.data.source

interface AuthenticationRepository {
    suspend fun getRequestToken() : String
}