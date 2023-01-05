package com.tr4n.moviedb.data.di

import com.tr4n.moviedb.data.repository.MoviesRepositoryImpl
import com.tr4n.moviedb.data.source.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MoviesRepository> {
        MoviesRepositoryImpl(apiService = get(), appDatabase = get())
    }
}
