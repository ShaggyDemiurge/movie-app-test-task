package com.github.shaggydemiurge.movieapp.data.entities.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.time.LocalDate

@JsonClass(generateAdapter = true)
data class MovieDetailsResponse(
    @Json(name = "id") val id: Int,
    @Json(name = "title") val title: String,
    @Json(name = "tagline") val tagline: String?,
    @Json(name = "poster_path") val posterPath: String?,
    @Json(name = "release_date") val releaseDate: LocalDate?,
    @Json(name = "vote_count") val voteCount: Int,
    @Json(name = "vote_average") val avgScore: Float,
    @Json(name = "overview") val overview: String?,
)
