package com.github.shaggydemiurge.movieapp.data.repository

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.entities.PagedResult
import com.github.shaggydemiurge.movieapp.data.datasource.MovieDataSource
import com.github.shaggydemiurge.movieapp.data.preferences.AppPreferences

internal class MovieRepository(
    private val movieDataSource: MovieDataSource,
    private val preferences: AppPreferences,
) : MovieRepositoryApi {

    override suspend fun loadMovieListPage(page: Int): PagedResult<MovieSummary> {
        val configuration = preferences.configuration ?: movieDataSource.loadConfiguration().invoke().also {
            preferences.configuration = it
        }
        return movieDataSource.loadMovieList(page).invoke(configuration.baseImageUrl, configuration.posterSize)
    }

    override suspend fun updateConfiguration() {
        preferences.configuration = movieDataSource.loadConfiguration().invoke()
    }
}
