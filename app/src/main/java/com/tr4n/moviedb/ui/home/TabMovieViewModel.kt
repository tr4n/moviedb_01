package com.tr4n.moviedb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tr4n.moviedb.base.BaseViewModel
import com.tr4n.moviedb.data.model.Movie
import com.tr4n.moviedb.data.source.MoviesRepository
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class TabMovieViewModel : BaseViewModel(), KoinComponent {

    private val moviesRepository : MoviesRepository by inject()

    var listMoviesTabName = MutableLiveData<List<Movie>>()

    fun getTabMovie(tab : String, page : Int) {
        viewModelScope.launch {
            try {
                listMoviesTabName.value = moviesRepository.getTabMovie(tab, page).results ?: emptyList()
            } catch (ex : Exception) {
                exception.value = ex
            }
        }
    }

}
