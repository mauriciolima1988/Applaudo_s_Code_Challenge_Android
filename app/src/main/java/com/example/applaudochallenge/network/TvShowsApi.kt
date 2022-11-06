package com.example.applaudochallenge.network

import com.example.applaudochallenge.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.applaudochallenge.ui.models.ErrorResponse
import com.example.applaudochallenge.ui.models.TvShowDetails
import com.example.applaudochallenge.ui.models.TvShowsList
import retrofit2.http.Path

interface TvShowsApi {

    @GET("tv/{filterBy}")
    suspend fun getTvShows(
        @Path("filterBy") filterBy: String,
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
    ): NetworkResponse<TvShowsList, ErrorResponse>

    @GET("tv/{id}")
    suspend fun getTVShowById(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): NetworkResponse<TvShowDetails, ErrorResponse>

}
