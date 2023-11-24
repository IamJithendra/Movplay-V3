package com.jvktech.moviebuff.ui.components.others

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.ImageUrlParser
import com.jvktech.moviebuff.utils.TmdbImage

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun AnimatedBackdrops(
    paths: List<String>,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
    ) {

        val pagerState = rememberPagerState(initialPage = 0)

        HorizontalPager(
            pageCount = paths.size,
            state = pagerState
        ) { page ->
            val currentBackdropPath = paths[page]


            TmdbImage(
                imagePath = currentBackdropPath,
                imageType = ImageUrlParser.ImageType.Backdrop,
            )

        }


        if (paths.isNotEmpty()) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(bottom = MaterialTheme.spacing.medium)
            ) {

                Surface(
                    modifier = Modifier
                        .padding(end = 8.dp, top = 8.dp)
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(12.dp)),
                    color = MaterialTheme.colorScheme.background

                ) {
                    Text(
                        text = "${pagerState.currentPage + 1} / ${paths.size}",
                        modifier = Modifier
                            .padding(
                                start = 8.dp,
                                top = 2.dp,
                                end = 8.dp,
                                bottom = 2.dp
                            ),
                        style = MaterialTheme.typography.labelSmall,
                        maxLines = 1
                    )
                }
            }
        }
    }
}