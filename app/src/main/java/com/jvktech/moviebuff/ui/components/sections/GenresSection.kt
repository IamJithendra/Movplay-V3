package com.jvktech.moviebuff.ui.components.sections

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jvktech.moviebuff.data.model.Genre
import com.jvktech.moviebuff.ui.components.chips.GenreChip
import com.jvktech.moviebuff.ui.theme.spacing
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun GenresSection(
    genres: List<Genre>,
    modifier: Modifier = Modifier
) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = MaterialTheme.spacing.extraSmall,
        crossAxisSpacing = MaterialTheme.spacing.extraSmall
    ) {
        genres.map { genre ->
            GenreChip(text = genre.name)
        }
    }
}