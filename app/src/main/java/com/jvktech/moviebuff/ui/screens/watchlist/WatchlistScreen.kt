package com.jvktech.moviebuff.ui.screens.watchlist

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvktech.moviebuff.data.model.FavoriteType
import com.jvktech.moviebuff.ui.components.others.FavoriteEmptyState
import com.jvktech.moviebuff.ui.components.sections.PresentableGridSection
import com.jvktech.moviebuff.ui.components.selectors.FavoriteTypeSelector
import com.jvktech.moviebuff.ui.screens.destinations.*
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.isNotEmpty
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun AnimatedVisibilityScope.WatchlistScreen(
    viewModel: WatchlistScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onFavoriteTypeSelected: (type: FavoriteType) -> Unit = viewModel::onFavoriteTypeSelected
    val onFavoriteClicked: (favoriteId: Int) -> Unit = { id ->
        val destination = when (uiState.selectedFavouriteType) {
            FavoriteType.Movie -> {
                MovieDetailsScreenDestination(
                    movieId = id,
                    startRoute = WatchlistScreenDestination.route
                )
            }

            FavoriteType.TvShow -> {
                TvShowDetailsScreenDestination(
                    tvShowId = id,
                    startRoute = WatchlistScreenDestination.route
                )
            }
        }
        navigator.navigate(destination)
    }
    val onNavigateToMoviesButtonClicked: () -> Unit = {
        navigator.navigate(HomeScreenDestination) {
            popUpTo(MovieDetailsScreenDestination.route) {
                inclusive = true
            }
        }
    }
    val onNavigateToTvShowButtonClicked: () -> Unit = {
        navigator.navigate(TvShowScreenDestination) {
            popUpTo(HomeScreenDestination.route) {
                inclusive = true
            }
        }
    }
    WatchlistScreenContent(
        uiState = uiState,
        onFavoriteTypeSelected = onFavoriteTypeSelected,
        onFavoriteClicked = onFavoriteClicked,
        onNavigateToMoviesButtonClicked = onNavigateToMoviesButtonClicked,
        onNavigateToTvShowButtonClicked = onNavigateToTvShowButtonClicked
    )
}

@Composable
fun WatchlistScreenContent(
    uiState: WatchlistScreenUIState,
    onFavoriteTypeSelected: (type: FavoriteType) -> Unit,
    onFavoriteClicked: (favouriteId: Int) -> Unit,
    onNavigateToMoviesButtonClicked: () -> Unit,
    onNavigateToTvShowButtonClicked: () -> Unit
) {
    val favoritesLazyItems = uiState.favorites.collectAsLazyPagingItems()
    val notEmpty = favoritesLazyItems.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {
        FavoriteTypeSelector(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
            selected = uiState.selectedFavouriteType,
            onSelected = onFavoriteTypeSelected
        )
        Crossfade(
            modifier = Modifier.fillMaxSize(),
            targetState = notEmpty,
            label = ""
        ) { notEmpty ->
            if (notEmpty) {
                PresentableGridSection(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(
                        top = MaterialTheme.spacing.medium,
                        start = MaterialTheme.spacing.small,
                        end = MaterialTheme.spacing.small,
                        bottom = MaterialTheme.spacing.large
                    ),
                    state = favoritesLazyItems,
                    showRefreshLoading = false,
                    onPresentableClick = onFavoriteClicked
                )
            } else {
                FavoriteEmptyState(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.extraLarge),
                    type = uiState.selectedFavouriteType,
                    onButtonClick = when (uiState.selectedFavouriteType) {
                        FavoriteType.Movie -> onNavigateToMoviesButtonClicked
                        FavoriteType.TvShow -> onNavigateToTvShowButtonClicked
                    }
                )
            }
        }
    }
}