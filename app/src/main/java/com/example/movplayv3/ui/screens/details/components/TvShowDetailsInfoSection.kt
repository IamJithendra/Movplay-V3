package com.example.movplayv3.ui.screens.details.components

import android.annotation.SuppressLint
import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movplayv3.R
import com.example.movplayv3.data.model.ExternalId
import com.example.movplayv3.data.model.ShareDetails
import com.example.movplayv3.data.model.tvshow.TvShowDetails
import com.example.movplayv3.ui.components.sections.GenresSection
import com.example.movplayv3.ui.components.texts.AdditionalInfoText
import com.example.movplayv3.ui.components.texts.ExpandableText
import com.example.movplayv3.ui.theme.spacing
import com.example.movplayv3.utils.yearRangeString

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun TvShowDetailsInfoSection(
    tvShowDetails: TvShowDetails?,
    nextEpisodeDaysRemaining: Long?,
    modifier: Modifier = Modifier,
    imdbExternalId: ExternalId.Imdb? = null,
    onShareClicked: (ShareDetails) -> Unit = {}
) {
    val otherOriginalTitle: Boolean by derivedStateOf {
        tvShowDetails?.run { !originalName.isNullOrEmpty() && title != originalName } ?: false
    }

    Crossfade(
        modifier = modifier,
        targetState = tvShowDetails,
        label = ""
    ) { details ->
        if (details != null) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = details.name,
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.ExtraBold
                        )
                        details.originalName?.let { name ->
                            if (otherOriginalTitle) {
                                Text(
                                    modifier = Modifier.fillMaxWidth(),
                                    text = name
                                )
                            }
                        }
                        AdditionalInfoText(
                            modifier = Modifier.fillMaxWidth(),
                            infoTexts = details.run {
                                listOfNotNull(
                                    yearRangeString(
                                        from = firstAirDate,
                                        to = lastAirDate
                                    ),
                                    nextEpisodeDaysRemaining?.let { days ->
                                        when (days) {
                                            0L -> stringResource(R.string.next_episode_today_text)
                                            1L -> stringResource(R.string.next_episode_tomorrow_text)
                                            else -> stringResource(
                                                R.string.next_episode_days_text,
                                                days
                                            )
                                        }
                                    }
                                )
                            }
                        )
                    }
                    AnimatedVisibility(
                        visible = imdbExternalId != null,
                        enter = fadeIn() + scaleIn(initialScale = 0.7f),
                        exit = fadeOut() + scaleOut()
                    ) {
                        IconButton(
                            modifier = Modifier.background(
                                color = MaterialTheme.colorScheme.surface,
                                shape = CircleShape
                            ),
                            onClick = {
                                imdbExternalId?.let { id ->
                                    val shareDetails = ShareDetails(
                                        title = details.title,
                                        imdbId = id
                                    )

                                    onShareClicked(shareDetails)
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "share",
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
                if (details.genres.isNotEmpty()) {
                    GenresSection(
                        genres = details.genres
                    )
                }

                Column(
                    modifier = Modifier.padding(top = MaterialTheme.spacing.small),
                    verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
                ) {
                    details.tagline.let { tagline ->
                        if (tagline.isNotEmpty()) {
                            Text(
                                text = "\"$tagline\"",
                                fontStyle = FontStyle.Italic,
                                fontSize = 12.sp
                            )
                        }
                    }

                    details.overview.let { overview ->
                        if (overview.isNotBlank()) {
                            ExpandableText(
                                modifier = Modifier.fillMaxWidth(),
                                text = overview
                            )
                        }
                    }
                }
            }
        }
    }
}