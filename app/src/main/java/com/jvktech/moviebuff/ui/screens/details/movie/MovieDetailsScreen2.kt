package com.jvktech.moviebuff.ui.screens.details.movie

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.ExternalId
import com.jvktech.moviebuff.data.model.MediaType
import com.jvktech.moviebuff.data.model.RelationType
import com.jvktech.moviebuff.data.model.ShareDetails
import com.jvktech.moviebuff.data.model.Video
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.ui.screens.destinations.MovieDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.PersonDetailsScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.RelatedMoviesScreenDestination
import com.jvktech.moviebuff.ui.screens.destinations.ReviewsScreenDestination
import com.jvktech.moviebuff.utils.openExternalId
import com.jvktech.moviebuff.utils.openVideo
import com.jvktech.moviebuff.utils.shareImdb
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination(
    navArgsDelegate = MovieDetailsScreenArgs::class,
    style = MovieDetailsScreenTransitions::class
)
@Composable
fun AnimatedVisibilityScope.MovieDetailsScreen2(
    viewModel: MovieDetailsScreenViewModel = hiltViewModel(),
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val onBackClicked: () -> Unit = { navigator.navigateUp() }
    val onFavouriteClicked: (details: MovieDetails) -> Unit = { details ->
        if (uiState.additionalMovieDetailsInfo.isFavorite) {
            viewModel.onUnlikeClick(details)
        } else {
            viewModel.onLikeClick(details)
        }
    }

    val onExternalIdClicked = { id: ExternalId ->
        openExternalId(
            context = context,
            externalId = id
        )
    }
    val onShareClicked = { details: ShareDetails ->
        shareImdb(
            context = context,
            details = details
        )
    }
    val onVideoClicked = { video: Video ->
        openVideo(
            context = context,
            video = video
        )
    }
    val onMemberClicked = { personId: Int ->
        val destination = PersonDetailsScreenDestination(
            personId = personId,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }
    val onMovieClicked = { movieId: Int ->
        val destination = MovieDetailsScreenDestination(
            movieId = movieId,
            startRoute = uiState.startRoute
        )

        navigator.navigate(destination)
    }

    val onReviewsClicked: () -> Unit = {
        val movieId = uiState.movieDetails?.id

        if (movieId != null) {
            val destination = ReviewsScreenDestination(
                startRoute = uiState.startRoute,
                mediaId = movieId,
                type = MediaType.Movie
            )

            navigator.navigate(destination)
        }
    }

    val onSimilarMoreClicked = {
        val movieId = uiState.movieDetails?.id

        if (movieId != null) {
            val destination = RelatedMoviesScreenDestination(
                movieId = movieId,
                type = RelationType.Similar,
                startRoute = uiState.startRoute
            )

            navigator.navigate(destination)
        }
    }

    val onRecommendationsMoreClicked = {
        val movieId = uiState.movieDetails?.id

        if (movieId != null) {
            val destination = RelatedMoviesScreenDestination(
                movieId = movieId,
                type = RelationType.Recommended,
                startRoute = uiState.startRoute
            )

            navigator.navigate(destination)
        }
    }
    MovieDetailsScreenContent2(
//        uiState = uiState,
//        onBackClicked = onBackClicked,
//        onExternalIdClicked = onExternalIdClicked,
//        onShareClicked = onShareClicked,
//        onVideoClicked = onVideoClicked,
//        onFavouriteClicked = onFavouriteClicked,
//        onMemberClicked = onMemberClicked,
//        onMovieClicked = onMovieClicked,
//        onSimilarMoreClicked = onSimilarMoreClicked,
//        onRecommendationsMoreClicked = onRecommendationsMoreClicked,
//        onReviewsClicked = onReviewsClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreenContent2(

) {

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Leo",
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Filled.Share,
                            contentDescription = "Share"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
            )
        }
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .height(250.dp)
                .padding(innerPadding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.icc_wc),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 8.dp, bottom = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Poster Image
                Box(
                    modifier = Modifier
                        .size(width = 80.dp, height = 110.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_demo_poster),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(8.dp))
                    )
                }

                // Title Text
                Text(
                    text = "Leo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )


            }
        }

//// Certification, Release Date, and Runtime in a Row scope
//        Row {
//            Text(
//                text = "U/A",
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White,
//                modifier = Modifier.padding(end = 4.dp)
//            )
//            Text(
//                text = "16 Apr 2021",
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White,
//                modifier = Modifier.padding(horizontal = 4.dp)
//            )
//            Text(
//                text = "\u00B7", // Unicode for middle dot (·)
//                fontSize = 14.sp,
//                color = Color.White,
//                modifier = Modifier.padding(horizontal = 4.dp)
//            )
//
//            Text(
//                text = "124 Mins",
//                fontSize = 14.sp,
//                fontWeight = FontWeight.Bold,
//                color = Color.White,
//                modifier = Modifier.padding(horizontal = 4.dp)
//            )
//        }

    }
}

@Composable
@Preview
fun MovieDetailsScreen2Preview() {
    MovieDetailsScreenContent2()
}