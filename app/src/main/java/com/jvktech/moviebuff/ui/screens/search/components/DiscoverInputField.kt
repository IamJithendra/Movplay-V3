package com.jvktech.moviebuff.ui.screens.search.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.ui.screens.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreenTextField(
    navigator: DestinationsNavigator,
) {

    AssistChip(
        modifier = Modifier
            .fillMaxWidth(),
        onClick = { navigator.navigate(SearchScreenDestination.route) },
        label = {
            androidx.compose.material3.Text(
                modifier = Modifier.padding(start=5.dp),
                text = "Search Movie, show or Actor",
                color = MaterialTheme.colorScheme.primary
            )
        },
        leadingIcon = {
            androidx.compose.material3.Icon(
                Icons.Filled.Search,
                contentDescription = "Localized description",
                Modifier.size(AssistChipDefaults.IconSize)
            )
        }
    )

}

