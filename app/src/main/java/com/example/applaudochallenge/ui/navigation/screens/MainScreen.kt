package com.example.applaudochallenge.ui.navigation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import com.example.applaudochallenge.data.network.LoadingUiState
import com.example.applaudochallenge.data.network.getNetworkStatus
import com.example.applaudochallenge.ui.widgets.TopBar
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.applaudochallenge.data.models.TvShow
import com.example.applaudochallenge.data.models.tvshowdetails.FiltersType
import com.example.applaudochallenge.data.models.tvshowdetails.getTvShowFilters
import com.example.applaudochallenge.ui.navigation.viewmodels.MainScreenViewModel
import com.example.applaudochallenge.ui.navigation.components.FiltersAndTvShowsColumn

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    onCardClick: (Int) -> Unit,
) {
    val tvShows = viewModel.tvShows.collectAsLazyPagingItems()
    val netWorkStatus = getNetworkStatus()

    val loadingUiState = when {
        tvShows.loadState.source.refresh == LoadState.Loading -> {
            LoadingUiState.Loading
        }
        tvShows.itemCount == 0 -> {
            if (!netWorkStatus) {
                viewModel.getLocalListOfTvShows()
                LoadingUiState.Success(tvShows)
            }
            LoadingUiState.Empty
        }
        else -> {
            LoadingUiState.Success(tvShows)
        }
    }

    MainView(
        loadingUiState = loadingUiState,
        onEmptyButtonClick = { viewModel.getFilteredTvShows(FiltersType.Popular.filterName) },
        onSearchClick = onSearchClick,
        onProfileClick = onProfileClick,
        onCardClick = onCardClick,
        onFilterSelected = { viewModel.getFilteredTvShows(it) },
        onRefresh = { viewModel.getFilteredTvShows(getTvShowFilters()[0].filterName) },
        onCache = { viewModel.cacheTvShows(it) },
    )
}

@Composable
internal fun MainView(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState?,
    onSearchClick: () -> Unit,
    onProfileClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onFilterSelected: (String) -> Unit,
    onEmptyButtonClick: () -> Unit,
    onRefresh: () -> Unit,
    onCache: (TvShow) -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopBar(onSearchClick, onProfileClick) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            FiltersAndTvShowsColumn(
                onFilterSelected,
                onCardClick,
                loadingUiState,
                onEmptyButtonClick,
                onRefresh,
                onCache
            )
        }
    }
}









