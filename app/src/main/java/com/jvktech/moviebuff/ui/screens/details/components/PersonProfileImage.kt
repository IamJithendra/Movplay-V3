package com.jvktech.moviebuff.ui.screens.details.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.size.Scale
import com.jvktech.moviebuff.ui.components.items.ErrorPresentableItem
import com.jvktech.moviebuff.ui.components.items.LoadingPresentableItem
import com.jvktech.moviebuff.ui.components.items.NoPhotoPresentableItem
import com.jvktech.moviebuff.ui.screens.details.person.PersonDetailsState
import com.jvktech.moviebuff.ui.theme.Size
import com.jvktech.moviebuff.ui.theme.sizes
import com.jvktech.moviebuff.utils.ImageUrlParser
import com.jvktech.moviebuff.utils.TmdbImage

@Composable
fun PersonProfileImage(
    personDetailsState: PersonDetailsState,
    modifier: Modifier = Modifier,
    size: Size = MaterialTheme.sizes.presentableItemBig
) {
    Card(
        modifier = modifier
            .width(size.width)
            .height(size.height),
        shape = MaterialTheme.shapes.medium
    ) {
        when (personDetailsState) {
            is PersonDetailsState.Loading -> {
                LoadingPresentableItem(
                    modifier = Modifier.fillMaxSize()
                )
            }
            is PersonDetailsState.Error -> {
                ErrorPresentableItem(
                    modifier = Modifier.fillMaxSize()
                )
            }

            is PersonDetailsState.Result -> {
                val profilePath = personDetailsState.details.profilePath

                if (profilePath != null) {
                    TmdbImage(
                        modifier = Modifier.fillMaxSize(),
                        imagePath = profilePath,
                        imageType = ImageUrlParser.ImageType.Profile
                    ) {
                        size(coil.size.Size.ORIGINAL)
                        scale(Scale.FILL)
                        crossfade(true)
                    }
                } else {
                    NoPhotoPresentableItem(
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}