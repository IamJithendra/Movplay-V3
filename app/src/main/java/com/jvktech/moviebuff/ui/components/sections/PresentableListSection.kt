package com.jvktech.moviebuff.ui.components.sections

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntOffset
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.jvktech.moviebuff.data.model.Presentable
import com.jvktech.moviebuff.data.model.PresentableItemState
import com.jvktech.moviebuff.ui.components.button.ScrollToTopButton
import com.jvktech.moviebuff.ui.components.items.GridItem
import com.jvktech.moviebuff.ui.components.items.ListItem
import com.jvktech.moviebuff.ui.components.others.listVerticalScrollBar
import com.jvktech.moviebuff.ui.theme.spacing
import com.jvktech.moviebuff.utils.isScrollingTowardsStart
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun PresentableListSection(
    state: LazyPagingItems<out Presentable>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    showRefreshLoading: Boolean = true,
    contentPadding: PaddingValues = PaddingValues(MaterialTheme.spacing.default),
    scrollToBeginningItemsStart: Int = 30,
    onPresentableClick: (Int) -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val isScrollingLeft = listState.isScrollingTowardsStart()
    val showScrollToBeginningButton by derivedStateOf {
        val visibleMaxItem = listState.firstVisibleItemIndex > scrollToBeginningItemsStart

        visibleMaxItem && isScrollingLeft
    }
    val onScrollToStartClick: () -> Unit = {
        coroutineScope.launch {
            listState.animateScrollToItem(0)
        }
    }

    Box(modifier = Modifier) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .listVerticalScrollBar(listState),
            state = listState,
            contentPadding = contentPadding,
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.small)
        ) {
            items(state) { presentable ->
                presentable?.let {
                    ListItem(
                        presentableState = PresentableItemState.Result(it),
                        onClick = { onPresentableClick(it.id) }
                    )
                }
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = MaterialTheme.spacing.small, top = MaterialTheme.spacing.small),
            visible = showScrollToBeginningButton,
            enter = slideIn(
                animationSpec = tween(),
                initialOffset = { size -> IntOffset(size.width, 0) }),
            exit = fadeOut(animationSpec = spring()) + scaleOut(
                animationSpec = spring(),
                targetScale = 0.3f
            )
        ) {
            ScrollToTopButton(
                onClick = onScrollToStartClick
            )
        }
    }
}