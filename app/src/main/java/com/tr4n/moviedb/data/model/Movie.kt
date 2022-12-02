package com.tr4n.moviedb.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName


data class Movie(
    @SerializedName("id")
    val id : Long,
    @SerializedName("poster_path")
    val url : String = "",
    @SerializedName("vote_average")
    val votAverage : Float?,
    @SerializedName("original_title")
    val originalTitle : String = "",
    @SerializedName("original_language")
    val originalLanguage : String = "",
    @SerializedName("release_date")
    val releaseDate : String = ""
) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }

        }
    }
}
