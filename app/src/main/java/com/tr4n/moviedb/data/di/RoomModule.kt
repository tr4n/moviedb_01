package com.tr4n.moviedb.data.di

import androidx.room.Room
import com.tr4n.moviedb.data.source.local.room.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomModule = module {
    single { Room.databaseBuilder(androidContext(), AppDatabase::class.java, "db_name")
        .build()}
}
