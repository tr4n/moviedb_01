package com.tr4n.moviedb.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tr4n.moviedb.R
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.item_movie_search_recyclerview.view.*

class MovieSearchAdapter : ListAdapter<Movie, MovieSearchAdapter.MovieSearchViewHolder>(Movie.diffCallback) {

    var onItemSelected: (Long) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search_recyclerview, parent, false)
        return MovieSearchViewHolder(itemView, onItemSelected)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        holder.binData(getItem(position))
    }

    class MovieSearchViewHolder(
        itemView: View,
        onItemSelected: (Long) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {
        init {
            itemView.setOnClickListener {
                itemData?.let { movie ->
                    onItemSelected(movie.id)
                }
            }
        }

        private var itemData: Movie? = null
        fun binData(movie: Movie) {
            itemData = movie
            itemView.run {
                if (itemData?.url != null) {
                    Glide.with(this)
                        .load(Constant.BASE_URL_IMAGE + itemData?.url)
                        .into(imageViewMovieSearch)
                } else {
                    Glide.with(this)
                        .load(Constant.URL_IMG_NULL)
                        .into(imageViewMovieSearch)
                }

                textTitleNameMovieSearch.text = itemData?.title
                textVoteAverage.text = itemData?.votAverage.toString()
                textGenre.text = itemData?.genreIds.toString()
                textReleaseYear.text = itemData?.releaseDate
            }
        }

    }

}
