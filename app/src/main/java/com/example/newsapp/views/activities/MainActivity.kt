package com.example.newsapp.views.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsapp.R
import com.example.newsapp.di.DaggerAppComponent
import com.example.newsapp.viewmodels.UserAuthenticationViewModel

class MainActivity : AppCompatActivity() {
     var userAuthenticationViewModel: UserAuthenticationViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userAuthenticationViewModel = DaggerAppComponent.factory().create(applicationContext).getUserAuthenticationViewModel()
    }
}