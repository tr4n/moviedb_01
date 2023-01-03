package com.tr4n.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class Genre(
    @SerializedName("id")
    val id : Int,
    @SerializedName("name")
    val name : String
)