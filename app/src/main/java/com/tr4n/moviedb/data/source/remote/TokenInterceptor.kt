package com.tr4n.moviedb.data.source.remote

import com.tr4n.moviedb.utils.Constant
import okhttp3.Interceptor
import okhttp3.Response

class TokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var original = chain.request()
        val token = Constant.API_KEY
        val url = original.url.newBuilder().addQueryParameter("api_key", token)
            .addQueryParameter("language", Constant.LANGUAGE)
            .build()
        original = original.newBuilder().url(url).build()
        return chain.proceed(original)
    }

}
