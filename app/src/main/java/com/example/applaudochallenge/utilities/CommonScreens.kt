package com.example.applaudochallenge.ui

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.applaudochallenge.data.network.LoadingUiState
import com.example.applaudochallenge.ui.widgets.ErrorScreen
import com.example.applaudochallenge.ui.widgets.LoadingScreen

@Composable
internal fun <T> PageWithState(
    loadingUiState: LoadingUiState?,
    modifier: Modifier = Modifier,
    successBlock: @Composable (T) -> Unit,
) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        when (loadingUiState) {
            LoadingUiState.Loading -> {
                LoadingScreen(modifier = Modifier.background(MaterialTheme.colors.surface))
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
