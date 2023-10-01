package com.jvktech.moviebuff.ui.components.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FilterFloatingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick
    ) {
        // TODO change this icon to Filter funnel
        Icon(
            imageVector = Icons.Filled.Filter,
            contentDescription = "filter"
        )
    }
}


@Composable
@Preview
fun FilterFloatingButtonPreview(){
    FilterFloatingButton(
        modifier = Modifier,
        onClick = { }
    )
}