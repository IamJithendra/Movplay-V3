package com.jvktech.moviebuff.ui.components.others

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.*
import com.jvktech.moviebuff.R
import com.jvktech.moviebuff.data.model.FavouriteType
import com.jvktech.moviebuff.ui.theme.spacing


@Composable
fun FavouriteEmptyState(
    type: FavouriteType,
    modifier: Modifier,
    onButtonClick: () -> Unit = {}
) {
    @StringRes
    val infoTextResId = when (type) {
        FavouriteType.Movie -> R.string.favourite_empty_movies_info_text
        FavouriteType.TvShow -> R.string.favourite_empty_tv_series_info_text
    }

    @StringRes
    val buttonLabelResId = when (type) {
        FavouriteType.Movie -> R.string.favourite_empty_navigate_to_movies_button_label
        FavouriteType.TvShow -> R.string.favourite_empty_navigate_to_tv_series_button_label
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_favourite))
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR_FILTER,
            value = PorterDuffColorFilter(
                MaterialTheme.colorScheme.primary.copy(alpha = 0.7f).toArgb(),
                PorterDuff.Mode.SRC_ATOP
            ),
            keyPath = arrayOf(
                "**"
            )
        )
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LottieAnimation(
            modifier = Modifier.size(160.dp),
            composition = composition,
            speed = 0.2f,
            iterations = LottieConstants.IterateForever,
            dynamicProperties = dynamicProperties
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        Text(
            text = stringResource(infoTextResId),
            color = Color.White
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        OutlinedButton(onClick = onButtonClick) {
            Text(modifier = Modifier.animateContentSize(), text = stringResource(buttonLabelResId))
        }
    }
}
