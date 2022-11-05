package com.example.applaudochallenge.network

import com.example.applaudochallenge.BuildConfig
import com.google.gson.Gson
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit

object NetworkModule {

    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideOkHttpClient(okHttpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .also {
                if (BuildConfig.DEBUG) {
                    it.addInterceptor(okHttpLoggingInterceptor)
                }
            }
        return builder.build()
    }

    fun provideGsonConverterFactory(): GsonConverterFactory {
        val json = Gson().newBuilder()
            .setLenient()
            .create()
        return GsonConverterFactory.create(json)
    }

    fun provideRetrofitTvShowsApiInstance(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(ResponseAdapterFactory())
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

    fun provideTvShowsApiService(retrofit: Retrofit): TvShowsApi {
        return retrofit.create(TvShowsApi::class.java)
    }
}