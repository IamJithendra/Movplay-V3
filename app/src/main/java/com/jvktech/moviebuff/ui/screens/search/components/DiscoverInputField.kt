package com.jvktech.moviebuff.ui.screens.search.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.jvktech.moviebuff.ui.screens.destinations.SearchScreenDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverScreenTextField(
    navigator: DestinationsNavigator,
) {

    androidx.compose.material3.OutlinedTextField(
        enabled = false,
        value = "",
        onValueChange = { /* Handle text change */ },
        label = { androidx.compose.material3.Text(text = "Search Movie, show or Actor") },
        leadingIcon = {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = {
                navigator.navigate(SearchScreenDestination.route)
            }),
        colors = TextFieldDefaults.outlinedTextFieldColors()
    )
}

