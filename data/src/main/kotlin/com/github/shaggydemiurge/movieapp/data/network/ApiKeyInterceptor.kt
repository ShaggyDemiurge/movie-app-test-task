package com.github.shaggydemiurge.movieapp.data.network

import com.github.shaggydemiurge.movieapp.data.NetworkConfig
import okhttp3.Interceptor
import okhttp3.Response

internal class ApiKeyInterceptor(private val networkConfig: NetworkConfig) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        return originalRequest.url.newBuilder()
            .addQueryParameter(API_KEY_QUERY_PARAMETER, networkConfig.apiKey)
            .build()
            .let {
                originalRequest.newBuilder().url(it).build()
            }
            .let { chain.proceed(it) }
    }

    companion object {
        private const val API_KEY_QUERY_PARAMETER = "api_key"
    }
}
