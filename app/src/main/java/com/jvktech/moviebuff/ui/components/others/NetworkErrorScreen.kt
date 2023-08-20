package com.jvktech.moviebuff.ui.components.others

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.ui.theme.spacing

@Composable
fun NetworkErrorScreen(onClick: () -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape),
                colors = CardDefaults.cardColors(Color.LightGray),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp), // Added padding for better alignment
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_network_error),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(75.dp) // Reduced the size to 50%
                            .align(Alignment.Center)
                    )
                }
            }

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) // Gap after Card

            Text(
                text = "You are offline",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.small)) // Gap below the text

            Text(
                text = "Check your internet connection and try again"
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium)) // Gap after Card

            Button(
                onClick = onClick
            ) {
                Text(text = "Try Again")
            }
        }
    }
}


@Preview
@Composable
fun NetworkErrorScreenPreview() {
    NetworkErrorScreen(onClick = {})
}