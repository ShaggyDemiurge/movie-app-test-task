package com.github.shaggydemiurge.movieapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.shaggydemiurge.movieapp.core.usecase.UpdateConfiguration
import com.github.shaggydemiurge.movieapp.core.util.logger
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

class MainViewModel(private val updateConfiguration: UpdateConfiguration) : ViewModel() {

    private val logger = logger("MainViewModel")

    fun updateConfig() {
        viewModelScope.launch {
            try {
                updateConfiguration()
            } catch (e: Throwable) {
                logger.error("Error while loading config", e)
            }
        }
    }

    companion object {
        val module = module {
            viewModelOf(::MainViewModel)
        }
    }
}
