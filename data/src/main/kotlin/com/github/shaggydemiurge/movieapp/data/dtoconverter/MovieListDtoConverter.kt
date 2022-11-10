package com.github.shaggydemiurge.movieapp.data.dtoconverter

import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.entities.PagedResult
import com.github.shaggydemiurge.movieapp.data.entities.network.MovieListResponse

internal class MovieListDtoConverter(private val dto: MovieListResponse) {
    operator fun invoke(baseImageUrl: String?, posterSize: String?): PagedResult<MovieSummary> = PagedResult(
        dto.results.map { it.toSummary(baseImageUrl, posterSize) },
        dto.page,
        dto.totalPages
    )

    private fun MovieListResponse.Result.toSummary(baseImageUrl: String?, posterSize: String?) = MovieSummary(
        id,
        title,
        if (posterPath != null && baseImageUrl != null && posterSize != null) {
            baseImageUrl + posterSize + posterPath
        } else null,
        avgScore,
        releaseDate
    )
}
