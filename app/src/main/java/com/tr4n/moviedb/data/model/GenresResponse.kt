package com.tr4n.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("genres")
    val genres : List<Genre>?
)
