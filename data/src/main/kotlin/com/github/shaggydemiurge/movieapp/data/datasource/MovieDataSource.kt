package com.github.shaggydemiurge.movieapp.data.datasource

import com.github.shaggydemiurge.movieapp.data.dtoconverter.ConfigurationDtoConverter
import com.github.shaggydemiurge.movieapp.data.dtoconverter.MovieDetailsDtoConverter
import com.github.shaggydemiurge.movieapp.data.dtoconverter.MovieListDtoConverter
import com.github.shaggydemiurge.movieapp.data.network.NetworkDataSource
import com.github.shaggydemiurge.movieapp.data.network.api.MovieNetworkApi

internal class MovieDataSource(
    private val api: MovieNetworkApi,
) : NetworkDataSource {

    suspend fun loadMovieList(page: Int) = request {
        MovieListDtoConverter(api.getMovieList(page))
    }

    suspend fun loadMovieDetails(movieId: Int) = request {
        MovieDetailsDtoConverter(api.getMovieDetails(movieId))
    }

    suspend fun loadConfiguration() = request {
        ConfigurationDtoConverter(api.getConfiguration())
    }
}
