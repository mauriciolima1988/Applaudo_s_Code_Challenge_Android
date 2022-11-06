package com.example.applaudochallenge.network

sealed interface LoadingUiState {
    data class Success<out T>(val data: T) : LoadingUiState
    data class Error(
        val errorMessage: String? = null, val errorStringResource: Int? = null
    ) : LoadingUiState

    object Loading : LoadingUiState
    object Empty : LoadingUiState
}