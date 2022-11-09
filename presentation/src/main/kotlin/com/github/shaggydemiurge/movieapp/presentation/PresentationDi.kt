package com.github.shaggydemiurge.movieapp.presentation

import com.github.shaggydemiurge.movieapp.presentation.screen.list.MovieListViewModel
import org.koin.dsl.module

val presentationModule = module {
    includes(
        MovieListViewModel.module
    )
}
