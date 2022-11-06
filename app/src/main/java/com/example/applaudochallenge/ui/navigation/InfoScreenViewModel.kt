package com.example.applaudochallenge.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudochallenge.R
import com.example.applaudochallenge.network.Result
import com.example.applaudochallenge.Constants
import com.example.applaudochallenge.database.FavoriteTvShow
import com.example.applaudochallenge.database.TvShowDetailsRepository
import com.example.applaudochallenge.network.LoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfoScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: TvShowDetailsRepository
) : ViewModel() {

    private val _Loading_uiState = MutableStateFlow<LoadingUiState?>(null)
    val uiState = _Loading_uiState.asStateFlow()

    private val _isInFavorites = MutableStateFlow(false)
    val isInFavorites = _isInFavorites.asStateFlow()

    init {
        savedStateHandle.get<Int>(Constants.INFO_ARGS_ID)?.let { tvShowId ->
            getTvShowInfo(tvShowId)
            tvShowIsInFavorites(tvShowId)
        }
    }

    private fun getTvShowInfo(id: Int) = viewModelScope.launch {
        repository.getTvShowInfo(id).collect { result ->
            when (result) {
                is Result.Error -> _Loading_uiState.update { LoadingUiState.Error(errorStringResource = R.string.network_error) }
                Result.Loading -> _Loading_uiState.update { LoadingUiState.Loading }
                is Result.Success -> _Loading_uiState.update {
                    LoadingUiState.Success(result.data)
                }
            }
        }
    }

    fun tvShowIsInFavorites(id: Int) = viewModelScope.launch {
        repository.getTvShowFavoriteId(id).collect { dbId ->
            when (dbId) {
                id -> _isInFavorites.update { true }
                null -> _isInFavorites.update { false }
            }
        }
    }

    fun insertTvShowInFavorites(tvShow: FavoriteTvShow) = viewModelScope.launch {
        repository.insertTvShowInFavorite(tvShow)
    }

    fun deleteTvShowFromFavorites(id: Int) = viewModelScope.launch {
        repository.deleteTvShowFromFavorite(id)
    }
}