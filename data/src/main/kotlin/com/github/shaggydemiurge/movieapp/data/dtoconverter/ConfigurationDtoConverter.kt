package com.github.shaggydemiurge.movieapp.data.dtoconverter

import com.github.shaggydemiurge.movieapp.core.entities.Configuration
import com.github.shaggydemiurge.movieapp.data.entities.network.ConfigurationResponse

internal class ConfigurationDtoConverter(private val dto: ConfigurationResponse) {

    operator fun invoke() = Configuration(
        dto.images.baseUrl,
        dto.images.posterSizes
            // Because most sizes have format w200 and sorted, we can select first one that is big enough
            .mapNotNull { it.drop(1).toIntOrNull() }
            .firstOrNull { it > MIN_POSTER_SIZE }
            ?.let { "w$it" } ?: "original"
    )

    companion object {
        const val MIN_POSTER_SIZE = 200
    }
}
