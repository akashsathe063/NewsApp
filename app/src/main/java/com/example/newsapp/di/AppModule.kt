package com.example.newsapp.di

import com.example.newsapp.repository.AuthenticationRepository
import com.example.newsapp.repository.AutheticationRepositoryImpl
import com.example.newsapp.retrofit.ApiEndPoints
import com.example.newsapp.utils.Constants
import com.example.newsapp.viewmodels.UserAuthenticationViewModel
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun getFireBaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Singleton
    @Provides
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun providesApiEndPoits(retrofit: Retrofit): ApiEndPoints {
        return retrofit.create(ApiEndPoints::class.java)
    }

    @Provides
    fun getAutheticationRepositoryImpl(
        firebaseAuth: FirebaseAuth,
        apiEndPoints: ApiEndPoints
    ): AuthenticationRepository {
        return AutheticationRepositoryImpl(firebaseAuth, apiEndPoints)
    }

}