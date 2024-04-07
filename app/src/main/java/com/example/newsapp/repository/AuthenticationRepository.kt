package com.example.newsapp.repository

import androidx.lifecycle.LiveData
import com.example.newsapp.model.Article
import com.example.newsapp.utils.AuthListner

interface AuthenticationRepository {
    suspend fun registerUserOnFireBase( name:String,email:String, password:String,authListner: AuthListner)
    suspend fun userLogin(email:String, password:String,authListner: AuthListner)

    suspend fun getNewsApiCall():ArrayList<Article>
    suspend fun getNewsFromLOcalDataBAse():LiveData<List<Article>>
}