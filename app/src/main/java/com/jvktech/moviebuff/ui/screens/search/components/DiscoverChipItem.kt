package com.jvktech.moviebuff.ui.screens.search.components

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Sort
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiscoverChipRow(
    filterOptions: List<Pair<String, ImageVector>>,
    selectedFilters: Set<String>,
    onChipClicked: () -> Unit // Updated parameter
) {
    val selectedIconStates = remember(filterOptions) { mutableStateMapOf<String, Boolean>() }
    var isFirstChipMovies by remember { mutableStateOf(true) }

    LazyRow(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)) {
        itemsIndexed(filterOptions) { index, (filterOption, icon) ->
            val isSelected = selectedFilters.contains(filterOption)
            val isArrowUp = selectedIconStates[filterOption] ?: false

            val adjustedFilterOption = when {
                index == 0 && !isFirstChipMovies -> "TV series"
                index == 0 && isFirstChipMovies -> "Movies"
                else -> filterOption
            }

            val adjustedIcon = if ((filterOption == "Watch providers" || filterOption == "Genres") && isArrowUp) {
                Icons.Default.ArrowDropUp
            } else {
                icon
            }

            AssistChip(
                onClick = {
                    Log.d("Assist chip", "hello world")
                    if (filterOption == "Watch providers" || filterOption == "Genres") {
                        selectedIconStates[filterOption] = !isArrowUp
                    } else if (index == 0) {
                        isFirstChipMovies = !isFirstChipMovies
                    }

                },
                label = {
                    Text(
                        text = adjustedFilterOption,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = adjustedIcon,
                        contentDescription = "Localized description",
                        Modifier.size(AssistChipDefaults.IconSize)
                    )
                },
                // Apply padding to the chip
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}




@Composable
@Preview
fun FilterChipRowPreview() {
    val filterOptions = listOf(
        Pair("Movies", Icons.Default.SwapHoriz),
        Pair("Popularity", Icons.Default.Sort),
        Pair("Watch providers", Icons.Default.ArrowDropDown),
        Pair("Genres", Icons.Default.ArrowDropDown)
    )
    val selectedFilters by remember { mutableStateOf(setOf<String>()) }

    DiscoverChipRow(
        filterOptions = filterOptions,
        selectedFilters = selectedFilters
    ) { }
}

