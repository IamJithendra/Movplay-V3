package com.jvktech.moviebuff.utils

import androidx.navigation.NavController
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination

fun NavController.safeNavigate(
    route: String,
    onSameRouteSelected: (String) -> Unit = {}
) {
    val currentRoute = currentBackStackEntry?.destination?.route

    if (currentRoute == route) {
        onSameRouteSelected(route)
        return
    }

    val isInBackstack = backQueue.map { entry -> entry.destination.route }
        .any { it == route }

    if (isInBackstack) {
        popBackStack(
            route = route,
            inclusive = false
        )
    } else {
        navigate(route) {
            popUpTo(HomeScreenDestination.route)
        }
    }
}