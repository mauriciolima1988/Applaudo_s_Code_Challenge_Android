package com.example.applaudochallenge.database

import com.example.applaudochallenge.database.dao.FavsTvShowsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface TvShowDetailsLocalDataSource {
    fun getFavoritesTvShows(): Flow<List<FavoriteTvShow>>
    suspend fun insertFavoritesTvShow(tvShow: FavoriteTvShow)
    fun getFavoritesId(id: Int): Flow<Int?>
    suspend fun deleteFavoritesTvShow(id: Int)
}

class TvShowDetailsLocalDataSourceImp @Inject constructor(
    private val favsTvShowsDao: FavsTvShowsDao
) : TvShowDetailsLocalDataSource {

    override fun getFavoritesTvShows(): Flow<List<FavoriteTvShow>> = flow {
        emit(favsTvShowsDao.getFavsTvShows())
    }.flowOn(Dispatchers.IO)

    override suspend fun insertFavoritesTvShow(tvShow: FavoriteTvShow) = withContext(Dispatchers.IO) {
        favsTvShowsDao.insertFavTvShow(tvShow)
    }

    override fun getFavoritesId(id: Int): Flow<Int?> = flow {
        emit(favsTvShowsDao.getFavId(id))
    }.flowOn(Dispatchers.IO)

    override suspend fun deleteFavoritesTvShow(id: Int) = withContext(Dispatchers.IO) {
        favsTvShowsDao.deleteFavTvShow(id)
    }

}

