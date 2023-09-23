package com.jvktech.moviebuff.ui.screens.home

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.collectAsLazyPagingItems
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.jvktech.moviebuff.MainViewModel
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.movie.MovieType
import com.jvktech.moviebuff.data.model.tvshow.TvShowType
import com.jvktech.moviebuff.ui.components.others.AboutBottomSheet
import com.jvktech.moviebuff.ui.components.others.DonateBottomSheet
import com.jvktech.moviebuff.ui.components.others.StoryLogo
import com.jvktech.moviebuff.ui.components.sections.PresentableSection
import com.jvktech.moviebuff.ui.screens.destinations.BrowseMoviesScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.BrowseTvShowsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.DiscoverMoviesScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.DiscoverTvShowScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.HomeScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.MovieDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.TvShowDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.TvShowScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.UpdatesChannelDestination
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.isAnyRefreshing
import com.jvktech.moviebuff.utils.isNotEmpty
import com.jvktech.moviebuff.utils.refreshAll
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph(start = true)
@Destination
@Composable
fun AnimatedVisibilityScope.HomeScreen(
    mainViewModel: MainViewModel,
    viewModel: HomeScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    LaunchedEffect(Unit) {
        mainViewModel.sameBottomBarRoute.collectLatest { sameRoute ->
            if (sameRoute == HomeScreenDestination.route) {
                scrollState.animateScrollTo(0)
            }
        }
    }
    val onMovieClicked = { movieId: Int ->
        val destination = MovieDetailsScreenDestination(
            movieId = movieId,
            startRoute = HomeScreenDestination.route
        )
        navigator.navigate(destination)
    }

    val onBrowseMoviesClicked = { type: MovieType ->
        navigator.navigate(BrowseMoviesScreenDestination(type))
    }

    val onDiscoverMoviesClicked = {
        navigator.navigate(DiscoverMoviesScreenDestination)
    }

    val onTvShowClicked = { tvShowId: Int ->
        val destination =
            TvShowDetailsScreenDestination(
                tvShowId = tvShowId,
                startRoute = TvShowScreenDestination.route
            )
        navigator.navigate(destination)
    }

    val onBrowseTvShowClicked: (TvShowType) -> Unit = { type ->
        navigator.navigate(BrowseTvShowsScreenDestination(type))
    }

    val onDiscoverTvShowClicked = {
        navigator.navigate(DiscoverTvShowScreenDestination)
    }

    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    var showAbout by rememberSaveable {
        mutableStateOf(false)
    }

    if (showAbout) {
        AboutBottomSheet(
            onDismiss = { showAbout = false }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize().nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    StoryLogo(onClick = { navigator.navigate(UpdatesChannelDestination) })
                },
                actions = {
                    IconButton(
                        onClick = { showAbout = true }
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "Mark as favorite"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = innerPadding.calculateTopPadding())
        ) {

            HomeScreenContent(
                uiState = uiState,
                scrollState = scrollState,
                onMovieClicked = onMovieClicked,
                onBrowseMoviesClicked = onBrowseMoviesClicked,
                onDiscoverMoviesClicked = onDiscoverMoviesClicked,
                onTvShowClicked = onTvShowClicked,
                onBrowseTvShowClicked = onBrowseTvShowClicked,
                onDiscoverTvShowClicked = onDiscoverTvShowClicked
            )

        }
    }

}


@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreenContent(
    uiState: MovieScreenUIState,
    scrollState: ScrollState,
    // Movie
    onMovieClicked: (movieId: Int) -> Unit,
    onBrowseMoviesClicked: (type: MovieType) -> Unit,
    onDiscoverMoviesClicked: () -> Unit,
    // Tv Series
    onTvShowClicked: (tvShowId: Int) -> Unit,
    onBrowseTvShowClicked: (type: TvShowType) -> Unit,
    onDiscoverTvShowClicked: () -> Unit
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    // Movies
    val discoverMoviesLazyItems = uiState.moviesState.discoverMovies.collectAsLazyPagingItems()
    val upcomingMoviesLazyItems = uiState.moviesState.upcomingMovies.collectAsLazyPagingItems()
    val topRatedMoviesLazyItems = uiState.moviesState.topRatedMovies.collectAsLazyPagingItems()
    val trendingMoviesLazyItems = uiState.moviesState.trendingMovies.collectAsLazyPagingItems()
    val nowPlayingMoviesLazyItems = uiState.moviesState.nowPlayingMovies.collectAsLazyPagingItems()
    val favoriteMoviesLazyItems = uiState.favoriteMovies.collectAsLazyPagingItems()
    val recentlyBrowsedMoviesLazyItems = uiState.recentlyBrowsedMovies.collectAsLazyPagingItems()
    // Tv series
    val topRatedTvSeriesLazyItems = uiState.moviesState.topRatedTvSeries.collectAsLazyPagingItems()
    val discoverTvSeriesLazyItems = uiState.moviesState.discoverTvSeries.collectAsLazyPagingItems()
    val onTheAirTvSeriesLazyItems = uiState.moviesState.onTheAirTvSeries.collectAsLazyPagingItems()
    val trendingTvSeriesLazyItems = uiState.moviesState.trendingTvSeries.collectAsLazyPagingItems()
    val airingTodayTvSeriesLazyItems = uiState.moviesState.airingTodayTvSeries.collectAsLazyPagingItems()
    val favoriteTvSeriesLazyItems = uiState.favoriteTvSeries.collectAsLazyPagingItems()
    val recentlyBrowsedTvSeriesLazyItems = uiState.recentlyBrowsedTvSeries.collectAsLazyPagingItems()

    var topSectionHeight: Float? by remember {
        mutableStateOf(null)
    }

    val appBarHeight = density.run { 56.dp.toPx() }
    val topSectionScrollLimitValue: Float? = topSectionHeight?.minus(appBarHeight)

    // show Alert dialog on back pressed
//    var showExitDialog by remember {
//        mutableStateOf(false)
//    }
//    val dismissDialog = {
//        showExitDialog = false
//    }
//    BackHandler {
//        showExitDialog = true
//    }
//
//    if (showExitDialog) {
//        ExitDialog(
//            onDismissRequest = dismissDialog,
//            onCancelClick = dismissDialog,
//            onConfirmClick = {
//                val activity = (context as? Activity)
//                activity?.finish()
//            }
//        )
//    }

    val isRefreshing by derivedStateOf {
        listOf(
            // Movies
            discoverMoviesLazyItems,
            upcomingMoviesLazyItems,
            topRatedMoviesLazyItems,
            trendingMoviesLazyItems,
            nowPlayingMoviesLazyItems,
            // Tv Series
            topRatedTvSeriesLazyItems,
            discoverTvSeriesLazyItems,
            onTheAirTvSeriesLazyItems,
            trendingTvSeriesLazyItems,
            airingTodayTvSeriesLazyItems,
        ).isAnyRefreshing()
    }

    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)

    val refreshAllPagingData = {
        listOf(
            // Movies
            discoverMoviesLazyItems,
            upcomingMoviesLazyItems,
            topRatedMoviesLazyItems,
            trendingMoviesLazyItems,
            nowPlayingMoviesLazyItems,
            // Tv Series
            topRatedTvSeriesLazyItems,
            discoverTvSeriesLazyItems,
            onTheAirTvSeriesLazyItems,
            trendingTvSeriesLazyItems,
            airingTodayTvSeriesLazyItems,
        ).refreshAll()
    }

    LaunchedEffect(isRefreshing) {
        swipeRefreshState.isRefreshing = isRefreshing
    }

    SwipeRefresh(
        modifier = Modifier
            .fillMaxSize(),
        state = swipeRefreshState,
        indicator = { state, trigger ->
            SwipeRefreshIndicator(
                modifier = Modifier
                    .statusBarsPadding(),
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
                state = nowPlayingMoviesLazyItems,
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
                state = discoverMoviesLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = onDiscoverMoviesClicked
            )

            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned { coordinates ->
                        topSectionHeight = coordinates.size.height.toFloat()
                    },
                title = stringResource(R.string.now_airing_tv_series),
                state = onTheAirTvSeriesLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.OnTheAir)
                }
            )

            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.upcoming_movies),
                state = upcomingMoviesLazyItems,
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
                state = trendingMoviesLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = { onBrowseMoviesClicked(MovieType.Trending) }
            )

            PresentableSection(
                title = stringResource(R.string.explore_tv_series),
                state = discoverTvSeriesLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = onDiscoverTvShowClicked
            )

            PresentableSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize(),
                title = stringResource(R.string.top_rated_movies),
                state = topRatedMoviesLazyItems,
                onPresentableClick = onMovieClicked,
                onMoreClick = { onBrowseMoviesClicked(MovieType.TopRated) }
            )

            PresentableSection(
                title = stringResource(R.string.top_rated_tv_series),
                state = topRatedTvSeriesLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.TopRated)
                }
            )

            PresentableSection(
                title = stringResource(R.string.trending_tv_series),
                state = trendingTvSeriesLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.Trending)
                }
            )
            PresentableSection(
                title = stringResource(R.string.today_airing_tv_series),
                state = airingTodayTvSeriesLazyItems,
                onPresentableClick = onTvShowClicked,
                onMoreClick = {
                    onBrowseTvShowClicked(TvShowType.AiringToday)
                }
            )

            if (favoriteMoviesLazyItems.isNotEmpty()) {
                PresentableSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    title = stringResource(R.string.favourite_movies),
                    state = favoriteMoviesLazyItems,
                    onPresentableClick = onMovieClicked,
                    onMoreClick = { onBrowseMoviesClicked(MovieType.Favorite) }
                )
            }
            if (recentlyBrowsedMoviesLazyItems.isNotEmpty()) {
                PresentableSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateContentSize(),
                    title = stringResource(R.string.recently_browsed_movies),
                    state = recentlyBrowsedMoviesLazyItems,
                    onPresentableClick = onMovieClicked,
                    onMoreClick = { onBrowseMoviesClicked(MovieType.RecentlyBrowsed) }
                )
            }

            if(favoriteTvSeriesLazyItems.isNotEmpty()){
                PresentableSection(
                    title = stringResource(R.string.favourites_tv_series),
                    state = favoriteTvSeriesLazyItems,
                    onPresentableClick = onTvShowClicked,
                    onMoreClick = {
                        onBrowseTvShowClicked(TvShowType.Favorite)
                    }
                )
            }
            if (recentlyBrowsedTvSeriesLazyItems.isNotEmpty()){
                PresentableSection(
                    title = stringResource(R.string.recently_browsed_tv_series),
                    state = recentlyBrowsedTvSeriesLazyItems,
                    onPresentableClick = onTvShowClicked,
                    onMoreClick = {
                        onBrowseTvShowClicked(TvShowType.RecentlyBrowsed)
                    }
                )
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        }
    }
}