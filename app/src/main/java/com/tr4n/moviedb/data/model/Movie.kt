package com.tr4n.moviedb.data.model

import androidx.recyclerview.widget.DiffUtil

data class Movie(
    val id : Long,
    val url : String = ""
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
