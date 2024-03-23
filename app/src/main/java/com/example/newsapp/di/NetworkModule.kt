package com.example.newsapp.di

import com.example.newsapp.retrofit.ApiEndPoints
import com.example.newsapp.utils.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
@Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiEndPoits(retrofit: Retrofit):ApiEndPoints{
        return retrofit.create(ApiEndPoints::class.java)
    }
}