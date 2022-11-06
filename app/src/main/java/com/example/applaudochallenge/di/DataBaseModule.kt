package com.example.applaudochallenge.di

import android.content.Context
import androidx.room.Room
import com.example.applaudochallenge.database.TvShowsInfoDataBase
import com.example.applaudochallenge.database.dao.TvShowListDao
import com.example.applaudochallenge.database.dao.FavsTvShowsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Singleton
    @Provides
    fun provideMovieListDao(tvShowsInfoDataBase: TvShowsInfoDataBase): TvShowListDao {
        return tvShowsInfoDataBase.tvShowListDao()
    }

    @Singleton
    @Provides
    fun provideFavsTvShowsDao(tvShowsInfoDataBase: TvShowsInfoDataBase): FavsTvShowsDao {
        return tvShowsInfoDataBase.favsTvShowsDao()
    }

    @Singleton
    @Provides
    fun provideMoviesInfoAppDataBase(@ApplicationContext appContext: Context): TvShowsInfoDataBase {
        return Room.databaseBuilder(appContext, TvShowsInfoDataBase::class.java, "MoviesInfoApp_DB"
        ).fallbackToDestructiveMigration().build()
    }
}