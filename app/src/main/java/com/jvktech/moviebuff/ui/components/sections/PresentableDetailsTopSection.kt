package com.jvktech.moviebuff.ui.components.sections

import android.annotation.SuppressLint
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.jvktech.moviebuff.data.model.DetailPresentable
import com.jvktech.moviebuff.data.model.DetailPresentableItemState
import com.jvktech.moviebuff.data.model.Image
import com.jvktech.moviebuff.ui.components.items.DetailPresentableItem
import com.jvktech.moviebuff.ui.components.others.AnimatedBackdrops
import com.jvktech.moviebuff.ui.theme.sizes
import com.jvktech.moviebuff.ui.theme.spacing

@SuppressLint("UnrememberedMutableState")
@Composable
fun PresentableDetailsTopSection(
    presentable: DetailPresentable?,
    modifier: Modifier = Modifier,
    backdrops: List<Image> = emptyList(),
    scrollState: ScrollState? = null,
    scrollValueLimit: Float? = null,
    content: @Composable ColumnScope.() -> Unit = {}
) {
    val presentableItemState by derivedStateOf {
        presentable?.let { DetailPresentableItemState.Result(it) }
            ?: DetailPresentableItemState.Loading
    }

    val availableBackdropPaths by remember(backdrops) {
        mutableStateOf(
            backdrops.map { backdrops -> backdrops.filePath }
        )
    }

    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(color = Color.Transparent)
    }

    Box(
        modifier = modifier.clip(RectangleShape)
    ) {
        AnimatedBackdrops(
            modifier = Modifier.height(200.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,        // Fully transparent
                            Color.Black.copy(alpha = 0.5F),  // 70% transparent black
                            Color.Black               // Fully opaque black
                        ),
                    )
                ),
            paths = availableBackdropPaths
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MaterialTheme.spacing.medium, top = 150.dp,
                        end = MaterialTheme.spacing.medium, bottom = MaterialTheme.spacing.medium)
            ) {
                val (presentableRef, contentRef) = createRefs()

                DetailPresentableItem(
                    modifier = Modifier.constrainAs(presentableRef) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                    size = MaterialTheme.sizes.presentableItemExtraSmall,
                    showScore = false,
                    showTitle = false,
                    showAdult = true,
                    presentableState = presentableItemState
                )

                Column(
                    modifier = Modifier
                        .constrainAs(contentRef) {
                            start.linkTo(presentableRef.end)
                            end.linkTo(parent.end)
                            top.linkTo(presentableRef.top)
                            bottom.linkTo(presentableRef.bottom)

                            height = Dimension.fillToConstraints
                            width = Dimension.fillToConstraints
                        }
                        .padding(start = MaterialTheme.spacing.medium)
                ) {
                    content()
                }
            }

            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
        }
    }
}