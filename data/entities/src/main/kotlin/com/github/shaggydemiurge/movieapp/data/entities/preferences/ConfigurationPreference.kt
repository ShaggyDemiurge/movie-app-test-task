package com.github.shaggydemiurge.movieapp.data.entities.preferences

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationPreference(
    @Json(name = "base_url") val baseImageUrl: String,
    @Json(name = "poster_size") val posterSize: String,
)
