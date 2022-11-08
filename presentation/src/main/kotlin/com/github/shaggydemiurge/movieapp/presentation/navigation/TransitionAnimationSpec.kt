package com.github.shaggydemiurge.movieapp.presentation.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import dev.olshevski.navigation.reimagined.NavAction
import dev.olshevski.navigation.reimagined.NavTransitionScope
import dev.olshevski.navigation.reimagined.NavTransitionSpec

@OptIn(ExperimentalAnimationApi::class)
class TransitionAnimationSpec : NavTransitionSpec<Screen> {
    override fun NavTransitionScope.getContentTransform(action: NavAction, from: Screen, to: Screen): ContentTransform {
        val direction = if (action == NavAction.Pop) {
            AnimatedContentScope.SlideDirection.Down
        } else {
            AnimatedContentScope.SlideDirection.Up
        }
        return slideIntoContainer(direction) with slideOutOfContainer(direction)
    }
}
