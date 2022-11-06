package com.example.applaudochallenge.ui.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applaudochallenge.utilities.Exclude

@Entity
data class TvShow(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: List<Int>,
    val media_type: String,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int,
    @Exclude(during = Exclude.During.BOTH)
    val timestamp: Long = System.currentTimeMillis()
)