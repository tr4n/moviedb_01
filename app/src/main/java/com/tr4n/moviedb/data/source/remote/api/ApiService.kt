package com.tr4n.moviedb.data.source.remote.api

import com.tr4n.moviedb.data.model.AuthenticationTokenResponse
import com.tr4n.moviedb.data.model.MoviesResponse
import com.tr4n.moviedb.utils.Constant
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/authentication/token/new")
    suspend fun getRequestToken(
        @Query("api_key") api_key: String = Constant.API_KEY
    ) : AuthenticationTokenResponse

    @GET("/3/movie/{tab}")
    suspend fun getTabMovie(
        @Path("tab") tab : String,
        @Query("language") language : String,
        @Query("page") page : Int,
        @Query("api_key") api_key: String = Constant.API_KEY
        ) : MoviesResponse
}