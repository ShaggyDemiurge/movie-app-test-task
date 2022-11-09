package com.github.shaggydemiurge.movieapp.core.usecase

import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.entities.PagedResult

fun interface LoadMovieListPage {
    suspend fun load(page: Int): PagedResult<MovieSummary>
}
