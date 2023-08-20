package com.jvktech.moviebuff.ui.components.items

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvktech.moviebuff.ui.theme.DarkGreen
import com.jvktech.moviebuff.ui.theme.DarkRed
import com.jvktech.moviebuff.ui.theme.LightGreen
import com.jvktech.moviebuff.ui.theme.Orange
import com.jvktech.moviebuff.ui.theme.Red
import com.jvktech.moviebuff.ui.theme.Yellow
import com.jvktech.moviebuff.ui.theme.spacing

@Composable
fun PresentableScoreItem(
    score: Float,
    modifier: Modifier = Modifier,
    scoreRange: ClosedFloatingPointRange<Float> = 0f..10f,
    animationEnabled: Boolean = true
) {
    val progress = score / scoreRange.run { endInclusive - start }

    val animatedProgress = remember { Animatable(0f) }

    val percent = (progress * 100).toInt()

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

    val backgroundColor = MaterialTheme.colorScheme.background.copy(alpha = 0.7f)

    val scoreText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.White
            )
        ) {
            append(percent.toString())
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                color = Color.White
            )
        ) {
            append("%")
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Surface(
            modifier = Modifier
                .padding(end = 8.dp, top = 8.dp)
                .align(Alignment.TopEnd)
                .clip(RoundedCornerShape(12.dp)),
            // TODO use little more better background
            color = MaterialTheme.colorScheme.tertiaryContainer

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

//@Preview
//@Composable
//fun PresentableScoreItemPreview() {
//    PresentableScoreItem(
//        score = 10f
//    )
//}