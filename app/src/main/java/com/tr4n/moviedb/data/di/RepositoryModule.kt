package com.tr4n.moviedb.data.di

import com.tr4n.moviedb.data.repository.AuthenticationRepositoryImpl
import com.tr4n.moviedb.data.repository.MoviesRepositoryImpl
import com.tr4n.moviedb.data.source.AuthenticationRepository
import com.tr4n.moviedb.data.source.MoviesRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(apiService = get())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(apiService = get())
    }
}