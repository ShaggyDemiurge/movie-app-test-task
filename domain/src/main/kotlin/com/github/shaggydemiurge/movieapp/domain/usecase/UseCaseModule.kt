package com.github.shaggydemiurge.movieapp.domain.usecase

import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieListPage
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::LoadMovieListPageFromRepo) bind LoadMovieListPage::class
}