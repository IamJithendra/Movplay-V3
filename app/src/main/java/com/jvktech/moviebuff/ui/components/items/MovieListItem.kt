package com.jvktech.moviebuff.ui.components.items

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.jvktech.moviebuff.R


@Composable
fun MovieListItem(
//    imagePoster: ImageBitmap,
//    date: String,
//    movieName: String,
//    genres: String
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        val (imagePoster, textDate, textTitle, textGenres, iconMore, iconWatchlist, iconWatched, box, divider) = createRefs()

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.drawable.ic_demo_poster)
                .crossfade(true)
                .build(),
            contentDescription = "Poster item",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(imagePoster) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    centerVerticallyTo(parent)
                }
                .width(120.dp) // Adjust the width as needed
                .height(175.dp) // Reduced height by 5dp
                .clip(RoundedCornerShape(8.dp))
        )

        Text(
            text = "18 August 2023",
            style = MaterialTheme.typography.bodySmall,
//            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.constrainAs(textDate) {
                start.linkTo(imagePoster.end, 16.dp)
                top.linkTo(imagePoster.top)
            }
        )

        Spacer(modifier = Modifier.height(3.dp)) // Vertical spacing of 3dp

        Text(
            text = "Foundation",
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.constrainAs(textTitle) {
                start.linkTo(textDate.start)
                top.linkTo(textDate.bottom)
            }
        )

        Spacer(modifier = Modifier.height(3.dp)) // Vertical spacing of 3dp

        Text(
            text = "Sci-Fi, Fantasy & Drama",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.constrainAs(textGenres) {
                start.linkTo(textTitle.start)
                top.linkTo(textTitle.bottom)
            }
        )

        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = null,
            modifier = Modifier
                .constrainAs(iconMore) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                }
                .padding(8.dp)
                .size(24.dp)
                .clickable { /* Handle click here */ }
        )

        Row(
            modifier = Modifier.constrainAs(iconWatchlist) {
                bottom.linkTo(parent.bottom)
                end.linkTo(iconWatched.start, 16.dp)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_watchlist),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            )
        }

        Row(
            modifier = Modifier.constrainAs(iconWatched) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end, 16.dp)
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_watched),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .padding(4.dp)
            )
        }

        // Add empty Box for spacing
        Box(
            modifier = Modifier.constrainAs(box) {
                bottom.linkTo(iconWatchlist.bottom)
            }
        )

        // Add Divider starting after the image
        Divider(
            color = MaterialTheme.colorScheme.onBackground,
            thickness = 1.dp,
            modifier = Modifier
                .padding(start = 125.dp)
                .constrainAs(divider) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(imagePoster.start)
                    end.linkTo(parent.end)
                }
        )
    }
}


@Composable
@Preview
fun PreviewMovieListItem() {
    MovieListItem()
}