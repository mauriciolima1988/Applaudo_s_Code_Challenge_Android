package com.example.applaudochallenge.data.database

import com.example.applaudochallenge.data.network.Result
import com.example.applaudochallenge.data.models.ErrorResponse
import com.example.applaudochallenge.data.models.TvShowDetails
import com.example.applaudochallenge.data.source.TvShowDetailsRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

interface TvShowDetailsRepository {
    fun getTvShowInfo(id: Int): Flow<Result<TvShowDetails, ErrorResponse>>
    fun getTvShowFromFavorite(): Flow<List<FavoriteTvShow>>
    suspend fun insertTvShowInFavorite(tvShow: FavoriteTvShow)
    fun getTvShowFavoriteId(id: Int): Flow<Int?>
    suspend fun deleteTvShowFromFavorite(id: Int)
}

class TvShowDetailsRepositoryImp @Inject constructor(
    private val tvShowDetailsRemoteDataSource: TvShowDetailsRemoteDataSource,
    private val tvShowDetailsLocalDataSource: TvShowDetailsLocalDataSource
) : TvShowDetailsRepository {

    override fun getTvShowInfo(id: Int): Flow<Result<TvShowDetails, ErrorResponse>> {
        return tvShowDetailsRemoteDataSource.getTvShowDetails(id).flowOn(Dispatchers.Default)
    }

    override fun getTvShowFromFavorite(): Flow<List<FavoriteTvShow>> {
        return tvShowDetailsLocalDataSource.getFavoritesTvShows()
    }

    override suspend fun insertTvShowInFavorite(tvShow: FavoriteTvShow) {
        tvShowDetailsLocalDataSource.insertFavoritesTvShow(tvShow)
    }

    override fun getTvShowFavoriteId(id: Int): Flow<Int?> {
        return tvShowDetailsLocalDataSource.getFavoritesId(id)
    }

    override suspend fun deleteTvShowFromFavorite(id: Int) {
        tvShowDetailsLocalDataSource.deleteFavoritesTvShow(id)
    }

}