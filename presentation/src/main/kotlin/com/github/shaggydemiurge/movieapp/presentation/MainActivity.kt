package com.github.shaggydemiurge.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.github.shaggydemiurge.movieapp.presentation.common.widget.AppErrorResolver
import com.github.shaggydemiurge.movieapp.presentation.common.widget.LocalProvider
import com.github.shaggydemiurge.movieapp.presentation.navigation.MainNavigationHost
import com.github.shaggydemiurge.movieapp.presentation.theme.MovieAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
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
