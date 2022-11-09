package com.github.shaggydemiurge.movieapp.core.usecase

import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary

fun interface LoadMovieListPage {
    suspend fun load(page: Int): List<MovieSummary>
}
