package com.github.shaggydemiurge.movieapp.data.repository

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.entities.PagedResult
import com.github.shaggydemiurge.movieapp.data.datasource.MovieDataSource

internal class MovieRepository(
    private val movieDataSource: MovieDataSource,
) : MovieRepositoryApi {

    override suspend fun loadMovieListPage(page: Int): PagedResult<MovieSummary> {
        return movieDataSource.loadMovieList(page).invoke() // TODO use image base url
    }
}
