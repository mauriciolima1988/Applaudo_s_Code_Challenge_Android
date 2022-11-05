package com.example.applaudochallenge.network

import com.example.applaudochallenge.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.applaudochallenge.ui.model.ErrorResponse
import com.example.applaudochallenge.ui.model.MoviesList

interface MoviesApi {

    @GET("tv/{filterBy}")
    suspend fun getMovies(
        @Query("page") page: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): NetworkResponse<MoviesList, ErrorResponse>

}
