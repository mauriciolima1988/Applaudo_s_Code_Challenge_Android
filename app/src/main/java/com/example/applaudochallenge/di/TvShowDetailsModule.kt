package com.example.applaudochallenge.di

import com.example.applaudochallenge.database.TvShowDetailsLocalDataSource
import com.example.applaudochallenge.database.TvShowDetailsLocalDataSourceImp
import com.example.applaudochallenge.database.TvShowDetailsRepository
import com.example.applaudochallenge.database.TvShowDetailsRepositoryImp
import com.example.applaudochallenge.ui.navigation.TvShowDetailsRemoteDataSource
import com.example.applaudochallenge.ui.navigation.TvShowDetailsRemoteDataSourceImp
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