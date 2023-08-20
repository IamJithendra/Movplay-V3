package com.jvktech.moviebuff.ui.screens.details.components

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.movie.MovieDetails
import com.jvktech.moviebuff.ui.components.texts.LabeledText
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.formattedMoney

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