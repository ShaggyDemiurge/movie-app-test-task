package com.github.shaggydemiurge.movieapp.presentation.common.error

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf

/**
 * An entity that decides which text should be shown at each error
 */
fun interface ErrorResolver {

    fun resolveError(context: Context, error: Throwable): String?

    companion object {
        val Local = compositionLocalOf { ErrorResolver { _, _ -> null } }

        val current
            @Composable
            @ReadOnlyComposable
            get() = Local.current
    }
}
