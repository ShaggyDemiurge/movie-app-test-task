package com.github.shaggydemiurge.movieapp.presentation.common.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import com.github.shaggydemiurge.movieapp.presentation.common.error.ErrorResolver

@Composable
fun LocalProvider(
    errorResolver: ErrorResolver? = null,
    loadableViewDelegate: LoadableViewDelegate? = null,
    content: @Composable () -> Unit,
) {
    val values = listOfNotNull<ProvidedValue<*>>(
        errorResolver?.let { ErrorResolver.Local provides it },
        loadableViewDelegate?.let { LoadableViewDelegate.Local provides it }
    ).toTypedArray()
    CompositionLocalProvider(*values, content = content)
}
