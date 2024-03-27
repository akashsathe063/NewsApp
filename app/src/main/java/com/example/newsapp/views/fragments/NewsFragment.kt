package com.example.newsapp.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.di.DaggerAppComponent


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private val userAuthenticationViewModel =
        DaggerAppComponent.builder().build().getUserAuthenticationViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        newsAdapter = NewsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        userAuthenticationViewModel.newsApiCall()
        observers()
    }

    private fun setRecyclerView() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    private fun observers() {
        userAuthenticationViewModel.newsData.observe(viewLifecycleOwner) { article ->
            newsAdapter.setNewsList(article)
        }
    }


}