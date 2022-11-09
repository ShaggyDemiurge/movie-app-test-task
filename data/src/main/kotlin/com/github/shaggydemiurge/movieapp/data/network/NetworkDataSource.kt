package com.github.shaggydemiurge.movieapp.data.network

import com.github.shaggydemiurge.movieapp.core.error.ConnectionException
import com.github.shaggydemiurge.movieapp.core.error.ServerException
import com.github.shaggydemiurge.movieapp.data.network.NetworkDataSource.ErrorParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

interface NetworkDataSource {

    val defaultErrorParser
        get() = ErrorParser { _, code, responseBody ->
            val serverException by lazy {
                ServerException(
                    code,
                    responseBody
                )
            }
            when {
                else -> serverException
            }
        }

    /**
     * Executes suspend call in correct dispatcher and handles errors when they occur
     */
    suspend fun <T> request(
        parser: ErrorParser = defaultErrorParser,
        call: suspend () -> T,
    ) = try {
        withContext(Dispatchers.IO) {
            call()
        }
    } catch (t: Throwable) {
        throw parseError(parser, t)
    }

    private fun parseError(parser: ErrorParser, throwable: Throwable): Throwable = when (throwable) {
        is TimeoutException, is ConnectException, is SocketTimeoutException, is UnknownHostException ->
            ConnectionException(throwable)
        is HttpException -> {
            parser.parseHttpError(throwable, throwable.code(), throwable.response()?.errorBody()?.toString())
                ?: throwable
        }
        else -> throwable
    }

    fun interface ErrorParser {
        fun parseHttpError(error: HttpException, code: Int, responseBody: String?): Throwable?
    }
}
