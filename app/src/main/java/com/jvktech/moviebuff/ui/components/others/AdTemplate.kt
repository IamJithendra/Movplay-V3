package com.jvktech.moviebuff.ui.components.others

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.ui.theme.MovieBuffTheme
import com.jvktech.moviebuff.ui.theme.Red


@Composable
fun LargeNativeAdCard() {
    Card(
        modifier = Modifier.padding(16.dp),
        shape = MaterialTheme.shapes.medium.copy(
            bottomStart = CornerSize(16.dp),
            bottomEnd = CornerSize(16.dp)
        ),
        colors = CardDefaults.cardColors(Red.copy(alpha = 0.5f)) // Transparent black background
    ) {
        Box(
            modifier = Modifier
                .background(
                    Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 12.dp
                    )
                )
                .padding(8.dp)
        ) {
            Text(
                text = "advertisement",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    color = Color.Yellow.copy(alpha = 0.5f),
                    letterSpacing = 0.15.sp
                )
            )
        }


        Column(
            modifier = Modifier.padding(top = 0.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.collage),
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = "50% off first month of Assistant at €75",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    letterSpacing = 0.15.sp
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "5x Play Points for new subscribers",
                style = typography.bodyMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween, // Align elements to the left, center, and right
                modifier = Modifier.fillMaxWidth()
            ) {
                // App logo
                Image(
                    painter = painterResource(id = R.drawable.ic_telegraam),
                    contentDescription = "app logo"
                )

                Column(
                    modifier = Modifier
                        .padding(start = 16.dp)
                        .weight(1f) // Occupy available space in the center
                ) {
                    // App name
                    Text(
                        text = "Truecaller: Caller ID & Block",
                        style = typography.labelSmall
                    )

                    // App details
                    Row(
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text(
                            text = "4.0 ★",
                            style = typography.labelSmall,
                            modifier = Modifier.padding(start = 4.dp)
                        )

                        Text(
                            text = "Rated for 3+",
                            style = typography.labelSmall,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }

                    Text(
                        text = "In-app purchases",
                        style = typography.labelSmall,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Install button
                Button(
                    onClick = { /* Handle install button click */ },
                    modifier = Modifier.padding(start = 16.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.inverseSurface.copy(alpha = .5f))
                ) {
                    Text(
                        text = "Install",
                        style = typography.labelSmall,
                        color = MaterialTheme.colorScheme.inverseSurface
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewNativeAdScreen() {
    MovieBuffTheme {
        LargeNativeAdCard()
    }
}

