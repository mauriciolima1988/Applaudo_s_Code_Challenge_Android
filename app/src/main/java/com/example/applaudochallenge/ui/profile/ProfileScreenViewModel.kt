package com.example.applaudochallenge.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applaudochallenge.database.FavoriteTvShow
import com.example.applaudochallenge.database.TvShowDetailsLocalDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileScreenViewModel @Inject constructor(
    private val localDataSource: TvShowDetailsLocalDataSource
) : ViewModel() {

    private val _favoritesList = MutableStateFlow<List<FavoriteTvShow>?>(null)
    val favoritesList = _favoritesList.asStateFlow()

    init {
        getFavoritesTvShows()
    }

    private fun getFavoritesTvShows() = viewModelScope.launch {
        localDataSource.getFavoritesTvShows().collect { list ->
            when {
                list.isEmpty() -> {
                    _favoritesList.update { emptyList() }
                }
                else -> {
                    _favoritesList.update { list }
                }
            }
        }
    }
}