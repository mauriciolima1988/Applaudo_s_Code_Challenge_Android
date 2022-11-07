package com.example.applaudochallenge.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.applaudochallenge.data.database.dao.FavsTvShowsDao
import com.example.applaudochallenge.data.database.dao.TvShowListDao
import com.example.applaudochallenge.data.database.utils.Converters
import com.example.applaudochallenge.data.models.TvShow

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