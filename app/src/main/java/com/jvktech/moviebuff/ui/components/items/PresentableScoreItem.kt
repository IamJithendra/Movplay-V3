package com.jvktech.moviebuff.ui.components.items

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.ui.theme.DarkGreen
import com.jvktech.moviebuff.ui.theme.DarkRed
import com.jvktech.moviebuff.ui.theme.LightGreen
import com.jvktech.moviebuff.ui.theme.Orange
import com.jvktech.moviebuff.ui.theme.Red
import com.jvktech.moviebuff.ui.theme.Yellow

@Composable
fun PresentableScoreItem(
    score: Float,
    modifier: Modifier = Modifier,
    scoreRange: ClosedFloatingPointRange<Float> = 0f..10f,
    animationEnabled: Boolean = true
) {
    val progress = score / scoreRange.run { endInclusive - start }

    val animatedProgress = remember { Animatable(0f) }

    val oneDecimalScore = ((score * 10).toInt() / 10.0).toString()

    LaunchedEffect(progress, animationEnabled) {
        if (animationEnabled) {
            animatedProgress.animateTo(progress, animationSpec = tween(durationMillis = 700))
        } else {
            animatedProgress.snapTo(progress)
        }
    }

    val indicatorColor by animateColorAsState(
        targetValue = when (progress) {
            in 0f..0.15f -> DarkRed
            in 0.15f..0.3f -> Red
            in 0.3f..0.45f -> Orange
            in 0.45f..0.7f -> Yellow
            in 0.7f..0.85f -> LightGreen
            else -> DarkGreen
        },
        label = ""
    )

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {

        Surface(
            modifier = Modifier
                .padding(end = 8.dp, top = 8.dp)
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(12.dp)),
            // TODO use little more better background
            color = MaterialTheme.colorScheme.background

        ) {
            Text(
                text = oneDecimalScore,
                modifier = Modifier
                    .padding(
                        start = 8.dp,
                        top = 2.dp,
                        end = 8.dp,
                        bottom = 2.dp
                    ),
                color = indicatorColor,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                fontWeight = FontWeight.Bold
            )
        }

    }
}