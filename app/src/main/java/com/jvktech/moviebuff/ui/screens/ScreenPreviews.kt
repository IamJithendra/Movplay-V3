package com.jvktech.moviebuff.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jvktech.moviebuff.R


@Composable
fun PosterLayout() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click here */ }
            .background(MaterialTheme.colorScheme.secondary)
            .padding(start = 4.dp, end = 4.dp)
    ) {
        val (imagePoster, textHeader, textTitle, textSubtitle, iconAddWatchlist, iconAddWatched) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.ic_demo_poster),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(imagePoster) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    bottom.linkTo(parent.bottom)
                }
                .fillMaxHeight()
                .width(0.dp)
                .aspectRatio(3f / 2)
        )

        Text(
            text = "Header Text",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.constrainAs(textHeader) {
                top.linkTo(imagePoster.top)
                start.linkTo(imagePoster.end)
            }
        )

        Text(
            text = "Title Text",
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(textTitle) {
                top.linkTo(textHeader.bottom)
                start.linkTo(textHeader.start)
                end.linkTo(parent.end)
            }
        )

        Text(
            text = "Subtitle Text",
            style = MaterialTheme.typography.displayMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(textSubtitle) {
                top.linkTo(textTitle.bottom)
                start.linkTo(textTitle.start)
                end.linkTo(parent.end)
            }
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_watchlist),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(iconAddWatchlist) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
                .padding(7.dp)
                .size(42.dp)
                .clip(MaterialTheme.shapes.medium)
                .selectable(
                    selected = false,
                    onClick = { /* Handle click here */ }
                )
                .background(MaterialTheme.colorScheme.primary)
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_watched),
            contentDescription = null,
            modifier = Modifier
                .constrainAs(iconAddWatched) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
                .padding(8.dp)
                .size(42.dp)
                .clip(MaterialTheme.shapes.medium)
                .selectable(
                    selected = false,
                    onClick = { /* Handle click here */ }
                )
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}

@Composable
@Preview
fun PreviewPosterLayout() {
    Surface {
        PosterLayout()
    }
}
