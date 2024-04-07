package com.example.newsapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.newsapp.model.Article

@Database(entities = [Article::class], version = 3, exportSchema = false)
@TypeConverters(TypeConverter::class)
abstract class NewsDataBase : RoomDatabase() {
    abstract fun getNewsDao(): NewsDao
}