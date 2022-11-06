package com.example.applaudochallenge.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applaudochallenge.ui.models.TvShow

@Dao
interface TvShowListDao {

    @Query("select * from tvshow")
    suspend fun getCachedTvShows(): List<TvShow>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvShow: TvShow)

    @Query("delete from tvshow")
    suspend fun clearTvShows()
}
