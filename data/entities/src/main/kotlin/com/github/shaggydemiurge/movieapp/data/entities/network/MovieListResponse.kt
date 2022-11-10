package com.github.shaggydemiurge.movieapp.data.entities.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val results: List<Result>,
    @Json(name = "total_pages") val totalPages: Int,
) {

    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "id") val id: Int,
        @Json(name = "title") val title: String,
        @Json(name = "poster_path") val posterPath: String?,
        @Json(name = "release_date") val releaseDate: LocalDate?,
        @Json(name = "vote_count") val voteCount: Int,
        @Json(name = "vote_average") val avgScore: Float,
    )
}
