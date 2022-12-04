package com.tr4n.moviedb.data.model

import com.google.gson.annotations.SerializedName


data class MoviesResponse(
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Movie>?
)
