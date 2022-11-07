package com.example.applaudochallenge.di

import com.example.applaudochallenge.data.source.TvShowsLocalDataSource
import com.example.applaudochallenge.data.source.TvShowsLocalDataSourceImp
import com.example.applaudochallenge.data.source.TvShowsRemoteDataSource
import com.example.applaudochallenge.data.source.TvShowsRemoteDataSourceImp
import com.example.applaudochallenge.data.source.TvShowsRepository
import com.example.applaudochallenge.data.source.TvShowsRepositoryImp
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