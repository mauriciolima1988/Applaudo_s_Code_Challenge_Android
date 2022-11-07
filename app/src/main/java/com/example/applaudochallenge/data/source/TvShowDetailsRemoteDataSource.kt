package com.example.applaudochallenge.data.source

import com.example.applaudochallenge.data.network.Result
import com.example.applaudochallenge.database.mapResponse
import com.example.applaudochallenge.data.network.TvShowsApi
import com.example.applaudochallenge.data.models.ErrorResponse
import com.example.applaudochallenge.data.models.TvShowDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TvShowDetailsRemoteDataSource {
    fun getTvShowDetails(id: Int): Flow<Result<TvShowDetails, ErrorResponse>>
}

class TvShowDetailsRemoteDataSourceImp @Inject constructor(
    private val apiService: TvShowsApi
) : TvShowDetailsRemoteDataSource {

    override fun getTvShowDetails(id: Int): Flow<Result<TvShowDetails, ErrorResponse>> =
        mapResponse(Dispatchers.IO) {
            apiService.getTVShowById(id)
        }
}