package com.github.shaggydemiurge.movieapp.data.repository

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::MovieRepository) bind MovieRepositoryApi::class
}
