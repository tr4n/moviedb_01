package com.tr4n.moviedb.data.model
import com.google.gson.annotations.SerializedName

data class AuthenticationTokenResponse(
    @SerializedName("success")
    val success : Boolean,
    @SerializedName("expires_at")
    val expiresAt : String = "",
    @SerializedName("request_token")
    val requestToken : String = ""
)
