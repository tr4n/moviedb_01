package com.tr4n.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class MovieDetail(
    @SerializedName("id")
    val id : Long,
    @SerializedName("backdrop_path")
    val backdropPath : String = "",
    @SerializedName("poster_path")
    val posterPath : String = "",
    @SerializedName("genres")
    val genres : List<Genre>?,
    @SerializedName("original_title")
    val originalTitle : String = "",
    @SerializedName("overview")
    val overview : String = "",
    @SerializedName("status")
    val status : String = "",
    @SerializedName("release_date")
    val releaseDate : String = "",
    @SerializedName("runtime")
    val runtime : Int,
    @SerializedName("vote_average")
    val voteAverage : Float
)
