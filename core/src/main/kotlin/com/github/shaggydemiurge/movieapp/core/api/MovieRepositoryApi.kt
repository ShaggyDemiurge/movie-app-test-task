package com.github.shaggydemiurge.movieapp.core.api

import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary

interface MovieRepositoryApi {
    suspend fun loadMovieListPage(page: Int): List<MovieSummary>
}
