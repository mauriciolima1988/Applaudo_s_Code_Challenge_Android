package com.example.applaudochallenge.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applaudochallenge.utilities.Exclude

@Entity
data class TvShow(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val origin_country: List<String>,
    val adult: Boolean,
    val first_air_date: String,
    val genre_ids: List<Int>,
    val original_language: String,
    val original_name: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    @Exclude(during = Exclude.During.BOTH)
    val timestamp: Long = System.currentTimeMillis()
)