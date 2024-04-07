package com.example.newsapp.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.viewmodels.UserAuthenticationViewModel
import com.example.newsapp.views.activities.MainActivity


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var newsAdapter: NewsAdapter
    private var userAuthenticationViewModel :UserAuthenticationViewModel? = null
//        context?.let { DaggerAppComponent.factory().create(it.applicationContext).getUserAuthenticationViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userAuthenticationViewModel = (activity as MainActivity).userAuthenticationViewModel
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
        userAuthenticationViewModel?.newsApiCall()
        observers()
    }

    private fun setRecyclerView() {
        binding.rv.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = newsAdapter
        }
    }

    private fun observers() {
        userAuthenticationViewModel?.newsData?.observe(viewLifecycleOwner) { article ->
            newsAdapter.setNewsList(article)
        }

        userAuthenticationViewModel?.getNewsDataFromLocatDataBase()?.observe(viewLifecycleOwner){ localDataBase->
            Log.d("NewsFragment", "#ak inside a Local db : ${localDataBase.size}")
            newsAdapter.setNewsList(localDataBase)
        }
    }


}