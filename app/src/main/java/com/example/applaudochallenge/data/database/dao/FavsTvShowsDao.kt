package com.example.applaudochallenge.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.applaudochallenge.data.database.FavoriteTvShow

@Dao
interface FavsTvShowsDao {

    @Query("select * from favoritetvshow")
    suspend fun getFavsTvShows(): List<FavoriteTvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavTvShow(tvShow: FavoriteTvShow)

    @Query("select id from favoritetvshow where id = :id")
    suspend fun getFavId(id: Int): Int?

    @Query("delete from favoritetvshow where id = :id")
    suspend fun deleteFavTvShow(id: Int)
}