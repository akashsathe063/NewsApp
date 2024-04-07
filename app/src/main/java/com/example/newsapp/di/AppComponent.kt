package com.example.newsapp.di

import android.content.Context
import com.example.newsapp.repository.AuthenticationRepository
import com.example.newsapp.viewmodels.UserAuthenticationViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance context: Context):AppComponent
    }

    fun getAuthenticatioRepositoryImpl(): AuthenticationRepository

    fun getUserAuthenticationViewModel(): UserAuthenticationViewModel
}