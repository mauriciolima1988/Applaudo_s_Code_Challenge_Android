package com.example.applaudochallenge.ui.navigation.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.paging.compose.LazyPagingItems
import com.example.applaudochallenge.R
import com.example.applaudochallenge.data.network.LoadingUiState
import com.example.applaudochallenge.data.network.getNetworkStatus
import com.example.applaudochallenge.data.models.TvShow
import com.example.applaudochallenge.utilities.ScreenState

@Composable
internal fun TvShowsContent(
    loadingUiState: LoadingUiState?,
    onEmptyButtonClick: () -> Unit,
    onCardClick: (Int) -> Unit,
    onRefresh: () -> Unit,
    onCache: (TvShow) -> Unit
) {
    val context = LocalContext.current
    val status = getNetworkStatus()

    ScreenState<LazyPagingItems<TvShow>>(
        loadingUiState = loadingUiState,
        onClick = onEmptyButtonClick
    ) {
        TvShowList(it, onRefresh, onCache) { id ->
            if (status) {
                onCardClick(id)
                return@TvShowList
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_connection_found),
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}