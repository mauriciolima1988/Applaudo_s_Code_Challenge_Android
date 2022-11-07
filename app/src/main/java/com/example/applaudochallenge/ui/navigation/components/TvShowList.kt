package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.paging.compose.LazyPagingItems
import com.example.applaudochallenge.data.models.TvShow
import com.example.applaudochallenge.utilities.ScreenSize
import com.example.applaudochallenge.utilities.rememberWindowSize
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.delay

@Composable
internal fun TvShowList(
    tvShows: LazyPagingItems<TvShow>,
    onRefresh: () -> Unit,
    onCache: (TvShow) -> Unit,
    onCardClick: (Int) -> Unit
) {

    var refreshing by remember { mutableStateOf(false) }
    val windowSize = rememberWindowSize()
    val gridAmount = when (windowSize.horizontalSize) {
        ScreenSize.HorizontalSize.Normal -> 2
        ScreenSize.HorizontalSize.Large -> 4
        ScreenSize.HorizontalSize.ExtraLarge -> 6
    }

    LaunchedEffect(refreshing) {
        if (refreshing) {
            delay(2000)
            refreshing = false
        }
    }

    SwipeRefresh(
        state = rememberSwipeRefreshState(refreshing),
        onRefresh = {
            onRefresh()
            refreshing = true
        },
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(gridAmount),
            verticalArrangement = Arrangement.Center,
            horizontalArrangement = Arrangement.Center
        ) {
            items(tvShows.itemCount) { i ->
                onCache(tvShows[i]!!)
                TvShowItemsList(tvShow = tvShows[i]!!, onCardClick = onCardClick)
            }
        }
    }
}