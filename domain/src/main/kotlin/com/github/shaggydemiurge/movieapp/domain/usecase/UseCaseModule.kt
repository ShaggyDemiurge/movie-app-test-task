package com.github.shaggydemiurge.movieapp.domain.usecase

import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieDetails
import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieListPage
import com.github.shaggydemiurge.movieapp.core.usecase.UpdateConfiguration
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

internal val useCaseModule = module {
    factoryOf(::LoadMovieListPageFromRepo) bind LoadMovieListPage::class
    factoryOf(::LoadMovieDetailsFromRepo) bind LoadMovieDetails::class
    factoryOf(::UpdateConfigurationFromRepo) bind UpdateConfiguration::class
}
