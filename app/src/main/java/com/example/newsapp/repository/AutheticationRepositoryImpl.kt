package com.example.newsapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.newsapp.db.NewsDataBase
import com.example.newsapp.model.Article
import com.example.newsapp.retrofit.ApiEndPoints
import com.example.newsapp.utils.AuthListner
import com.example.newsapp.utils.Constants
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AutheticationRepositoryImpl @Inject constructor(
    private var auth: FirebaseAuth,
    private var apiEndPoints: ApiEndPoints,
    private var newsDataBase: NewsDataBase
) :
    AuthenticationRepository {
    /**
     * FireBaseAuth can create a new user account with collected information and login also
     */
//    val auth = FirebaseAuth.getInstance()
    override suspend fun registerUserOnFireBase(
        name: String,
        email: String,
        password: String,
        authListner: AuthListner
    ) {
        Log.d("AutheticationRepositoryImpl", "#ak inside a suspend fun registerUserOnFireBase")
        if (email.isNotEmpty() && password.isNotEmpty()) {
            Log.d(
                "AutheticationRepositoryImpl",
                "#ak inside a suspend fun registerUserOnFireBase 1"
            )
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    authListner.isSuccessful(true)
                } else {
                    Log.d("AutheticationRepositoryImpl", "#ak inside a ${it.exception?.message}")
                }
            }
        }
    }

    override suspend fun userLogin(email: String, password: String, authListner: AuthListner) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val user = auth.currentUser
                    authListner.isSuccessful(true)
                } else {
                    Log.d("AutheticationRepositoryImpl", "#ak inside a ${it.exception?.message}")
                }
            }
        }
    }

    override suspend fun getNewsApiCall(): ArrayList<Article> {
        val result = apiEndPoints.getNewsHeadLines("in", "sports", Constants.API_KEY)
        Log.d("AuthenticationRepositoryImpl", "#ak inside a getNewsApiCall ${result.code()}")
        if (result.isSuccessful) {
            if (result.body() != null) {
                val articals = result.body()?.articles
                newsDataBase.getNewsDao().upsertNews(articals as ArrayList<Article>)
                return articals as ArrayList<Article>
            }
        }
        return emptyList<Article>() as ArrayList<Article>
    }

    override suspend fun getNewsFromLOcalDataBAse(): LiveData<List<Article>> {
        return newsDataBase.getNewsDao().getNews()
    }
}