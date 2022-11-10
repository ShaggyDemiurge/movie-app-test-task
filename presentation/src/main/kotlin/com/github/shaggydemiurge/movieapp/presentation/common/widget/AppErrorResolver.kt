package com.github.shaggydemiurge.movieapp.presentation.common.widget

import android.content.Context
import com.github.shaggydemiurge.movieapp.core.error.ConnectionException
import com.github.shaggydemiurge.movieapp.core.error.ServerException
import com.github.shaggydemiurge.movieapp.presentation.R
import com.github.shaggydemiurge.movieapp.presentation.common.error.ErrorResolver

class AppErrorResolver : ErrorResolver {
    override fun resolveError(context: Context, error: Throwable): String = when (error) {
        is ServerException -> when (error.code) {
            404 -> context.getString(R.string.error_server_404)
            else -> context.getString(R.string.error_server_unknown)
        }
        is ConnectionException -> context.getString(R.string.error_connection)
        else -> context.getString(R.string.error_internal_unknown, error.message ?: error.javaClass.simpleName)
    }
}
