package com.github.shaggydemiurge.movieapp.data.datasource

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

internal val dataSourceModule = module {
    singleOf(::MovieDataSource)
}
