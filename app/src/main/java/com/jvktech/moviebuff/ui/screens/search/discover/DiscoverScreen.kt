package com.jvktech.moviebuff.ui.screens.search.discover

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Tv
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvktech.moviebuff.ui.screens.search.components.DiscoverScreenTextField
import com.jvktech.moviebuff.ui.theme.spacing
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Destination
@Composable
fun DiscoverScreen(
    navigator: DestinationsNavigator,
) {

    Column (
        modifier = Modifier.fillMaxSize()
            .statusBarsPadding()
            .padding(MaterialTheme.spacing.medium),
        verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.medium),
        horizontalAlignment = Alignment.Start
    ) {

        Text(
            text = "Discover",
            fontWeight = Bold,
            fontSize = 25.sp,
            color = MaterialTheme.colorScheme.primary
        )

        DiscoverScreenTextField()

        Text(
            text = "Top categories",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust the spacing value as needed
        ) {
            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Movies clicked") },
                label = {
                    Text(
                        text = "Movies",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Movie,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Tv shows clicked") },
                label = {
                    Text(
                        text = "Tv shows",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Tv,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Actors clicked") },
                label = {
                    Text(
                        text = "Actors",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Localized description",
                        modifier = Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust the spacing value as needed
        ) {

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Trailers clicked") },
                label = {
                    Text(
                        text = "Trailers",
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        Icons.Filled.PlayArrow,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                }
            )
        }


        Text(
            text = "General",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust the spacing value as needed
        ) {
            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Networks clicked") },
                label = {
                    Text(
                        text = "Networks",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Production companies clicked") },
                label = {
                    Text(
                        text = "Production companies",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust the spacing value as needed
        ) {

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Movie Genres clicked") },
                label = {
                    Text(
                        text = "Movie Genres",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Show Genres clicked") },
                label = {
                    Text(
                        text = "Show Genres",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Videos clicked") },
                label = {
                    Text(
                        text = "Videos",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )

        }


        Text(
            text = "Streaming",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 5.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Adjust the spacing value as needed
        ) {
            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Networks clicked") },
                label = {
                    Text(
                        text = "Netflix releases",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )

            AssistChip(
                onClick = { Timber.tag("Assist chip").d("Production companies clicked") },
                label = {
                    Text(
                        text = "Netflix Expirations",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )
        }


    }
}
