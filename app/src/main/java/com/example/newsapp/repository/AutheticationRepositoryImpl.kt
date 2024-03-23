package com.example.newsapp.repository

import android.util.Log
import com.example.newsapp.utils.AuthListner
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AutheticationRepositoryImpl @Inject constructor(private var auth: FirebaseAuth) :
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
}