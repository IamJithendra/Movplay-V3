package com.jvktech.moviebuff.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScrenPreied(

) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            androidx.compose.material3.OutlinedTextField(
                enabled = false,
                value = "",
                onValueChange = { /* Handle text change */ },
                label = { androidx.compose.material3.Text(text = "Search Movie, show or Actor") },
                leadingIcon = {
                    androidx.compose.material3.Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}


//@Preview
//@Composable
//fun CustomOutlinedTextFieldPreview() {
//    var text by remember { mutableStateOf(TextFieldValue()) }
//    CustomOutlinedTextField(value = text, label = "Label")
//}