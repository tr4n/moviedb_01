package com.tr4n.moviedb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.base.BaseViewModel
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.source.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : BaseViewModel(), KoinComponent {
    private val moviesRepository : MoviesRepository by inject()

    var listMoviesNowPlaying = MutableLiveData<List<Movie>>()

    fun getTabMovie(tab : String, page : Int) {
        viewModelScope.launch {
            try {
                listMoviesNowPlaying.value = moviesRepository.getTabMovie(tab, page).results ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getNextPageTabMovie(tab: String, page: Int) {
        viewModelScope.launch {
            try {
                val nextPageMovie = moviesRepository.getTabMovie(tab, page).results ?: emptyList()
                val currentPageMovie = listMoviesNowPlaying.value
                listMoviesNowPlaying.value = currentPageMovie?.plus(nextPageMovie)
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

}
