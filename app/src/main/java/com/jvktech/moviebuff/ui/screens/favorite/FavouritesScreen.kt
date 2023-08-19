package com.jvktech.moviebuff.ui.screens.favorite

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvktech.moviebuff.data.model.FavouriteType
import com.jvktech.moviebuff.ui.components.others.FavouriteEmptyState
import com.jvktech.moviebuff.ui.components.sections.PresentableGridSection
import com.jvktech.moviebuff.ui.components.selectors.FavoriteTypeSelector
import com.jvktech.moviebuff.ui.screens.destinations.*
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.isNotEmpty
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalLifecycleComposeApi::class)
@Destination
@Composable
fun AnimatedVisibilityScope.FavoriteScreen(
    viewModel: FavouritesScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onFavoriteTypeSelected: (type: FavouriteType) -> Unit = viewModel::onFavoriteTypeSelected
    val onFavoriteClicked: (favoriteId: Int) -> Unit = { id ->
        val destination = when (uiState.selectedFavouriteType) {
            FavouriteType.Movie -> {
                MovieDetailsScreenDestination(
                    movieId = id,
                    startRoute = FavouriteScreenDestination.route
                )
            }

            FavouriteType.TvShow -> {
                TvShowDetailsScreenDestination(
                    tvShowId = id,
                    startRoute = FavouriteScreenDestination.route
                )
            }
        }
        navigator.navigate(destination)
    }
    val onNavigateToMoviesButtonClicked: () -> Unit = {
        navigator.navigate(MovieScreenDestination) {
            popUpTo(MovieDetailsScreenDestination.route) {
                inclusive = true
            }
        }
    }
    val onNavigateToTvShowButtonClicked: () -> Unit = {
        navigator.navigate(TvShowScreenDestination) {
            popUpTo(MovieScreenDestination.route) {
                inclusive = true
            }
        }
    }
    FavoriteScreenContent(
        uiState = uiState,
        onFavoriteTypeSelected = onFavoriteTypeSelected,
        onFavoriteClicked = onFavoriteClicked,
        onNavigateToMoviesButtonClicked = onNavigateToMoviesButtonClicked,
        onNavigateToTvShowButtonClicked = onNavigateToTvShowButtonClicked
    )
}

@Composable
fun FavoriteScreenContent(
    uiState: FavouritesScreenUIState,
    onFavoriteTypeSelected: (type: FavouriteType) -> Unit,
    onFavoriteClicked: (favouriteId: Int) -> Unit,
    onNavigateToMoviesButtonClicked: () -> Unit,
    onNavigateToTvShowButtonClicked: () -> Unit
) {
    val favouritesLazyItems = uiState.favorites.collectAsLazyPagingItems()
    val notEmpty = favouritesLazyItems.isNotEmpty()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
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
                    state = favouritesLazyItems,
                    showRefreshLoading = false,
                    onPresentableClick = onFavoriteClicked
                )
            } else {
                FavouriteEmptyState(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = MaterialTheme.spacing.medium)
                        .padding(top = MaterialTheme.spacing.extraLarge),
                    type = uiState.selectedFavouriteType,
                    onButtonClick = when (uiState.selectedFavouriteType) {
                        FavouriteType.Movie -> onNavigateToMoviesButtonClicked
                        FavouriteType.TvShow -> onNavigateToTvShowButtonClicked
                    }
                )
            }
        }
    }
}