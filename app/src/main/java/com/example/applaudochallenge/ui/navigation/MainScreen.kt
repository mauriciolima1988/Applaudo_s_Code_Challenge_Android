package com.example.applaudochallenge.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import com.example.applaudochallenge.network.LoadingUiState
import com.example.applaudochallenge.network.getNetworkStatus
import com.example.applaudochallenge.ui.TopBar
import com.example.applaudochallenge.ui.models.TvShowInfos.FiltersType
import androidx.paging.compose.collectAsLazyPagingItems

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel(),
    onFavoriteClick: () -> Unit,
    onCardClick: (Int) -> Unit
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

    MainScreen(
        loadingUiState = loadingUiState,
        onEmptyButtonClick = { viewModel.getFilteredTvShows(FiltersType.Popular.filterName) },
        onFavoriteClick = onFavoriteClick,
        onCardClick = onCardClick,
        onFilterSelected = { viewModel.getFilteredTvShows(it) }
    )
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    loadingUiState: LoadingUiState?,
    onFavoriteClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onFilterSelected: (String) -> Unit,
    onEmptyButtonClick: () -> Unit,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { TopBar(onFavoriteClick) }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            FiltersAndTvShowsColumn(
                onFilterSelected,
                onCardClick,
                loadingUiState,
                onEmptyButtonClick
            )
        }
    }
}

@Composable
private fun FiltersAndTvShowsColumn(
    onFilterSelected: (String) -> Unit,
    onCardClick: (Int) -> Unit,
    loadingUiState: LoadingUiState?,
    onEmptyButtonClick: () -> Unit,
) {

    Column {
        // TODO insert filters pills bar and tv shows list
    }
}