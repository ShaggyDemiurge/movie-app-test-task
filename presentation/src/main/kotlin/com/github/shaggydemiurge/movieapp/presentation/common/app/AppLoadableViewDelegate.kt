package com.github.shaggydemiurge.movieapp.presentation.common.app

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.shaggydemiurge.movieapp.presentation.R
import com.github.shaggydemiurge.movieapp.presentation.common.error.ErrorResolver
import com.github.shaggydemiurge.movieapp.presentation.common.widget.LoadableViewDelegate
import com.github.shaggydemiurge.movieapp.presentation.common.widget.localLogger

class AppLoadableViewDelegate(private val errorResolver: ErrorResolver? = null) : LoadableViewDelegate {
    @Composable
    override fun Loading(modifier: Modifier) {
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }

    @Composable
    override fun Error(error: Throwable, modifier: Modifier) {
        val logger = localLogger
        LaunchedEffect(error) {
            logger.error("Encountered an error", error)
        }
        Box(modifier = modifier, contentAlignment = Alignment.Center) {
            Card(
                backgroundColor = MaterialTheme.colors.error,
                modifier = Modifier.padding(32.dp)
            ) {
                Box(modifier = Modifier, contentAlignment = Alignment.Center) {
                    Text(
                        text = (errorResolver ?: ErrorResolver.current).resolveError(LocalContext.current, error)
                            ?: stringResource(R.string.error_internal_unknown, error),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
