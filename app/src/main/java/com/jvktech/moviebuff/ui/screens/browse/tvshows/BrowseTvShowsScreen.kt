package com.jvktech.moviebuff.ui.screens.browse.tvshows

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ViewList
import androidx.compose.material.icons.filled.ViewModule
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.jvktech.moviebuff.data.model.tvshow.TvShowType
import com.jvktech.moviebuff.ui.components.dialogs.InfoDialog
import com.jvktech.moviebuff.ui.components.others.BasicAppBar
import com.jvktech.moviebuff.ui.components.sections.PresentableGridSection
import com.jvktech.moviebuff.ui.screens.destinations.TvShowDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.TvShowScreenDestination
import com.jvktech.moviebuff.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.ui.components.sections.PresentableListSection


@Destination(
    navArgsDelegate = BrowseTvShowsScreenArgs::class,
    style = BrowseTvShowsScreenTransitions::class
)
@Composable
fun BrowseTvShowsScreen(
    viewModel: BrowseTvShowsViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onClearDialogConfirmClicked: () -> Unit = viewModel::onClearClicked
    val onTvShowClicked = { tvShowId: Int ->
        val destination = TvShowDetailsScreenDestination(
            tvShowId = tvShowId,
            startRoute = TvShowScreenDestination.route
        )

        navigator.navigate(destination)
    }
    BrowseTvShowScreenContent(
        uiState = uiState,
        onBackClicked = onBackClicked,
        onClearDialogConfirmClicked = onClearDialogConfirmClicked,
        onTvShowClicked = onTvShowClicked
    )
}

@Composable
fun BrowseTvShowScreenContent(
    uiState: BrowseTvShowsScreenUIState,
    onBackClicked: () -> Unit,
    onClearDialogConfirmClicked: () -> Unit,
    onTvShowClicked: (tvShowId: Int) -> Unit
) {
    val tvShow = uiState.tvShow.collectAsLazyPagingItems()
    val appbarTitle = when (uiState.selectedTvShowType) {
        TvShowType.OnTheAir -> stringResource(R.string.all_tv_series_on_the_air_label)
        TvShowType.TopRated -> stringResource(R.string.all_tv_series_top_rated_label)
        TvShowType.AiringToday -> stringResource(R.string.all_tv_series_airing_today_label)
        TvShowType.Favorite -> stringResource(
            R.string.all_tv_series_favourites_label,
            uiState.favoriteTvShowsCount
        )
        TvShowType.RecentlyBrowsed -> stringResource(R.string.all_tv_series_recently_browsed_label)
        TvShowType.Trending -> stringResource(R.string.all_tv_series_trending_label)
    }
    val showClearButton =
        uiState.selectedTvShowType == TvShowType.RecentlyBrowsed && tvShow.itemSnapshotList.isNotEmpty()

    val gridState = rememberLazyGridState()

    var isGridView by rememberSaveable { mutableStateOf(true) }

    val listState = rememberLazyListState()

    var showClearDialog by remember { mutableStateOf(false) }

    val showDialog = {
        showClearDialog = true
    }

    val dismissDialog = {
        showClearDialog = false
    }

    if (showClearDialog) {
        InfoDialog(
            infoText = stringResource(R.string.clear_recent_tv_series_dialog_text),
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
            title = appbarTitle, action = {
                IconButton(
                    onClick = onBackClicked
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "go back",
                    )
                }
            },
            trailing = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = { isGridView = !isGridView }
                    ) {
                        Icon(
                            imageVector = if (isGridView) Icons.Filled.ViewList else Icons.Filled.ViewModule,
                            contentDescription = if (isGridView) "list view" else "grid view",
                        )
                    }

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
            }
        )

        if (isGridView) {
            PresentableGridSection(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding(),
                gridState = gridState,
                contentPadding = PaddingValues(
                    top = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large
                ),
                state = tvShow,
                onPresentableClick = onTvShowClicked
            )
        } else {
            PresentableListSection(
                modifier = Modifier.fillMaxSize(),
                listState = listState, // Assume you have a LazyListState for list view
                contentPadding = PaddingValues(
                    top = MaterialTheme.spacing.medium,
                    start = MaterialTheme.spacing.small,
                    end = MaterialTheme.spacing.small,
                    bottom = MaterialTheme.spacing.large
                ),
                state = tvShow,
                onPresentableClick = onTvShowClicked
            )
        }
    }
}