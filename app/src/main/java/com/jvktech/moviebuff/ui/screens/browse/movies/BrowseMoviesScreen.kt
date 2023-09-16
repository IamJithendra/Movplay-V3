package com.jvktech.moviebuff.ui.screens.browse.movies

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvktech.moviebuff.data.model.movie.MovieType
import com.jvktech.moviebuff.ui.components.dialogs.InfoDialog
import com.jvktech.moviebuff.ui.components.others.BasicAppBar
import com.jvktech.moviebuff.ui.components.sections.PresentableGridSection
import com.jvktech.moviebuff.ui.screens.destinations.MovieDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import com.jvktech.moviebuff.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.FlowPreview
import com.jvktech.moviebuff.R


@OptIn(FlowPreview::class)
@Destination(
    navArgsDelegate = BrowseMoviesScreenArgs::class,
    style = BrowseMoviesScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.BrowseMoviesScreen(
    viewModel: BrowseMoviesViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onClearDialogConfirmClick: () -> Unit = viewModel::onClearClicked
    val onMovieClicked = { movieId: Int ->
        val destination = MovieDetailsScreenDestination(
            movieId = movieId,
            startRoute = HomeScreenDestination.route
        )
        navigator.navigate(destination)
    }
    BrowseMoviesScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onClearDialogConfirmClicked = onClearDialogConfirmClick,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun BrowseMoviesScreenContent(
    uiState: BrowseMoviesScreenUIState,
    onBackClicked: () -> Unit,
    onClearDialogConfirmClicked: () -> Unit,
    onMovieClicked: (movieId: Int) -> Unit
) {
    val movies = uiState.movies.collectAsLazyPagingItems()
    val appbarTitle = when (uiState.selectedMovieType) {
        MovieType.NowPlaying -> stringResource(R.string.all_movies_now_playing_label)
        MovieType.Upcoming -> stringResource(R.string.all_movies_upcoming_label)
        MovieType.TopRated -> stringResource(R.string.all_movies_top_rated_label)
        MovieType.Favorite -> stringResource(
            R.string.all_movies_favourites_label,
            uiState.favoriteMoviesCount
        )
        MovieType.RecentlyBrowsed -> stringResource(R.string.all_movies_recently_browsed_label)
        MovieType.Trending -> stringResource(R.string.all_movies_trending_label)
    }
    val showClearButton =
        uiState.selectedMovieType == MovieType.RecentlyBrowsed && movies.itemSnapshotList.isNotEmpty()

    var showClearDialog by remember {
        mutableStateOf(false)
    }
    val showDialog = {
        showClearDialog = true
    }

    val dismissDialog = {
        showClearDialog = false
    }
    if (showClearDialog) {
        InfoDialog(
            infoText = stringResource(R.string.clear_recent_movies_dialog_text),
            onDismissRequest = dismissDialog,
            onCancelClick = dismissDialog,
            onConfirmClick = {
                onClearDialogConfirmClicked()
                dismissDialog()
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        BasicAppBar(
            title = appbarTitle,
            action = {
                IconButton(
                    onClick = { onBackClicked() }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = null
                    )
                }
            },
            trailing = {
                AnimatedVisibility(
                    visible = showClearButton,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    IconButton(onClick = showDialog) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "clear recent",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        )
        PresentableGridSection(
            modifier = Modifier
                .fillMaxSize()
                .navigationBarsPadding(),
            contentPadding = PaddingValues(
                top = MaterialTheme.spacing.medium,
                start = MaterialTheme.spacing.small,
                end = MaterialTheme.spacing.small,
                bottom = MaterialTheme.spacing.large
            ),
            state = movies,
            onPresentableClick = onMovieClicked
        )
    }
}