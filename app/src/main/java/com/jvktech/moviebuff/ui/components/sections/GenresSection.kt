package com.jvktech.moviebuff.ui.components.sections

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jvktech.moviebuff.data.model.Genre
import com.jvktech.moviebuff.ui.components.chips.GenreChip
import com.jvktech.moviebuff.ui.theme.spacing

@Composable
fun GenresSection(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = MaterialTheme.spacing.extraSmall),
        horizontalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.extraSmall)
    ) {
        items(genres.size) { index ->
            val genre = genres[index]
            GenreChip(text = genre.name)
        }
    }
}
