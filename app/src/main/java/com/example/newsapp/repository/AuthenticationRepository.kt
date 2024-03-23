package com.example.newsapp.repository

import com.example.newsapp.utils.AuthListner

interface AuthenticationRepository {
    suspend fun registerUserOnFireBase( name:String,email:String, password:String,authListner: AuthListner)
    suspend fun userLogin(email:String, password:String,authListner: AuthListner)
}