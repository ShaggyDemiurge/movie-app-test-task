package com.github.shaggydemiurge.movieapp.data.network

import com.github.shaggydemiurge.movieapp.data.NetworkConfig
import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

internal fun buildRetrofit(
    networkConfig: NetworkConfig,
    moshi: Moshi,
    client: OkHttpClient,
) = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(networkConfig.baseUrl)
    .client(client)
    .build()
