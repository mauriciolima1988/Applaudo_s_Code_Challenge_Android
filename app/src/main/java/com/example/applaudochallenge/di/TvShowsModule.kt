package com.example.applaudochallenge.di

import com.example.applaudochallenge.ui.navigation.TvShowsLocalDataSource
import com.example.applaudochallenge.ui.navigation.TvShowsLocalDataSourceImp
import com.example.applaudochallenge.ui.navigation.TvShowsRemoteDataSource
import com.example.applaudochallenge.ui.navigation.TvShowsRemoteDataSourceImp
import com.example.applaudochallenge.ui.navigation.TvShowsRepository
import com.example.applaudochallenge.ui.navigation.TvShowsRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TvShowsModule {

    @Binds
    @Singleton
    abstract fun provideTvShowsRemoteDataSource(
        tvShowsRemoteDataSourceImp: TvShowsRemoteDataSourceImp
    ): TvShowsRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideTvShowsLocalDataSource(
        tvShowsLocalDataSourceImp: TvShowsLocalDataSourceImp
    ): TvShowsLocalDataSource

    @Binds
    @Singleton
    abstract fun provideTvShowRepository(
        tvShowsRepositoryImp: TvShowsRepositoryImp
    ): TvShowsRepository
}