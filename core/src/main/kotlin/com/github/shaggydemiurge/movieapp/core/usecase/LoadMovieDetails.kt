package com.github.shaggydemiurge.movieapp.core.usecase

import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails

fun interface LoadMovieDetails {
    suspend operator fun invoke(movieId: Int): MovieDetails
}
