package com.github.shaggydemiurge.movieapp.presentation.common.widget

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.github.shaggydemiurge.movieapp.presentation.common.entity.Loadable

@Composable
fun <T> LoadableView(
    loadable: Loadable<T>,
    modifier: Modifier = Modifier,
    loadableViewDelegate: LoadableViewDelegate = LoadableViewDelegate.Local.current,
    content: @Composable (value: T, Modifier) -> Unit,
) {
    when (loadable) {
        is Loadable.Error -> loadableViewDelegate.Error(loadable.error, modifier)
        is Loadable.Loading -> loadableViewDelegate.Loading(modifier)
        is Loadable.Success -> content(loadable.value, modifier)
    }
}

interface LoadableViewDelegate {

    @Composable
    fun Loading(modifier: Modifier)

    @Composable
    fun Error(error: Throwable, modifier: Modifier)

    companion object {
        val Local = compositionLocalOf<LoadableViewDelegate> {
            object : LoadableViewDelegate {
                @Composable
                override fun Loading(modifier: Modifier) {
                }

                @Composable
                override fun Error(error: Throwable, modifier: Modifier) {
                }
            }
        }
    }
}
