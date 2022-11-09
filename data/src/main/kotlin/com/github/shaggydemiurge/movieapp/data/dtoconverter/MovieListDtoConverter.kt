package com.github.shaggydemiurge.movieapp.data.dtoconverter

import com.github.shaggydemiurge.movieapp.core.entities.MovieSummary
import com.github.shaggydemiurge.movieapp.core.entities.PagedResult
import com.github.shaggydemiurge.movieapp.data.entities.MovieListResponse

internal class MovieListDtoConverter(private val dto: MovieListResponse) {
    operator fun invoke(): PagedResult<MovieSummary> = PagedResult(
        dto.results.map { it.toSummary() },
        dto.page,
        dto.totalPages
    )

    private fun MovieListResponse.Result.toSummary() = MovieSummary(
        id,
        title,
        poster, // TODO use baseUrl for images
        avgScore,
        releaseDate
    )
}
