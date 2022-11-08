package com.example.applaudochallenge.ui.navigation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.applaudochallenge.data.models.TvShow
import com.example.applaudochallenge.data.models.tvshowdetails.FiltersType
import com.example.applaudochallenge.data.source.TvShowsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: TvShowsRepository
) : ViewModel() {

    private val _tvShows = MutableStateFlow<PagingData<TvShow>>(PagingData.empty())
    val tvShows = _tvShows.asStateFlow()

    init {
        getFilteredTvShows(FiltersType.TopRated.filterName)
    }

    fun getFilteredTvShows(filter: String) = viewModelScope.launch {
        repository.getFilteredTvShows(filter)
            .cachedIn(viewModelScope)
            .collectLatest { tvShows ->
                _tvShows.update { tvShows }
            }
    }

    fun getLocalListOfTvShows() = viewModelScope.launch {
        repository.getCachedTvShows().collect {
            val tvShows = it ?: emptyList()
            _tvShows.update { PagingData.from(tvShows) }
        }
    }

    fun cacheTvShows(tvShow: TvShow) = viewModelScope.launch {
        var oldestTimestamp = System.currentTimeMillis()

        repository.getCachedTvShows().collect { tvShowList ->
            tvShowList?.let {
                if (tvShowList.isNotEmpty()) {
                    oldestTimestamp = it.last().timestamp
                }
            }

            val needsRefresh =
                oldestTimestamp < System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(20)
                        || tvShowList.isNullOrEmpty()

            if (needsRefresh) {
                repository.clearCachedTvShows()
                repository.insertTvShows(tvShow)
                return@collect
            }
        }
    }
}