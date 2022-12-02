package com.tr4n.moviedb.data.di

import com.tr4n.moviedb.data.source.remote.api.ApiService
import com.tr4n.moviedb.utils.Constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
}

fun provideRetrofit(client: OkHttpClient) : Retrofit {
    return Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient() : OkHttpClient {
    val logging: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)
    return OkHttpClient.Builder().addInterceptor(logging).build()
}

fun provideApiService(retrofit: Retrofit) : ApiService {
    return retrofit.create(ApiService::class.java)
}