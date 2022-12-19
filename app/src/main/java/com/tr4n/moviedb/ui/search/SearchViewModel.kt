package com.tr4n.moviedb.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.base.BaseViewModel
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.source.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : BaseViewModel(), KoinComponent {
    private val moviesRepository : MoviesRepository by inject()

    var listMovieSearchResults = MutableLiveData<List<Movie>?>()

    fun getMovieSearchResults(query : String, page : Int) {
        viewModelScope.launch {
            try {
                listMovieSearchResults.value = moviesRepository.getMovieSearchResults(query, page).results ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getNextMovieSearchPage(query: String, page: Int) {
        viewModelScope.launch {
            try {
                val nextMovieSearchPage = moviesRepository.getMovieSearchResults(query, page +1).results ?: emptyList()
                val currentMovieSearchPage = moviesRepository.getMovieSearchResults(query, page).results ?: emptyList()
                if (nextMovieSearchPage.isNotEmpty()) {
                    listMovieSearchResults.value = currentMovieSearchPage.plus(nextMovieSearchPage)
                }
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

    fun getPreMovieSearchPage(query : String, page: Int) {
        viewModelScope.launch {
            val preMovieSearchPage = moviesRepository.getMovieSearchResults(query, page -1).results ?: emptyList()
            val currentMovieSearchPage = moviesRepository.getMovieSearchResults(query, page).results ?: emptyList()
            listMovieSearchResults.value = preMovieSearchPage.plus(currentMovieSearchPage)
        }
    }
}
