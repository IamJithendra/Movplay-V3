package com.example.movplayv3.ui.screens.details.person

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.example.movplayv3.ui.screens.destinations.FavoriteScreenDestination
import com.example.movplayv3.ui.screens.destinations.MovieScreenDestination
import com.example.movplayv3.ui.screens.destinations.TvShowScreenDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object PersonDetailsScreenTransitions : DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return when (targetState.destination.route) {
            TvShowScreenDestination.route,
            MovieScreenDestination.route,
            FavoriteScreenDestination.route  -> slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(300)
            )
            else -> null
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition? {
        return when (targetState.destination.route) {
            TvShowScreenDestination.route,
            MovieScreenDestination.route,
            FavoriteScreenDestination.route  -> slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(300)
            )
            else -> null
        }
    }
}