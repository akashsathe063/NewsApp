package com.example.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.newsapp.model.Article

@Dao
interface NewsDao {
    @Upsert
    suspend fun upsertNews(article: ArrayList<Article>)

    @Query("SELECT * FROM Article")
    fun getNews(): LiveData<List<Article>>
}