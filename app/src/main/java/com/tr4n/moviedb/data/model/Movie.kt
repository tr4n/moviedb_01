package com.tr4n.moviedb.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    val id : Long,
    @SerializedName("poster_path")
    val posterPath : String = "",
    @SerializedName("backdrop_path")
    val backdropPath : String = "",
    @SerializedName("vote_average")
    val voteAverage : Float?,
    @SerializedName("original_title")
    val originalTitle : String = "",
    @SerializedName("title")
    val title : String = "",
    @SerializedName("genre_ids")
    val genreIds : List<Int> ?,
    @SerializedName("genres")
    var genres : List<Genre> ?,
    @SerializedName("original_language")
    val originalLanguage : String = "",
    @SerializedName("release_date")
    val releaseDate : String = "",
    @SerializedName("runtime")
    val runtime : Int,
    @SerializedName("overview")
    val overview : String = ""
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
