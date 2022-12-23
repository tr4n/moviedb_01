package com.tr4n.moviedb.data.model

import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("name")
    val name : String = "",
    @SerializedName("username")
    val userName : String = "",
    @SerializedName("avatar_path")
    val avatarPath : String = "",
    @SerializedName("rating")
    val rating: Int
)
