package com.tr4n.moviedb.ui.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.base.BaseViewModel
import com.tr4n.moviedb.data.model.Genre
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.source.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : BaseViewModel(), KoinComponent {
    private val moviesRepository : MoviesRepository by inject()

    var listMovieSearchResults = MutableLiveData<List<Movie>>()
    var listGenres = MutableLiveData<List<Genre>>()

    fun getMovieSearchResults(query : String, page : Int) {
        viewModelScope.launch {
            try {
                listMovieSearchResults.value = moviesRepository.getMovieSearchResults(query, page).results ?: emptyList()
                listGenres.value = moviesRepository.getGenres().genres ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

}
