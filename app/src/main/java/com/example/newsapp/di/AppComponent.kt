package com.example.newsapp.di

import com.example.newsapp.repository.AuthenticationRepository
import com.example.newsapp.repository.AutheticationRepositoryImpl
import com.example.newsapp.retrofit.ApiEndPoints
import com.example.newsapp.viewmodels.UserAuthenticationViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun getAuthenticatioRepositoryImpl(): AuthenticationRepository

    fun getUserAuthenticationViewModel(): UserAuthenticationViewModel
}