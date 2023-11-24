package com.jvktech.moviebuff.ui.components.chips

import androidx.compose.material3.*
import androidx.compose.runtime.Composable

@Composable
fun GenreChip(
    text: String,
) {
    SuggestionChip(
        onClick = {},
        label = { Text(text) },
        colors = SuggestionChipDefaults.suggestionChipColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = .1f)),
        border = null
    )
}