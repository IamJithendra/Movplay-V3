package com.jvktech.moviebuff.ui.components.others

import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutBottomSheet(
    onDismiss: () -> Unit
) {
    val bottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = bottomSheetState,
        windowInsets = WindowInsets(0, 0, 0, 0),
        tonalElevation = 10.dp,
        containerColor = MaterialTheme.colorScheme.background
    ) {

        Scaffold(
            content = { innerPadding ->
                ConstraintLayout(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                ) {
                    val (buttonsRow) = createRefs()


                    // Rest of your layout remains the same.
                    // ...

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
                            .padding(bottom = 16.dp), // Adjust bottom padding as needed
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
                            text = "•",
                            modifier = Modifier
                                .padding(vertical = 16.dp)
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
            }
        )

    }
}


@Composable
@Preview
fun AboutBottomSheetPreview() {
    AboutBottomSheet(onDismiss = { })
}