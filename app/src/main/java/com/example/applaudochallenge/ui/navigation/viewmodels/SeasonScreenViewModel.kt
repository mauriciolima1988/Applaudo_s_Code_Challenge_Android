package com.example.applaudochallenge.ui.navigation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudochallenge.ui.Constants
import com.example.applaudochallenge.data.network.LoadingUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeasonScreenViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    // private val repository: SeasonRepository
) : ViewModel() {

    private val _seasonLoadingUiState = MutableStateFlow<LoadingUiState?>(null)
    val uiStateSeason = _seasonLoadingUiState.asStateFlow()

    init {
        val tvShowId = savedStateHandle.get<Int>(Constants.TVSHOW_ARGS_ID)!!
        savedStateHandle.get<Int>(Constants.SEASON_ARGS_ID)?.let { seasonId ->
            getEpisodes(tvShowId, seasonId)
        }
    }

    private fun getEpisodes(id: Int, seasonNumber: Int) = viewModelScope.launch {
        // TO DO
        // Create SeasonRepository
        // Get episodes from repository
    }
}