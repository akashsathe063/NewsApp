package com.example.newsapp.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.databinding.NewsHeadlineItemBinding
import com.example.newsapp.model.Article

class NewsViewHolder(private val binding: NewsHeadlineItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(article: Article, context: Context) {
        binding.title.text = article.title
        Glide.with(context).load(article.urlToImage).into(binding.img)
    }

}
