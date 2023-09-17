package com.jvktech.moviebuff.ui.components.others

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DesktopMac
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.NetworkCheck
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonateBottomSheet(
    onDismiss: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(

        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        windowInsets = WindowInsets(0, 0, 0, 0)
    ) {


        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {


                DonateColoredCardView(
                    icon = Icons.Filled.NetworkCheck,
                    title = "Help us to recharge",
                    amount = "250",
                    highlightColor = Color.Cyan
                )

                DonateColoredCardView(
                    icon = Icons.Filled.MonetizationOn,
                    title = "Help us grow",
                    amount = "99",
                    highlightColor = Color.Green
                )
                DonateColoredCardView(
                    icon = Icons.Filled.MonetizationOn,
                    title = "Help us grow",
                    amount = "99",
                    highlightColor = Color.Green
                )

                DonateColoredCardView(
                    icon = Icons.Filled.DesktopMac,
                    title = "Help us Buy a Macbook",
                    amount = "1000",
                    highlightColor = Color.Magenta
                )
            }

        }
    }
}


@Composable
fun DonateColoredCardView(
    icon: ImageVector,
    title: String,
    amount: String,
    highlightColor: Color
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // App Icon
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your app icon resource
            contentDescription = "App Icon",
            modifier = Modifier.size(100.dp)
        )

        // Title
        Text(
            text = "Thank you for using Movie buff!",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(8.dp)
        )

        // Subtitle
        Text(
            text = "You can donate to help us in improving the app, or just to show an appreciation to our work",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.padding(8.dp)
        )


        Card(
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp)
                .shadow(
                    elevation = 16.dp,
                    shape = RoundedCornerShape(16.dp),
                    ambientColor = highlightColor,
                    spotColor = highlightColor
                ),
            elevation = CardDefaults.cardElevation(5.dp),
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(2.dp, highlightColor),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(highlightColor.copy(alpha = 0.1f)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = "Person Icon",
                    tint = highlightColor.copy(alpha = 0.5f)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = title,
                    style = MaterialTheme.typography.bodySmall,
                    color = highlightColor
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = "₹$amount",
                    style = MaterialTheme.typography.labelSmall,
                    color = highlightColor
                )
            }
        }

    }
}