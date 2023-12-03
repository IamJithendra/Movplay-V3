package com.jvktech.moviebuff.ui.components.texts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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


//@Composable
//fun DetailTextRow(
//    label: String,
//    text: String,
//    modifier: Modifier = Modifier,
//    spacing: Dp = MaterialTheme.spacing.default
//) {
//    Row(
//        modifier = modifier.padding(MaterialTheme.spacing.medium),
//        verticalAlignment = Alignment.Top
//    ) {
//        Text(
//            text = label,
//            textAlign = TextAlign.Start
//        )
//
////        Spacer(Modifier.width(spacing)) // Adjust the spacing here
//
//        Text(
//            text = text,
//            fontWeight = FontWeight.SemiBold,
//            textAlign = TextAlign.End
//        )
//    }
//
//    Box(
//        Modifier.fillMaxWidth()
//    ) {
//        Text(text = label, modifier = Modifier.align(Alignment.CenterStart))
//        Text(text = text, modifier = Modifier.align(Alignment.CenterEnd))
//    }
//
//}


@Composable
fun DetailTextRow(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
    spacing: Dp = MaterialTheme.spacing.default
) {


    Row(
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {

        Box(
            Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        ) {
            Text(
                text = label, modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterStart)
            )
        }

        Box(
            Modifier.padding(horizontal = MaterialTheme.spacing.medium)
        ) {

            Text(
                text = text, modifier = Modifier
                    .padding(end = 8.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }

}


@Preview
@Composable
fun DetailedTextRowPreview() {
    DetailTextRow(label = "original itle", text = "ഡാൻസ് പാർട്ടി", spacing = 8.dp)
}