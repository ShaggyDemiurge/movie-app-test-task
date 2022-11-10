package com.github.shaggydemiurge.movieapp.presentation.common.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shaggydemiurge.movieapp.presentation.common.entity.Loadable
import kotlinx.coroutines.launch

fun ViewModel.request(
    onLoading: (Boolean) -> Unit,
    onError: (Throwable) -> Unit,
    call: suspend () -> Unit,
) {
    viewModelScope.launch {
        try {
            onLoading(true)
            call()
        } catch (e: Throwable) {
            onError(e)
        } finally {
            onLoading(false)
        }
    }
}

fun <T> ViewModel.load(
    setter: (Loadable<T>) -> Unit,
    loader: suspend () -> T,
) {
    viewModelScope.launch {
        setter(Loadable.Loading())
        try {
            val result = loader()
            setter(Loadable.Success(result))
        } catch (e: Exception) {
            e.printStackTrace()
            setter(Loadable.Error(e))
        }
    }
}
