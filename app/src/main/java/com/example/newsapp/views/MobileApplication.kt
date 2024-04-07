package com.example.newsapp.views

import android.app.Application
import com.example.newsapp.di.AppComponent
import com.example.newsapp.di.DaggerAppComponent

class MobileApplication : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
         appComponent = DaggerAppComponent.factory().create(this)
    }
}