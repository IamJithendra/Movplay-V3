package com.jvktech.moviebuff.ui.components.others

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.BookmarkBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Search
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
import androidx.compose.ui.graphics.vector.ImageVector
import com.jvktech.moviebuff.ui.screens.destinations.FavoriteScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.MovieScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.TvShowScreenDestination


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
            containerColor = MaterialTheme.colorScheme.background,
        ) {
            NavBarItem(
                selected = selectedRoute == MovieScreenDestination.route,
                onClick = {
                    onItemClicked(MovieScreenDestination.route)
                },
                label = "Home",
                selectedIcon = Icons.Rounded.Home,
                unSelectedIcon = Icons.Outlined.Home,
                contentDescription = "Home",
                badgeCount = 7

            )
            NavBarItem(
                selected = selectedRoute == TvShowScreenDestination.route,
                onClick = {
                    onItemClicked(TvShowScreenDestination.route)
                },
                label = "Discover",
                selectedIcon = Icons.Rounded.Search,
                unSelectedIcon = Icons.Outlined.Search,
                contentDescription = "Discover"
            )
            NavBarItem(
                selected = selectedRoute == FavoriteScreenDestination.route,
                onClick = {
                    onItemClicked(FavoriteScreenDestination.route)
                },
                label = "Watchlist",
                selectedIcon = Icons.Filled.Bookmark,
                unSelectedIcon = Icons.Outlined.BookmarkBorder,
                contentDescription = "Watchlist",
                hasNews = true

            )

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