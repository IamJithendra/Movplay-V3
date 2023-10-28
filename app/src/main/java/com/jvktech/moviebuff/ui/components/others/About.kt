package com.jvktech.moviebuff.ui.components.others

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.jvktech.moviebuff.ui.theme.DarkGreen
import com.jvktech.moviebuff.ui.theme.spacing


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBottomSheet(
    onDismiss: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        modifier = Modifier.padding(bottom = 16.dp),
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        windowInsets = WindowInsets(0, 0, 0, 0),
        tonalElevation = 10.dp,
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            modifier = Modifier.fillMaxHeight(0.5f),
            content = { innerPadding ->
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    val (buttonsRow) = createRefs()


                    Box(
                        modifier = Modifier
                            .fillMaxHeight() // Adjust the fraction to control the height of the line
                            .clip(shape = RoundedCornerShape(16.dp)) // Adjust the corner radius as needed
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp),
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "3 Ad-free days left",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                                TextButton(onClick = {
                                    // Add your button click action here
                                }) {
                                    Text(text = "REFILL", color = DarkGreen)
                                }
                            }

                            Spacer(modifier = Modifier.height(10.dp)) // Add vertical space of 5dp

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(15.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                repeat(40) {index ->
                                    Card(
                                        modifier = Modifier
                                            .weight(1f) // Each card takes an equal portion of the available space
                                            .fillMaxHeight(),
                                        colors = CardDefaults.cardColors(if (index < 15) DarkGreen else Color.Gray),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        // Content inside the card, if any
                                    }
                                }
                            }

                            Spacer(modifier = Modifier.height(15.dp)) // Add vertical space of 5dp

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                // Display "0" as the first number
                                Text(
                                    text = "0",
                                    color = Color.White,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )

                                for (i in 1..8) {
                                    Text(
                                        text = i.toString(),
                                        color = Color.White,
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold, // Optional, for better visibility
                                        modifier = Modifier.align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }


                    // Align the buttons row to the bottom center
                    val buttonsAlignment = Modifier.constrainAs(buttonsRow) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }

                    // Buttons for privacy policy and terms of use
                    Row(
                        modifier = buttonsAlignment
                            .fillMaxWidth()
                            .padding(MaterialTheme.spacing.medium), // Adjust bottom padding as needed
                        horizontalArrangement = Arrangement.Center,
                    ) {
                        TextButton(
                            onClick = {
                                // Handle click event for Privacy Policy button here
                            },
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            Text(text = "Privacy policy")
                        }

                        Text(
                            text = "•", modifier = Modifier.padding(vertical = 16.dp)
                        )

                        TextButton(
                            onClick = {
                                // Handle click event for Terms of Use button here
                            },
                            modifier = Modifier.padding(horizontal = 16.dp),
                        ) {
                            Text(text = "Terms of use")
                        }
                    }
                }
            })

    }
}


@Composable
@Preview
fun AboutBottomSheetPreview() {
    AboutBottomSheet(onDismiss = { })
}