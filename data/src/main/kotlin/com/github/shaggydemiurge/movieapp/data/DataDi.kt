package com.github.shaggydemiurge.movieapp.data

import com.github.shaggydemiurge.movieapp.data.network.networkModule
import com.github.shaggydemiurge.movieapp.data.repository.repositoryModule
import com.squareup.moshi.Moshi
import org.koin.dsl.module

val dataModule = module {
    single { moshi() }
    includes(networkModule, repositoryModule)
}

private fun moshi(): Moshi = Moshi.Builder().build()
