package com.github.shaggydemiurge.movieapp.presentation.common.ext

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Helper method to wrap loading requests inside ViewModel
 * @param onLoading will be called with `true` when loading starts and `false` when loading stops
 * @param onError will be called with any errors that occur
 * @param showLoadingDelay is a pause in milliseconds before showing loading, used to avoid blinking for instant requests
 * @param call request in question
 */
fun ViewModel.request(
    onLoading: (Boolean) -> Unit,
    onError: (Throwable) -> Unit,
    showLoadingDelay: Long = 0,
    call: suspend () -> Unit,
) {
    viewModelScope.launch {
        try {
            if (showLoadingDelay == 0L) {
                onLoading(true)
                call()
            } else {
                val loadingJob = launch {
                    delay(showLoadingDelay)
                    onLoading(true)
                }
                launch {
                    call()
                    loadingJob.cancel()
                }
            }
        } catch (e: Throwable) {
            onError(e)
        } finally {
            onLoading(false)
        }
    }
}
