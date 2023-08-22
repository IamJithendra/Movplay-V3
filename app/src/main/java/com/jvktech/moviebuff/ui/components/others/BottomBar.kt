package com.jvktech.moviebuff.ui.components.others

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Movie
import androidx.compose.material.icons.outlined.SmartDisplay
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.ui.screens.destinations.FavoriteScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.MovieScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.TvShowScreenDestination
import com.jvktech.moviebuff.R


@Composable
fun BottomBar(
    modifier: Modifier = Modifier,
    currentRoute: String? = null,
    backQueueRoutes: List<String?> = emptyList(),
    visible: Boolean = true,
    onItemClicked: (String) -> Unit = {}
) {
    val bottomBarRoutes = remember {
        mutableSetOf(
            MovieScreenDestination.route,
            TvShowScreenDestination.route,
            FavoriteScreenDestination.route,
        )
    }

    val selectedRoute = when (currentRoute) {
        in bottomBarRoutes -> currentRoute
        else -> {
            backQueueRoutes.firstOrNull { route ->
                route in bottomBarRoutes
            } ?: MovieScreenDestination.route
        }
    }
    val paddingValues = WindowInsets.navigationBars.asPaddingValues()

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = Color.Cyan,
            tonalElevation = 4.dp
        ) {
            NavBarItem(
                selected = selectedRoute == MovieScreenDestination.route,
                onClick = {
                    onItemClicked(MovieScreenDestination.route)
                },
                label = stringResource(R.string.movies_label),
                selectedIcon = Icons.Filled.Movie,
                unSelectedIcon = Icons.Outlined.Movie,
                contentDescription = "Movie",
                badgeCount = 7

            )
            NavBarItem(
                selected = selectedRoute == TvShowScreenDestination.route,
                onClick = {
                    onItemClicked(TvShowScreenDestination.route)
                },
                label = stringResource(R.string.tv_series_label),
                selectedIcon = Icons.Filled.SmartDisplay,
                unSelectedIcon = Icons.Outlined.SmartDisplay,
                contentDescription = "Tv Show"
            )
            NavBarItem(
                selected = selectedRoute == FavoriteScreenDestination.route,
                onClick = {
                    onItemClicked(FavoriteScreenDestination.route)
                },
                label = stringResource(R.string.favourites_label),
                selectedIcon = Icons.Filled.Favorite,
                unSelectedIcon = Icons.Outlined.FavoriteBorder,
                contentDescription = "Favorites",
                hasNews = true

            )

//            NavBarItem(
//                selected = selectedRoute == SearchScreenDestination.route,
//                onClick = {
//                    onItemClicked(SearchScreenDestination.route)
//                },
//                label = stringResource(R.string.search_label),
//                selectedIcon = Icons.Filled.ZoomIn,
//                unSelectedIcon = Icons.Outlined.Search,
//                contentDescription = "Search"
////                icon = {
////                    Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
////                }
//            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RowScope.NavBarItem(
    label: String,
//    icon: @Composable () -> Unit,
    selected: Boolean,
    hasNews: Boolean? = false,
    badgeCount: Int? = null,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    selectedIcon: ImageVector,
    unSelectedIcon: ImageVector,
    contentDescription: String? = null,

    ) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        label = {
            Text(label)
        },
        icon = {
            BadgedBox(
                badge = {
                    if (badgeCount != null) {
                        Badge {
                            Text(text = badgeCount.toString())
                        }
                    } else if (hasNews == true) {
                        Badge()
                    }
                }
            ) {
                Icon(
                    imageVector = if (selected) selectedIcon else unSelectedIcon,
                    contentDescription = contentDescription
                )
            }
        }
    )
}