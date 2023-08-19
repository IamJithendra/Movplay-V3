package com.example.movplayv3.ui.components.button

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.with
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.movplayv3.ui.theme.spacing

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LikeButton(
    isFavourite: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier.padding(end = MaterialTheme.spacing.extraSmall),
        onClick = onClick,
    ) {
        AnimatedContent(
            targetState = isFavourite,
            contentAlignment = Alignment.Center,
            transitionSpec = {
                fadeIn(animationSpec = tween(200)) + scaleIn(
                    animationSpec = tween(200, delayMillis = 200),
                    initialScale = 0.8f
                ) with scaleOut(
                    animationSpec = tween(200),
                    targetScale = 0.8f
                )
            }
        ) { favourite ->
            if (favourite) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "add to favourite",
                    tint = Color.Red
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.FavoriteBorder,
                    contentDescription = "remove from favourites",
                    tint = Color.White
                )
            }
        }
    }
}