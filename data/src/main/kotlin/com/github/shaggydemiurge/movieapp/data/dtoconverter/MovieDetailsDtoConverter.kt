package com.github.shaggydemiurge.movieapp.data.dtoconverter

import com.github.shaggydemiurge.movieapp.core.entities.MovieDetails
import com.github.shaggydemiurge.movieapp.data.entities.network.MovieDetailsResponse

internal class MovieDetailsDtoConverter(private val dto: MovieDetailsResponse) {
    operator fun invoke(baseImageUrl: String?, posterSize: String?): MovieDetails = with(dto) {
        MovieDetails(
            id,
            title,
            tagline?.takeIf { it.isNotBlank() },
            if (posterPath != null && baseImageUrl != null && posterSize != null) {
                baseImageUrl + posterSize + posterPath
            } else null,
            avgScore.takeIf { voteCount > 0 },
            releaseDate,
            overview?.takeIf { it.isNotBlank() }
        )
    }
}
