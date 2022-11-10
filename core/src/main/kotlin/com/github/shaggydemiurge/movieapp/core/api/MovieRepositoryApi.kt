package com.github.shaggydemiurge.movieapp.core.api

import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.entities.PagedResult

interface MovieRepositoryApi {
    suspend fun loadMovieListPage(page: Int): PagedResult<MovieSummary>

    suspend fun updateConfiguration()
}
