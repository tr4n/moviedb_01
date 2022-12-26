package com.tr4n.moviedb.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tr4n.moviedb.R
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter : ListAdapter<Movie, MovieAdapter.MovieViewHolder>(Movie.diffCallback) {

    var onItemSelected : (Long) -> Unit = {}
    var currentPage = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieViewHolder(itemView, onItemSelected )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.binData(getItem(position), position, currentPage)
    }

    class MovieViewHolder(
        itemView : View,
        onItemSelected : (Long) -> Unit,
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                itemData?.let { movie ->
                    onItemSelected(movie.id)
                }
            }
        }
        private var itemData : Movie ?= null
        fun binData(movie: Movie, position: Int, currentPage: Int) {
            itemData = movie
            itemView.run {
                Glide.with(this)
                    .load(Constant.BASE_URL_IMAGE + itemData?.url)
                    .into(imageViewMovie)
                var number = position + 1 + currentPage * 20
                if (position == 19 || position == 39) {
                    number = 20 + currentPage * 20
                }
                if (currentPage != -1) {
                    itemView.textMovieNumber.text = number.toString()
                }
            }
        }

    }
}
