package com.example.applaudochallenge.network

import com.example.applaudochallenge.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.applaudochallenge.ui.models.ErrorResponse
import com.example.applaudochallenge.ui.models.TvShowsList

interface TvShowsApi {

    @GET("tv/{filterBy}")
    suspend fun getTvShows(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): NetworkResponse<TvShowsList, ErrorResponse>

}
