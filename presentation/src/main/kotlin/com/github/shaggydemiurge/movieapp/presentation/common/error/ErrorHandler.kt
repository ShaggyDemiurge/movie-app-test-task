package com.github.shaggydemiurge.movieapp.presentation.common

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.github.shaggydemiurge.movieapp.presentation.common.error.ErrorResolver
import com.github.shaggydemiurge.movieapp.presentation.common.widget.localLogger
import kotlinx.coroutines.flow.Flow

/**
 * Show errors collected from [errorFlow] via specified [snackbarHostState]
 */
@Composable
fun ErrorHandler(
    errorFlow: Flow<Throwable>,
    snackbarHostState: SnackbarHostState,
    errorResolver: ErrorResolver = ErrorResolver.current,
) {
    val resolver = rememberUpdatedState(newValue = errorResolver)
    val context = LocalContext.current
    val logger = localLogger

    LaunchedEffect(errorFlow) {
        errorFlow.collect { error ->
            logger.error("Error occurred", error)
            val message = resolver.value.resolveError(context, error)
            if (message != null) {
                snackbarHostState.showSnackbar(message)
            }
        }
    }
}

/**
 * Creates [SnackbarHost] and uses it to show errors collected from [errorFlow]
 */
@Composable
fun SnackbarErrorHandler(
    errorFlow: Flow<Throwable>,
    modifier: Modifier = Modifier,
    errorResolver: ErrorResolver = ErrorResolver.current,
) {
    val hostState = remember { SnackbarHostState() }

    SnackbarHost(hostState, modifier = modifier) { data ->
        Snackbar(
            data,
            backgroundColor = MaterialTheme.colors.error,
            contentColor = MaterialTheme.colors.onError
        )
    }

    ErrorHandler(errorFlow = errorFlow, snackbarHostState = hostState, errorResolver = errorResolver)
}
