package com.jvktech.moviebuff.ui.screens.details.person

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.TvShowScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.WatchlistScreenDestination
import com.ramcosta.composedestinations.spec.DestinationStyle

@OptIn(ExperimentalAnimationApi::class)
object PersonDetailsScreenTransitions : DestinationStyle.Animated {
    override fun AnimatedContentScope<NavBackStackEntry>.exitTransition(): ExitTransition? {
        return when (targetState.destination.route) {
            TvShowScreenDestination.route,
            HomeScreenDestination.route,
            WatchlistScreenDestination.route  -> slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(300)
            )
            else -> null
        }
    }

    override fun AnimatedContentScope<NavBackStackEntry>.popExitTransition(): ExitTransition? {
        return when (targetState.destination.route) {
            TvShowScreenDestination.route,
            HomeScreenDestination.route,
            WatchlistScreenDestination.route  -> slideOutOfContainer(
                towards = AnimatedContentScope.SlideDirection.Down,
                animationSpec = tween(300)
            )
            else -> null
        }
    }
}