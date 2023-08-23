package com.jvktech.moviebuff.ui.screens.movie

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jvktech.moviebuff.MainViewModel
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.movie.MovieType
import com.jvktech.moviebuff.ui.components.dialogs.ExitDialog
import com.jvktech.moviebuff.ui.components.sections.PresentableSection
import com.jvktech.moviebuff.ui.components.sections.PresentableTopSection
import com.jvktech.moviebuff.ui.screens.destinations.BrowseMoviesScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.DiscoverMoviesScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.MovieDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.MovieScreenDestination
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.isAnyRefreshing
import com.jvktech.moviebuff.utils.isNotEmpty
import com.jvktech.moviebuff.utils.refreshAll
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun AnimatedVisibilityScope.MovieScreen(
    mainViewModel: MainViewModel,
    viewModel: MovieScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        mainViewModel.sameBottomBarRoute.collectLatest { sameRoute ->
            if (sameRoute == MovieScreenDestination.route) {
                scrollState.animateScrollTo(0)
            }
        }
    }
    val onMovieClicked = { movieId: Int ->
        val destination = MovieDetailsScreenDestination(
            movieId = movieId,
            startRoute = MovieScreenDestination.route
        )

        navigator.navigate(destination)
    }

    val onBrowseMoviesClicked = { type: MovieType ->
        navigator.navigate(BrowseMoviesScreenDestination(type))
    }

    val onDiscoverMoviesClicked = {
        navigator.navigate(DiscoverMoviesScreenDestination)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(65.dp)
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
        content = {innerPadding ->

            MoviesScreenContent(
                uiState = uiState,
                scrollState = scrollState,
                onMovieClicked = onMovieClicked,
                onBrowseMoviesClicked = onBrowseMoviesClicked,
                onDiscoverMoviesClicked = onDiscoverMoviesClicked
            )
        }
    )
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MoviesScreenContent(
    uiState: MovieScreenUIState,
    scrollState: ScrollState,
    onMovieClicked: (movieId: Int) -> Unit,
    onBrowseMoviesClicked: (type: MovieType) -> Unit,
    onDiscoverMoviesClicked: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val discoverLazyItems = uiState.moviesState.discover.collectAsLazyPagingItems()
    val upcomingLazyItems = uiState.moviesState.upcoming.collectAsLazyPagingItems()
    val topRatedLazyItems = uiState.moviesState.topRated.collectAsLazyPagingItems()
    val trendingLazyItems = uiState.moviesState.trending.collectAsLazyPagingItems()
    val nowPlayingLazyItems = uiState.moviesState.nowPlaying.collectAsLazyPagingItems()
    val favoritesLazyItems = uiState.favorites.collectAsLazyPagingItems()
    val recentlyBrowsedLazyItems = uiState.recentlyBrowsed.collectAsLazyPagingItems()

    var topSectionHeight: Float? by remember {
        mutableStateOf(null)
    }
    val appBarHeight = density.run { 56.dp.toPx() }
    val topSectionScrollLimitValue: Float? = topSectionHeight?.minus(appBarHeight)
    var showExitDialog by remember {
        mutableStateOf(false)
    }
    val dismissDialog = {
        showExitDialog = false
    }
    BackHandler {
        showExitDialog = true
    }

    if (showExitDialog) {
        ExitDialog(
            onDismissRequest = dismissDialog,
            onCancelClick = dismissDialog,
            onConfirmClick = {
                val activity = (context as? Activity)
                activity?.finish()
            }
        )
    }

    val isRefreshing by derivedStateOf {
        listOf(
            discoverLazyItems,
            upcomingLazyItems,
            topRatedLazyItems,
            trendingLazyItems,
            nowPlayingLazyItems
        ).isAnyRefreshing()
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val refreshAllPagingData = {
        listOf(
            discoverLazyItems,
            upcomingLazyItems,
            topRatedLazyItems,
            trendingLazyItems,
            nowPlayingLazyItems
        ).refreshAll()
    }

    LaunchedEffect(isRefreshing) {
        swipeRefreshState.isRefreshing = isRefreshing
    }

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize().statusBarsPadding(),
        state = swipeRefreshState,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = MaterialTheme.spacing.large),
                state = state,
                refreshTriggerDistance = trigger,
                fade = true,
                scale = true,
                backgroundColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.primary
            )
        },
        onRefresh = refreshAllPagingData
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium)
        ) {
            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.now_playing_movies),
                state = nowPlayingLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = {
                    onBrowseMoviesClicked(MovieType.NowPlaying)
                }
            )
            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.explore_movies),
                state = discoverLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = onDiscoverMoviesClicked
            )
            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.upcoming_movies),
                state = upcomingLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = {
                    onBrowseMoviesClicked(MovieType.Upcoming)
                }
            )
            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.trending_movies),
                state = trendingLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = { onBrowseMoviesClicked(MovieType.Trending) }
            )
            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.top_rated_movies),
                state = topRatedLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = { onBrowseMoviesClicked(MovieType.TopRated) }
            )
            if (favoritesLazyItems.isNotEmpty()) {
                PresentableSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    title = stringResource(R.string.favourite_movies),
                    state = favoritesLazyItems,
                    onPresentableClick = onMovieClicked,
                    onMoreClick = { onBrowseMoviesClicked(MovieType.Favorite) }
                )
            }
            if (recentlyBrowsedLazyItems.isNotEmpty()) {
                PresentableSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    title = stringResource(R.string.recently_browsed_movies),
                    state = recentlyBrowsedLazyItems,
                    onPresentableClick = onMovieClicked,
                    onMoreClick = { onBrowseMoviesClicked(MovieType.RecentlyBrowsed) }
                )
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}