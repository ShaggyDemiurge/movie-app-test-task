package com.github.shaggydemiurge.movieapp.data.repository

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary

internal class MovieRepository() : MovieRepositoryApi {

    override suspend fun loadMovieListPage(page: Int): List<MovieSummary> {
        TODO("Not yet implemented")
    }
}
