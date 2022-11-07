package com.example.applaudochallenge.data.network

import com.example.applaudochallenge.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import com.example.applaudochallenge.data.models.ErrorResponse
import com.example.applaudochallenge.data.models.TvShowDetails
import com.example.applaudochallenge.data.models.TvShowsList
import com.example.applaudochallenge.data.models.tvshowdetails.season.episode.Episode
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

    @GET("tv/{id}/season/{season_number}")
    suspend fun getSeasonEpisodesByIds(
        @Path("id") id: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): NetworkResponse<List<Episode>, ErrorResponse>

}
