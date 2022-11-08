package com.github.shaggydemiurge.movieapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.github.shaggydemiurge.movieapp.presentation.screen.details.MovieDetailsView
import com.github.shaggydemiurge.movieapp.presentation.screen.list.MovieListView
import dev.olshevski.navigation.reimagined.AnimatedNavHost
import dev.olshevski.navigation.reimagined.NavBackHandler
import dev.olshevski.navigation.reimagined.navigate
import dev.olshevski.navigation.reimagined.pop
import dev.olshevski.navigation.reimagined.rememberNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavigationHost() {
    val navController = rememberNavController<Screen>(startDestination = Screen.List)

    NavBackHandler(controller = navController)
    AnimatedNavHost(controller = navController, transitionSpec = TransitionAnimationSpec()) { screen ->
        when (screen) {
            is Screen.List -> MovieListView(onMovieSelect = {
                navController.navigate(Screen.Details(it))
            })
            is Screen.Details -> MovieDetailsView(screen.movieId, onBack = { navController.pop() })
        }
    }
}
