package com

import android.app.Application
import com.example.newsapp.di.DaggerAppComponent

class MobileApplication : Application() {
    lateinit var appComponent: DaggerAppComponent
    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder().build()
    }
}