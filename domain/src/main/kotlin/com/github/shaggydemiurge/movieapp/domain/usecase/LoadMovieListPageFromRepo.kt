package com.github.shaggydemiurge.movieapp.domain.usecase

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieListPage

internal class LoadMovieListPageFromRepo(private val moviesRepository: MovieRepositoryApi) : LoadMovieListPage {
    override suspend fun load(page: Int) =
        moviesRepository.loadMovieListPage(page)
}
