package com.github.shaggydemiurge.movieapp.presentation.common.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
