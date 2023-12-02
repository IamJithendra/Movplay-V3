package com.jvktech.moviebuff.ui.screens.details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PeopleAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.movie.MovieDetails

@Composable
fun MultiRatings(
    movieDetails: MovieDetails?,
    imgSrc: Int
) {
    Crossfade(
        modifier = Modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(72.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(MaterialTheme.colorScheme.surface)
                        .clickable { /* Handle click for imageLogo */ }
                ) {
                    Image(
                        painter = painterResource(id = imgSrc),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = details.voteAverage.toString().take(3),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )

                Spacer(modifier = Modifier.height(4.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = details.voteCount.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                    )

                    // Add the icon for PeopleAlt
                    Icon(
                        imageVector = Icons.Default.PeopleAlt,
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .padding(4.dp)
                    )
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MultiRatingsBottomSheet(
    movieDetails: MovieDetails,
    onDismiss: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.padding(bottom = 8.dp),
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        windowInsets = WindowInsets(0, 0, 0, 0),
        tonalElevation = 10.dp,
        containerColor = MaterialTheme.colorScheme.background,
        dragHandle = null,
        content = {
            Column(
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .heightIn(
                        min = 100.dp,
                        max = 100.dp
                    ), // Adjust the max and min height as needed
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MultiRatings(movieDetails, R.drawable.logo_imdb)
                    MultiRatings(movieDetails, R.drawable.logo_tmdb)
                    MultiRatings(movieDetails, R.drawable.logo_trakt)
                    MultiRatings(movieDetails, R.drawable.logo_moviebase)
                }
            }
        }
    )
}


//@Composable
//@Preview
//fun MultiRatingsBottomSheetPreview() {
//    MultiRatingsBottomSheet(onDismiss = { })
//}