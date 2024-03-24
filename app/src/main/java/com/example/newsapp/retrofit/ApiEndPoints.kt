package com.example.newsapp.retrofit

import com.example.newsapp.model.NewsApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiEndPoints {
    @GET("top-headlines")
    suspend fun getNewsHeadLines(
        @Query("country") country: String,
        @Query("category") category: String,
//        @Query("q") query: String,
        @Query("apiKey") apiKey: String
    ): Response<NewsApiResponse>
}