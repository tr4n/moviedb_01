package com.tr4n.moviedb.di

import android.app.Application

import com.tr4n.moviedb.data.di.networkModule
import com.tr4n.moviedb.data.di.repositoryModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(viewModelModule, networkModule, repositoryModule)
        }
    }
}
