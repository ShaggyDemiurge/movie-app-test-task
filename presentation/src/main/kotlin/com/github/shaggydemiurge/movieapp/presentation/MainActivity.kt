package com.github.shaggydemiurge.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.github.shaggydemiurge.movieapp.presentation.common.widget.AppErrorResolver
import com.github.shaggydemiurge.movieapp.presentation.common.widget.LocalProvider
import com.github.shaggydemiurge.movieapp.presentation.navigation.MainNavigationHost
import com.github.shaggydemiurge.movieapp.presentation.theme.MovieAppTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = koinViewModel<MainViewModel>()
            LaunchedEffect(viewModel) {
                viewModel.updateConfig()
            }
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LocalProvider(
                        errorResolver = AppErrorResolver()
                    ) {
                        MainNavigationHost()
                    }
                }
            }
        }
    }
}
