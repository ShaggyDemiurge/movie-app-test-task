package com.github.shaggydemiurge.movieapp.data.network

import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.BuildConfig
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val networkModule = module {

    singleOf(::buildRetrofit)
    factoryOf(::buildOkHttpClient)
    factoryOf(::ApiKeyInterceptor)
    factory {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.BASIC
        }
    }
}
