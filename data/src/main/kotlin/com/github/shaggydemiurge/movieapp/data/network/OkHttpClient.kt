package com.github.shaggydemiurge.movieapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

internal fun buildOkHttpClient(
    loggingInterceptor: HttpLoggingInterceptor,
    apiKeyInterceptor: ApiKeyInterceptor,
) = OkHttpClient.Builder()
    .addInterceptor(apiKeyInterceptor)
    .addInterceptor(loggingInterceptor)
    .build()
