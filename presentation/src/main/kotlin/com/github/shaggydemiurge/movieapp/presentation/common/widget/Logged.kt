package com.github.shaggydemiurge.movieapp.presentation.common.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import com.github.shaggydemiurge.movieapp.core.util.logger
import org.slf4j.Logger

val localLoggerName = compositionLocalOf { "" }

val localLogger: Logger
    @Composable
    @ReadOnlyComposable
    get() = logger(localLoggerName.current)

@Composable
fun Logged(name: String, content: @Composable () -> Unit) {
    CompositionLocalProvider(localLoggerName provides name, content = content)
}
