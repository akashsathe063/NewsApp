package com.example.newsapp.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.db.NewsDataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {
    @Singleton
    @Provides
    fun getNewsDataBase(context:Context): NewsDataBase{
        return Room.databaseBuilder(context, NewsDataBase::class.java,"NewsDB").build()
    }
}