package com.tr4n.moviedb.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.base.BaseViewModel
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.model.Review
import com.tr4n.moviedb.data.source.MoviesRepository
import com.tr4n.moviedb.utils.Constant
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MovieDetailViewModel : BaseViewModel(), KoinComponent {
    private val moviesRepository : MoviesRepository by inject()
    private var movieId = 0L
    val movieDetail = MutableLiveData<Movie>()
    val txtAboutMovie = MutableLiveData<String>()
    val movieReviews = MutableLiveData<List<Review>>()
    val listMovieSimilar = MutableLiveData<List<Movie>>()
    val listMovieWatchList = MutableLiveData<List<Movie>>()
    var movieWatchList = MutableLiveData<Movie>()

    fun getListMovieWatchList() {
        viewModelScope.launch {
            try {
                listMovieWatchList.value = moviesRepository.getAllWatchList()
            } catch (ex: Exception) {
                exception.value = ex
            }
        }
    }

    fun getMovieDetails(movieId : Long) {
        viewModelScope.launch {
            try {
                this@MovieDetailViewModel.movieId = movieId
                movieDetail.value = moviesRepository.getMovieDetails(movieId)
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getAboutMovie() {
        viewModelScope.launch {
            try {
                txtAboutMovie.value = moviesRepository.getMovieDetails(movieId).overview
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getMovieReviews(page : Int) {
        viewModelScope.launch {
            try {
                movieReviews.value = moviesRepository.getMovieReviews(movieId, page).results ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getMoviesSimilar(page : Int) {
        viewModelScope.launch {
            try {
                listMovieSimilar.value = moviesRepository.getMovieSimilar(movieId, page).results ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getMovieDetailReleaseYear() : String {
        val firstApiFormatter = DateTimeFormatter.ofPattern(Constant.PATTERN_Y_M_D)
        val releaseDate = LocalDate.parse(movieDetail.value?.releaseDate, firstApiFormatter)
        return releaseDate.year.toString()
    }

    fun insertWatchList(movie: Movie) {
        viewModelScope.launch {
            try {
                moviesRepository.insertWatchList(movie)
            } catch (ex: Exception) {
                exception.value = ex
            }
        }
    }

    fun deleteWatchListById(id: Long) {
        viewModelScope.launch {
            try {
                moviesRepository.deleteWatchListById(id)
                listMovieWatchList.value = moviesRepository.getAllWatchList()
            } catch (ex: Exception) {
                exception.value = ex
            }
        }
    }

    fun getWatchListById(id: Long) {
        viewModelScope.launch {
            runBlocking {
                movieWatchList.value = moviesRepository.getWatchListById(id)
            }
        }
    }
}
