package com.example.applaudochallenge.data.models.tvshowdetails.season

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.applaudochallenge.data.models.tvshowdetails.season.episode.Episode

@Entity
data class Season(
    @PrimaryKey(autoGenerate = false)
    val _id: String,
    val air_date: String,
    val episodes: List<Episode>,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
    val season_number: Int
)

