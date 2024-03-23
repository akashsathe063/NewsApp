package com.example.newsapp.di

import com.example.newsapp.repository.AuthenticationRepository
import com.example.newsapp.repository.AutheticationRepositoryImpl
import com.example.newsapp.viewmodels.UserAuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun getFireBaseAuth():FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun getAutheticationRepositoryImpl(): AuthenticationRepository {
        return AutheticationRepositoryImpl(getFireBaseAuth())
    }

}