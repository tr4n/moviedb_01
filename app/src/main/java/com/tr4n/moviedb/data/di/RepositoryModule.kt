package com.tr4n.moviedb.data.di

import com.tr4n.moviedb.data.model.AuthenticationTokenResponse
import com.tr4n.moviedb.data.repository.AuthenticationRepositoryImpl
import com.tr4n.moviedb.data.source.AuthenticationRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<AuthenticationRepository> {
        AuthenticationRepositoryImpl(apiService = get())
    }
}