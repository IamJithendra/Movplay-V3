package com.jvktech.moviebuff.ui.components.dialogs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.R

@Composable
fun ApplicationDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {},
    infoText: String? = null,
    confirmButton: @Composable (() -> Unit)? = null,
    dismissButton: @Composable (() -> Unit)? = null
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        icon = {
            Image(
                modifier = Modifier.height(50.dp),
                painter = painterResource(R.drawable.ic_movplay),
                contentDescription = null,
            )
        },
        text = {
            infoText?.let { text -> Text(text = text) }
        },
        confirmButton = {
            confirmButton?.invoke()
        },
        dismissButton = {
            dismissButton?.invoke()
        }
    )
}