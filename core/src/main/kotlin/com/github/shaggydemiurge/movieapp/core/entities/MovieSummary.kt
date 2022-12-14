package com.github.shaggydemiurge.movieapp.core.entities

import java.time.LocalDate

data class MovieSummary(
    val id: Int,
    val title: String,
    val posterUri: String?,
    val avgScore: Float?,
    val releaseDate: LocalDate?,
)
