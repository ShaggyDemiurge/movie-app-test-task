package com.github.shaggydemiurge.movieapp.data.entities.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConfigurationResponse(
    @Json(name = "images") val images: Images,
) {
    @JsonClass(generateAdapter = true)
    data class Images(
        @Json(name = "secure_base_url") val baseUrl: String,
        @Json(name = "poster_sizes") val posterSizes: List<String>,
    )
}
