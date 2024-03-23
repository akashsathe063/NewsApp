package com.example.newsapp.model

data class NewsApiResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)