package com.jvktech.moviebuff.ui.components.others

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
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
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.ui.screens.destinations.DiscoverScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.WatchlistScreenDestination
import com.jvktech.moviebuff.ui.theme.LightGreen


@Composable
fun BottomBar(
    currentRoute: String? = null,
    backQueueRoutes: List<String?> = emptyList(),
    visible: Boolean = true,
    onItemClicked: (String) -> Unit = {}
) {
    val bottomBarRoutes = remember {
        mutableSetOf(
            HomeScreenDestination.route,
            DiscoverScreenDestination.route,
            WatchlistScreenDestination.route,
        )
    }

    val selectedRoute = when (currentRoute) {
        in bottomBarRoutes -> currentRoute
        else -> {
            backQueueRoutes.firstOrNull { route ->
                route in bottomBarRoutes
            } ?: HomeScreenDestination.route
        }
    }

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { it },
        exit = slideOutVertically { it }
    ) {
        NavigationBar(
            contentColor = MaterialTheme.colorScheme.primary,
            containerColor = MaterialTheme.colorScheme.background,
            tonalElevation = 10.dp
        ) {
            NavBarItem(
                selected = selectedRoute == HomeScreenDestination.route,
                onClick = {
                    onItemClicked(HomeScreenDestination.route)
                },
                label = "Home",
                selectedIcon = Icons.Rounded.Home,
                unSelectedIcon = Icons.Outlined.Home,
                contentDescription = "Home",
                badgeCount = 7

            )
            NavBarItem(
                selected = selectedRoute == DiscoverScreenDestination.route,
                onClick = {
                    onItemClicked(DiscoverScreenDestination.route)
                },
                label = "Discover",
                selectedIcon = Icons.Rounded.Search,
                unSelectedIcon = Icons.Outlined.Search,
                contentDescription = "Discover"
            )
            NavBarItem(
                selected = selectedRoute == WatchlistScreenDestination.route,
                onClick = {
                    onItemClicked(WatchlistScreenDestination.route)
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RowScope.NavBarItem(
    label: String,
    selected: Boolean,
    hasNews: Boolean? = false,
    badgeCount: Int? = null,
    onClick: () -> Unit = {},
    selectedIcon: ImageVector,
    unSelectedIcon: ImageVector,
    contentDescription: String? = null,

    ) {
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.onSecondaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
            selectedTextColor = MaterialTheme.colorScheme.onSurface,
            indicatorColor = MaterialTheme.colorScheme.secondaryContainer
        ),
        selected = selected,
        onClick = onClick,
        label = {
            Text(
                modifier = Modifier.basicMarquee(),
                text = label,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1
            )
        },
        icon = {
            Crossfade(
                targetState = selected,
                label = "navigation items animation"
            ) { selected ->
                BadgedBox(
                    badge = {
                        if (badgeCount != null) {
                            Badge(
                                containerColor = LightGreen,
                            ) {
                                Text(
                                    text = badgeCount.toString(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        } else if (hasNews == true) {
                            Badge(
                                containerColor = LightGreen
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = if (selected) selectedIcon else unSelectedIcon,
                        contentDescription = contentDescription
                    )
                }
            }
        }
    )
}