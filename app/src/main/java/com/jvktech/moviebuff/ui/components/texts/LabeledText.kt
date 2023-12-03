package com.jvktech.moviebuff.ui.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.jvktech.moviebuff.ui.theme.spacing

@Composable
fun LabeledText(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
    spacing: Dp = MaterialTheme.spacing.default
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(spacing)
    ) {
        Text(
            text = label,
//            fontSize = 12.sp,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = text,
//            fontSize = 12.sp
        )
    }
}


@Composable
fun DetailTextRow(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
    spacing: Dp = MaterialTheme.spacing.default
) {
    Row(
        modifier = modifier.padding(MaterialTheme.spacing.medium),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.Top
    ) {
        Text(
            text = label,
            // fontSize = 12.sp,
        )

        Spacer(Modifier.width(MaterialTheme.spacing.small))

        Text(
            modifier = modifier.padding(end = MaterialTheme.spacing.medium),
            text = text,
            fontWeight = FontWeight.SemiBold
        )
    }
}




@Preview
@Composable
fun DetailedTextRowPreview() {
    DetailTextRow(label = "original itle", text = "ഡാൻസ് പാർട്ടി")
}