package com.tr4n.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class MovieReviewsResponse(
    @SerializedName("id")
    val id : Long,
    @SerializedName("page")
    val page : Int,
    @SerializedName("results")
    val results : List<Review>?
)
