package com.jvktech.moviebuff.ui.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jvktech.moviebuff.data.model.PresentableItemState
import com.jvktech.moviebuff.ui.theme.Size
import com.jvktech.moviebuff.ui.theme.sizes
import com.jvktech.moviebuff.ui.theme.spacing


@Composable
fun ListItem(
    presentableState: PresentableItemState,
    modifier: Modifier = Modifier,
    size: Size = MaterialTheme.sizes.presentableItemSmall,
    selected: Boolean = false,
    showTitle: Boolean = false,
    transformations: GraphicsLayerScope.() -> Unit = {},
    onClick: (() -> Unit)? = null
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = MaterialTheme.spacing.small, vertical = MaterialTheme.spacing.extraSmall)
    ) {
        val (imagePoster, textDate, textTitle, textGenres, iconWatchlist, box, divider) = createRefs()

        Card(
            modifier = modifier
                .width(size.width)
                .aspectRatio(size.ratio)
                .graphicsLayer {
                    transformations()
                },
            shape = MaterialTheme.shapes.medium,
            border = if (selected) BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.primary
            ) else null
        ) {
            when (presentableState) {
                is PresentableItemState.Loading -> {
                    LoadingPresentableItem(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is PresentableItemState.Error -> {
                    ErrorPresentableItem(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is PresentableItemState.Result -> {
                    ResultPresentableItem(
                        modifier = Modifier.fillMaxSize(),
                        presentable = presentableState.presentable,
                        showTitle = showTitle,
                        onClick = onClick
                    )

                    Text(
                        text = presentableState.presentable.voteAverage.toString(),
                        style = MaterialTheme.typography.bodySmall
                    )

                    Spacer(modifier = Modifier.height(6.dp)) // Vertical spacing of 3dp

                    Text(
                        text = presentableState.presentable.title,
                        style = MaterialTheme.typography.bodyMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(6.dp)) // Vertical spacing of 3dp

                    Text(
                        text = presentableState.presentable.id.toString(),
                        style = MaterialTheme.typography.bodySmall,
                    )

                }
            }
        }
    }
}


//@Composable
//@Preview
//fun PreviewMovieListItem() {
//    ListItem()
//}