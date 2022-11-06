package com.example.applaudochallenge.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.applaudochallenge.R
import com.example.applaudochallenge.network.LoadingUiState

@Composable
fun <T> ScreenState(
    loadingUiState: LoadingUiState?,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    successBlock: @Composable (T) -> Unit
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (loadingUiState) {
            LoadingUiState.Loading -> {
                LoadingScreen(modifier = Modifier.background(MaterialTheme.colors.surface))
            }
            is LoadingUiState.Empty -> {
                MessageScreen(message = R.string.no_results, onClick = onClick)
            }
            is LoadingUiState.Error -> {
                ErrorScreen(
                    errorMessage = loadingUiState.errorMessage,
                    errorStringResource = loadingUiState.errorStringResource
                )
            }
            is LoadingUiState.Success<*> -> {
                @Suppress("UNCHECKED_CAST")
                successBlock(loadingUiState.data as T)
            }
            else -> {}
        }
    }
}
