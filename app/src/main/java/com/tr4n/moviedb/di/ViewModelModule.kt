package com.tr4n.moviedb.di

import com.tr4n.moviedb.ui.detail.MovieDetailViewModel
import com.tr4n.moviedb.ui.favorite.FavoriteViewModel
import com.tr4n.moviedb.ui.home.HomeViewModel
import com.tr4n.moviedb.ui.home.TabMovieViewModel
import com.tr4n.moviedb.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchViewModel()
    }
    viewModel {
        FavoriteViewModel()
    }
    viewModel {
        HomeViewModel()
    }
    viewModel {
        TabMovieViewModel()
    }
    viewModel {
        MovieDetailViewModel()
    }

}

