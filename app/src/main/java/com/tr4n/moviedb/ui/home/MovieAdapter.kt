package com.tr4n.moviedb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tr4n.moviedb.R
import com.tr4n.moviedb.data.model.Movie
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(Movie.diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    class MovieViewHolder(
        itemView : View
    ) : RecyclerView.ViewHolder(itemView) {
        private var itemData : Movie ?= null
        fun binData(movie: Movie) {
            itemData = movie
            itemView.run {
                Glide.with(this)
                    .load(itemData?.url)
                    .into(imageViewMovie)
            }
        }

    }
}
