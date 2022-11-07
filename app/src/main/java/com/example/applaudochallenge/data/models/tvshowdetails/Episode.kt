package com.example.applaudochallenge.data.models.tvshowdetails

import androidx.room.Entity

@Entity(primaryKeys = ["id", "episode_number"])
data class Episode(
    val episode_number: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String?,
)