package com.jvktech.moviebuff.ui.screens

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PrimaryScrollableTabRow
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


data class TabRowItem<T>(
    val value: T,
    @StringRes val title: Int?,
    @DrawableRes val icon: Int? = null,
)

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T> TabRowWithPager(
    tabs: Array<TabRowItem<T>>,
    modifier: Modifier = Modifier,
    initialPage: Int = 0,
    beyondBoundsPageCount: Int = 0,
    isTabScrollable: Boolean = false,
    isPrimaryTab: Boolean = true,
    pageContent: @Composable (Int) -> Unit,
) {

    val pagerState = rememberPagerState(
        initialPage = initialPage,
        initialPageOffsetFraction = 0f
    ) {
        tabs.size
    }

    val scope = rememberCoroutineScope()

    Column(modifier = modifier) {
        val tabsLayout = @Composable {
            tabs.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.animateScrollToPage(index) } },
                    text = if (item.title != null) {
                        {
                            Text(text = stringResource(item.title))
                        }
                    } else null,
                    icon = if (item.icon != null) {
                        {
                            Icon(
                                painter = painterResource(item.icon),
                                contentDescription = item.value.toString()
                            )
                        }
                    } else null,
                    unselectedContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        }

        if (isTabScrollable) {
            if (isPrimaryTab) {
                PrimaryScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    edgePadding = 16.dp,
                    divider = {},
                    tabs = tabsLayout
                )
            } else {
                SecondaryScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    edgePadding = 16.dp,
                    divider = {},
                    tabs = tabsLayout
                )
            }
            HorizontalDivider(modifier = Modifier.fillMaxWidth())
        } else {
            if (isPrimaryTab) {
                PrimaryTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    tabs = tabsLayout
                )
            } else {
                SecondaryTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    tabs = tabsLayout
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            beyondBoundsPageCount = if (beyondBoundsPageCount < 0) 0 else beyondBoundsPageCount,
            key = { tabs[it].value!! }
        ) { page ->
            if (
                page !in ((pagerState.currentPage - (beyondBoundsPageCount + 1))
                        ..(pagerState.currentPage + (beyondBoundsPageCount + 1)))
            ) {
                // To make sure only X offscreen pages are being composed
                return@HorizontalPager
            }
            pageContent(page)
        }
    }//: Column
}