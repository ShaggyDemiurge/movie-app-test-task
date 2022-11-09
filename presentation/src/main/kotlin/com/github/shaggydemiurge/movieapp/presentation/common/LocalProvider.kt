package com.github.shaggydemiurge.movieapp.presentation.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidedValue
import com.github.shaggydemiurge.movieapp.presentation.common.error.ErrorResolver

@Composable
fun LocalProvider(errorResolver: ErrorResolver? = null, content: @Composable () -> Unit) {
    val values = listOfNotNull<ProvidedValue<*>>(
        errorResolver?.let { ErrorResolver.local provides it }
    ).toTypedArray()
    CompositionLocalProvider(*values, content = content)
}
