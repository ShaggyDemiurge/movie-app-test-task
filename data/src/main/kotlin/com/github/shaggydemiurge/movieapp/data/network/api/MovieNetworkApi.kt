package com.github.shaggydemiurge.movieapp.data.network.api

import com.github.shaggydemiurge.movieapp.data.entities.network.ConfigurationResponse
import com.github.shaggydemiurge.movieapp.data.entities.network.MovieListResponse
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieNetworkApi {

    @GET("discover/movie")
    suspend fun getMovieList(
        @Query("page") page: Int,
    ): MovieListResponse

    @GET("configuration")
    suspend fun getConfiguration(): ConfigurationResponse

    companion object {
        fun create(retrofit: Retrofit) = retrofit.create<MovieNetworkApi>()
    }
}
