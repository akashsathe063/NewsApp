package com.example.newsapp.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.newsapp.databinding.NewsHeadlineItemBinding
import com.example.newsapp.model.Article

class NewsAdapter : Adapter<NewsViewHolder>() {
    private var newsList = ArrayList<Article>()
    lateinit var context: Context

    @SuppressLint("NotifyDataSetChanged")
    fun setNewsList(newsList: ArrayList<Article>) {
        this.newsList = newsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        context = parent.context
        return NewsViewHolder(
            NewsHeadlineItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
      holder.bind(newsList[position],context)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }
}