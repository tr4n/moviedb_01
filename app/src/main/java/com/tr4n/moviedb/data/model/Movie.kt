package com.tr4n.moviedb.data.model

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie (
    @PrimaryKey
    @SerializedName("id")
    var id : Long = 0L,
    @SerializedName("poster_path")
    var posterPath : String = "",
    @SerializedName("backdrop_path")
    var backdropPath : String = "",
    @SerializedName("vote_average")
    var voteAverage : Float = 0F,
    @SerializedName("original_title")
    var originalTitle : String = "",
    @SerializedName("title")
    var title : String = "",
    @SerializedName("genre_ids")
    @Ignore
    var genreIds : List<Int> = emptyList(),
    @SerializedName("genres")
    var genres : List<Genre> = emptyList(),
    @SerializedName("original_language")
    var originalLanguage : String = "",
    @SerializedName("release_date")
    var releaseDate : String = "",
    @SerializedName("runtime")
    var runtime : Int = 0,
    @SerializedName("overview")
    var overview : String = ""
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
