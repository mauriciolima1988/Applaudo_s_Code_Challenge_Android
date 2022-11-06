package com.example.applaudochallenge.ui.navigation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.applaudochallenge.network.LoadingUiState
import com.example.applaudochallenge.ui.models.TvShow

@Composable
internal fun FiltersAndTvShowsColumn(
    onFilterSelected: (String) -> Unit,
    onCardClick: (Int) -> Unit,
    loadingUiState: LoadingUiState?,
    onEmptyButtonClick: () -> Unit,
    onRefresh: () -> Unit,
    onCache: (TvShow) -> Unit
) {
    Column {
        Column {
            FiltersChipGroup(
                onFilterSelected = onFilterSelected
            )
            TvShowsContent(
                loadingUiState = loadingUiState,
                onEmptyButtonClick = onEmptyButtonClick,
                onRefresh = onRefresh,
                onCache = onCache,
                onCardClick = onCardClick
            )
        }
    }
}