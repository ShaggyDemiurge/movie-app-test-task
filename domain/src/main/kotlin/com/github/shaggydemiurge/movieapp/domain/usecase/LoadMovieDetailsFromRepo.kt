package com.github.shaggydemiurge.movieapp.domain.usecase

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails
import com.github.shaggydemiurge.movieapp.core.usecase.LoadMovieDetails

internal class LoadMovieDetailsFromRepo(private val movieRepository: MovieRepositoryApi) : LoadMovieDetails {
    override suspend fun invoke(movieId: Int): MovieDetails = movieRepository.loadMovieDetails(movieId)
}
