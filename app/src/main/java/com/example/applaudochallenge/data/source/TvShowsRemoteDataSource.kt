package com.example.applaudochallenge.data.source

import com.example.applaudochallenge.data.network.Result
import com.example.applaudochallenge.database.mapResponse
import com.example.applaudochallenge.data.network.TvShowsApi
import com.example.applaudochallenge.data.models.ErrorResponse
import com.example.applaudochallenge.data.models.TvShowsList
import javax.inject.Inject

interface TvShowsRemoteDataSource {
    suspend fun getFilteredTvShows(filter: String, page: Int): Result<TvShowsList, ErrorResponse>
}

class TvShowsRemoteDataSourceImp @Inject constructor(
    private val apiService: TvShowsApi
) : TvShowsRemoteDataSource {

    override suspend fun getFilteredTvShows(filter: String, page: Int) = mapResponse {
        apiService.getTvShows(filterBy = filter, page = page)
    }
}