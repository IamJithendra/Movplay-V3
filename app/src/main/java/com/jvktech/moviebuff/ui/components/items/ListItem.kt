package com.jvktech.moviebuff.ui.components.items

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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
    // TODO remove the square edge
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .padding(horizontal = MaterialTheme.spacing.small, vertical = MaterialTheme.spacing.extraSmall)
            .clickable { onClick?.invoke() },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center
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

                        ResultPresentableItem(
                            modifier = Modifier.fillMaxSize(),
                            presentable = presentableState.presentable,
                            showTitle = showTitle,
                            onClick = onClick
                        )
                    }

                    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))

                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = presentableState.presentable.voteAverage.toString(),
                            style = MaterialTheme.typography.labelMedium,
                            modifier = Modifier.clickable { /* Handle click */ }
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = presentableState.presentable.title,
                            style = MaterialTheme.typography.titleMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Text(
                            text = presentableState.presentable.id.toString(),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
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