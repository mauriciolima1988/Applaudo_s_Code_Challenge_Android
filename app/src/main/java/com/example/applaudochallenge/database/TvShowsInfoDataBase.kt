package com.example.applaudochallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.applaudochallenge.database.dao.FavsTvShowsDao
import com.example.applaudochallenge.database.dao.TvShowListDao
import com.example.applaudochallenge.database.utils.Converters
import com.example.applaudochallenge.ui.models.TvShow

@Database(
    entities = [TvShow::class, FavoriteTvShow::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TvShowsInfoDataBase : RoomDatabase() {
    abstract fun favsTvShowsDao(): FavsTvShowsDao
    abstract fun tvShowListDao(): TvShowListDao
}