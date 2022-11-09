package com.github.shaggydemiurge.movieapp

import com.github.shaggydemiurge.movieapp.data.NetworkConfig
import org.koin.dsl.module

val appModule = module {
    single {
        NetworkConfig(
            BuildConfig.API_BASE_URL,
            BuildConfig.API_KEY
        )
    }
}
