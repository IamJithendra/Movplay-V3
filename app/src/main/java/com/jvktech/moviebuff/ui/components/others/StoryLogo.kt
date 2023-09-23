package com.jvktech.moviebuff.ui.components.others

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.R

@Composable
fun StoryLogo() {


    Card(
        modifier = Modifier.size(50.dp)
            .clip(CircleShape),
        shape = CircleShape,
        border = BorderStroke(
            2.5.dp,
            Brush.linearGradient(
                colors = listOf(
                    Color(0xFF00ee6e),
                    Color(0xFF0c75e6)
                )
            )
        ),
    )
    {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.CenterHorizontally)
                .background(MaterialTheme.colorScheme.background),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.surfaceTint)
        )
    }
}


@Composable
@Preview
fun StoryLogoPreview() {
    StoryLogo()
}