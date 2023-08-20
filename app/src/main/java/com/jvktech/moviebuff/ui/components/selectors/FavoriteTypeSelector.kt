package com.jvktech.moviebuff.ui.components.selectors

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.data.model.FavoriteType
import com.jvktech.moviebuff.ui.theme.spacing

@Composable
fun FavoriteTypeSelector(
    selected: FavoriteType,
    modifier: Modifier = Modifier,
    onSelected: (FavoriteType) -> Unit = {}
) {
    Row(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.small
            )
            .clip(shape = MaterialTheme.shapes.small),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FavoriteType.values().map { type ->
            FavouriteTypeButton(
                modifier = Modifier.weight(1f),
                type = type,
                selected = type == selected,
                onClick = { onSelected(type) }
            )
        }
    }
}


@Composable
fun FavouriteTypeButton(
    type: FavoriteType,
    selected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) {
            MaterialTheme.colorScheme.primary
        } else Color.Transparent,
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) {
            Color.White
        } else MaterialTheme.colorScheme.primary,
        label = ""
    )

    Box(
        modifier = modifier
            .background(backgroundColor)
            .clickable { onClick() }
            .padding(MaterialTheme.spacing.small),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(type.getLabelResourceId()),
            color = textColor,
            fontWeight = FontWeight.Bold
        )
    }
}