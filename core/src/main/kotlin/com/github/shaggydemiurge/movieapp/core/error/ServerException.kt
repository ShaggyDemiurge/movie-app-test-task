package com.github.shaggydemiurge.movieapp.core.error

import java.io.IOException

data class ServerException(
    val code: Int,
    override val message: String?,
) : IOException()

class ConnectionException(override val cause: Throwable?) : IOException("Connection failure")
