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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.ui.components.texts.LabeledText
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.convertDate
import com.jvktech.moviebuff.utils.formattedMoney
import com.jvktech.moviebuff.utils.formattedRuntime


@Composable
fun MovieDetailsTopContent(
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
                LabeledText(
                    label = stringResource(R.string.movie_details_status),
                    text = stringResource(details.status.getLabel())
                )

                if (details.budget > 0) {
                    LabeledText(
                        label = stringResource(R.string.movie_details_budget),
                        text = details.budget.formattedMoney()
                    )
                }
                if (details.revenue > 0) {
                    LabeledText(
                        label = stringResource(R.string.movie_details_boxoffice),
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
                        text = details.originalTitle,
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
fun MovieBasicDetailsContent(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {

                Box(
                    modifier = Modifier
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.inverseSurface,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                        text = "UA",
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                details.runtime?.formattedRuntime()?.let {
                    Text(
                        text = it,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                details.releaseDate?.let {
                    Text(
                        text = convertDate(details.releaseDate),
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

        }
    }
}


@Composable
fun MovieRuntimeDetails(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Crossfade(
        modifier = modifier,
        targetState = movieDetails,
        label = ""
    ) { details ->
        if (details != null) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {

                Box(
                    modifier = Modifier
                        .border(
                            1.dp,
                            MaterialTheme.colorScheme.inverseSurface,
                            shape = RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        modifier = Modifier.padding(MaterialTheme.spacing.extraSmall),
                        text = "UA",
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                details.runtime?.formattedRuntime()?.let {
                    Text(
                        text = it,
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                details.releaseDate?.let {
                    Text(
                        text = convertDate(details.releaseDate),
                        fontFamily = FontFamily.SansSerif,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

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
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .clickable { }
                    .width(120.dp)
                    .clip(RoundedCornerShape(16.dp)),
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
            ) {
                // First Column - IMDb Icon
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_imdb),
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                            .clip(RoundedCornerShape(12.dp))
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
                        text = details.voteAverage.toString(),
                        modifier = Modifier.padding(bottom = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = details.voteCount.toString(),
                            style = MaterialTheme.typography.bodySmall
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Icon(
                            imageVector = Icons.Default.People,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun RatingBox() {

    Row(
        modifier = Modifier
            .clickable { }
            .width(125.dp)
            .clip(shape = RoundedCornerShape(20.dp))
//                horizontalArrangement = Arrangement.Center,
//                verticalAlignment = Alignment.CenterVertically
    ) {
        // First Column - IMDb Icon
        Box(
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_imdb),
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
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
                text = "6.0",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "400",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.width(8.dp))

                Icon(
                    imageVector = Icons.Default.People,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
            }
        }
    }


}


@Composable
@Preview
fun RatingBoxPreview() {
    RatingBox()
}