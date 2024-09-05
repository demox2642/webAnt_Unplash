@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.theme.AppTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun <T> TabPager(
    modifier: Modifier = Modifier,
    tabs: List<Pair<String, T>>,
    loading: Boolean = false,
    index: Int? = null,
    pagerState: PagerState = rememberPagerState(),
    pageFactory: @Composable (T) -> Unit,
) {
    val scope = rememberCoroutineScope()

    index?.let {
        scope.launch {
            if (pagerState.targetPage != index) {
                pagerState.scrollToPage(it)
            }
        }
    }

    Column {
        TabHeader(Modifier, pagerState, tabs.map { it.first }, loading)
        TabContent(Modifier.weight(1f, true), pagerState, tabs.map { it.second }, pageFactory)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun <T> TabContent(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    tabs: List<T>,
    pageFactory: @Composable (T) -> Unit,
) {
    HorizontalPager(
        tabs.size,
        modifier.padding(top = 16.dp),
        pagerState,
        verticalAlignment = Alignment.Top,
    ) { page ->
        pageFactory(tabs[page])
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabHeader(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    tabTitles: List<String>,
    loading: Boolean = false,
) {
    val coroutineScope = rememberCoroutineScope()

    TabRow(
        selectedTabIndex = pagerState.currentPage,
        modifier.padding(horizontal = 16.dp),
        backgroundColor = MaterialTheme.colors.background,
        divider = {
            TabRowDefaults.Divider(
                thickness = 1.dp,
                color = AppTheme.colors.grayLight,
            )
        },
        indicator = { tabPositions ->
            if (loading) {
                TabRowDefaults.Divider(
                    thickness = 1.dp,
                    color = AppTheme.colors.grayLight,
                )
            } else {
                TabRowDefaults.Indicator(
                    modifier = Modifier.pagerTabIndicatorOffset(pagerState = pagerState, tabPositions = tabPositions),
                    height = 2.dp,
                    color = AppTheme.colors.main,
                )
            }
        },
    ) {
        tabTitles.forEachIndexed { index, title ->
            Tab(
                selected = pagerState.currentPage == index,
                onClick = {
                    if (loading.not()) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                },
                enabled = true,
            ) {
                Box(Modifier.height(42.dp), Alignment.Center) {
                    Text(
                        text = title,
                        style = AppTheme.typography.h3,
                        color = if (pagerState.currentPage == index && loading.not()) AppTheme.colors.black else AppTheme.colors.gray,
                    )
                }
            }
        }
    }
}
