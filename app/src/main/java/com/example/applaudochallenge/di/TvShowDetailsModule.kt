package com.example.applaudochallenge.di

import com.example.applaudochallenge.data.database.TvShowDetailsLocalDataSource
import com.example.applaudochallenge.data.database.TvShowDetailsLocalDataSourceImp
import com.example.applaudochallenge.data.database.TvShowDetailsRepository
import com.example.applaudochallenge.data.database.TvShowDetailsRepositoryImp
import com.example.applaudochallenge.data.source.TvShowDetailsRemoteDataSource
import com.example.applaudochallenge.data.source.TvShowDetailsRemoteDataSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TvShowDetailsModule {

    @Binds
    @Singleton
    abstract fun provideTvShowDetailsRemoteDataSource(
        tvShowDetailsRemoteDataSourceImp: TvShowDetailsRemoteDataSourceImp
    ): TvShowDetailsRemoteDataSource

    @Binds
    @Singleton
    abstract fun provideTvShowDetailsLocalDataSource(
        tvShowDetailsLocalDataSourceImp: TvShowDetailsLocalDataSourceImp
    ): TvShowDetailsLocalDataSource

    @Binds
    @Singleton
    abstract fun provideTvShowDetailsRepository(
        tvShowDetailsRepositoryImp: TvShowDetailsRepositoryImp
    ): TvShowDetailsRepository
}