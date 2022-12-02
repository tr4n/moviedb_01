package com.tr4n.moviedb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.source.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TabMovieViewModel : ViewModel(), KoinComponent {

    private val moviesRepository : MoviesRepository by inject()

    var listMoviesNowPlaying = MutableLiveData<List<Movie>>()
    var exception = MutableLiveData<Exception>()

    fun getTabMovie(tab : String, language : String, page : Int) {
        viewModelScope.launch {
            try {
                listMoviesNowPlaying.value = moviesRepository.getTabMovie(tab, language, page).results ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getNextTabMovie(tab: String, language: String, page: Int) {
        viewModelScope.launch {
            try {
                val nextTabMovies = moviesRepository.getTabMovie(tab, language, page+1).results ?: emptyList()
                val currentTabMovies = moviesRepository.getTabMovie(tab, language, page).results ?: emptyList()
                listMoviesNowPlaying.value = currentTabMovies.plus(nextTabMovies)
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getPreTabMovie(tab : String, language: String, page: Int) {
        viewModelScope.launch {
            val preTabMovies = moviesRepository.getTabMovie(tab, language, page-1).results ?: emptyList()
            val currentTabMovies = moviesRepository.getTabMovie(tab, language, page).results ?: emptyList()
            listMoviesNowPlaying.value = preTabMovies.plus(currentTabMovies)
        }
    }

}
