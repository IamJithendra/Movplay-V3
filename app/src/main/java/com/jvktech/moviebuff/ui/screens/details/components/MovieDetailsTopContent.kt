package com.jvktech.moviebuff.ui.screens.details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.ui.components.texts.DetailTextRow
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.convertDate
import com.jvktech.moviebuff.utils.formattedMoney
import com.jvktech.moviebuff.utils.formattedRuntime
import com.jvktech.moviebuff.utils.timeString
import java.util.Date


@Composable
fun MovieDetailedInfo(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {



            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {

                Text(
                    modifier = modifier.padding(MaterialTheme.spacing.medium),
                    text = "Information",
                    fontSize = 24.sp
                )

                DetailTextRow(
                    label = "Original Title",
                    text = details.originalTitle
                )

                DetailTextRow(
                    label = "Status",
                    text = stringResource(details.status.getLabel())
                )


                details.runtime?.formattedRuntime()?.let {
                    DetailTextRow(
                        label = "Runtime",
                        text = it
                    )
                }

                DetailTextRow(
                    label = "Original language",
                    text = details.originalLanguage
                )


                Row(
                    modifier = modifier.height(50.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.padding(MaterialTheme.spacing.medium),
                        text = "Production Countries"
                    )

                    details.productionCountries.map {
                        Text(
                            modifier = modifier.padding(MaterialTheme.spacing.medium),
                            text = details.productionCountries.joinToString(", ") { it.name },
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

                Row(
                    modifier = modifier.height(80.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.padding(MaterialTheme.spacing.medium),
                        text = "Production Companies"
                    )

                    details.productionCountries.map {
                        Text(
                            modifier = modifier.padding(MaterialTheme.spacing.medium),
                            text = details.productionCountries.joinToString(", ") { it.name },
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                }

                if (details.budget > 0) {
                    DetailTextRow(
                        label = "Budget",
                        text = details.budget.formattedMoney()
                    )
                }

                if (details.revenue > 0) {
                    DetailTextRow(
                        label = "Box office",
                        text = details.revenue.formattedMoney()
                    )
                }

            }
        }
    }
}


@Composable
fun MovieTitle(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {


                if (details.originalTitle.isNotEmpty()) {
                    Text(
                        text = details.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        style = MaterialTheme.typography.titleSmall
                    )
                }
            }

        }
    }
}


@Composable
fun MovieRuntimeDetails(
    movieDetails: MovieDetails?,
    watchAtTime: Date?,
    modifier: Modifier = Modifier
) {

    val watchAtTimeString = watchAtTime?.let { time ->
        stringResource(R.string.movie_details_watch_at, time.timeString())
    }

    Crossfade(
        modifier = modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.inverseSurface,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    // TODO show actual certification
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                        text = "UA",
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                details.runtime?.formattedRuntime()?.let {
                    Text(
                        text = it,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                details.releaseDate?.let {
                    Text(
                        text = convertDate(details.releaseDate),
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }

            }

            // TODO add end watching at
//            if (watchAtTimeString != null) {
//                Text(
//                    text = watchAtTimeString,
//                    fontFamily = FontFamily.SansSerif,
//                    style = MaterialTheme.typography.bodyMedium,
//                    fontWeight = FontWeight.SemiBold
//                )
//            }

        }
    }
}


@Composable
fun MovieRatingsDetails(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {

            var showMultiRatings by rememberSaveable {
                mutableStateOf(false)
            }

            if (showMultiRatings) {
                MultiRatingsBottomSheet(
                    movieDetails = details,
                    onDismiss = { showMultiRatings = false }
                )
            }

            Card(
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier
                    .padding(start = MaterialTheme.spacing.medium)
                    .wrapContentSize()
                    .clickable { showMultiRatings = true }
                    .width(125.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // First Column - IMDb Icon
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .background(MaterialTheme.colorScheme.surface),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_tmdb),
                            contentDescription = null,
                            modifier = Modifier
                                .size(50.dp)
                                .padding(4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(4.dp))

                    // Second Column - Text and Icon
                    Column(
                        modifier = Modifier
                            .padding(start = 8.dp),
                        verticalArrangement = Arrangement.SpaceEvenly // Adjusted vertical arrangement to top
                    ) {
                        Text(
                            text = details.voteAverage.toString().take(3),
                            modifier = Modifier.padding(bottom = 8.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                        )

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                modifier = Modifier.padding(vertical = 4.dp),
                                text = details.voteCount.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                fontWeight = FontWeight.SemiBold
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Icon(
                                imageVector = Icons.Default.People,
                                contentDescription = null,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
