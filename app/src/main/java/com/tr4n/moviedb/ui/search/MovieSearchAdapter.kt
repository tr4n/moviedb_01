package com.tr4n.moviedb.ui.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tr4n.moviedb.R
import com.tr4n.moviedb.data.model.Genre
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.utils.Constant
import kotlinx.android.synthetic.main.item_movie_search_recyclerview.view.*

class MovieSearchAdapter : ListAdapter<Movie, MovieSearchAdapter.MovieSearchViewHolder>(Movie.diffCallback) {

    var onItemSelected: (Long) -> Unit = {}
    var listGenres = listOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_search_recyclerview, parent, false)
        return MovieSearchViewHolder(itemView, onItemSelected)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        val itemData = getItem(position)
        setGenre(itemData)
        holder.binData(itemData)
    }

    private fun setGenre(movie: Movie) {
        val genreIdsMovie = movie.genreIds
        val newGenres = mutableListOf<Genre>()
        for (genre in listGenres) {
            for (genreId in genreIdsMovie) {
                if (genre.id == genreId) {
                    newGenres.add(genre)
                }
            }
        }
        movie.genres = newGenres
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
        @SuppressLint("SetTextI18n")
        fun binData(movie: Movie) {
            itemData = movie
            itemView.run {
                if (!itemData?.posterPath.isNullOrEmpty()) {
                    Glide.with(this)
                        .load(Constant.BASE_URL_IMAGE + itemData?.posterPath)
                        .into(imageViewMovieSearch)
                } else {
                    imageViewMovieSearch.setImageResource(R.drawable.bg_image_not_found)
                }

                textTitleNameMovieSearch.text = itemData?.title
                textVoteAverage.text = itemData?.voteAverage.toString()
                for (genre in itemData?.genres ?: emptyList()) {
                    textGenre.text = textGenre.text.toString() + genre.name + " "
                }
                textReleaseYear.text = itemData?.releaseDate
            }
        }

    }

}
