package com.github.shaggydemiurge.movieapp.domain.usecase

import com.github.shaggydemiurge.movieapp.core.api.MovieRepositoryApi
import com.github.shaggydemiurge.movieapp.core.usecase.UpdateConfiguration

internal class UpdateConfigurationFromRepo(private val movieRepository: MovieRepositoryApi) : UpdateConfiguration {
    override suspend fun invoke() {
        movieRepository.updateConfiguration()
    }
}
