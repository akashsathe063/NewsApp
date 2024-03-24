package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.utils.AuthListner
import com.example.newsapp.repository.AuthenticationRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserAuthenticationViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {
    private var _UserRegisterStatus = MutableLiveData<Boolean>()
    val userRegisterStatus = _UserRegisterStatus as LiveData<Boolean>
    private var _UserLoginStatus = MutableLiveData<Boolean>()
    val userLoginStatus = _UserLoginStatus as LiveData<Boolean>
    fun userRegistration(name: String, email: String, password: String) {
        Log.d(
            "UserAuthenticationViewModel",
            "#ak iside a name = $name || email = $email || password = $password"
        )
        viewModelScope.launch {
            authenticationRepository.registerUserOnFireBase(
                name,
                email,
                password,
                object : AuthListner {
                    override fun isSuccessful(status: Boolean) {
                        Log.d("UserAuthenticationViewModel", "#ak iside a $status")
                        if (status) {
                            _UserRegisterStatus.value = status
                        } else {
                            _UserRegisterStatus.value = false
                        }
                    }

                })
        }
    }

    fun userLogin(email: String, password: String) {
        Log.d(
            "UserAuthenticationViewModel",
            "#ak inside a loginflow email = $email || password = $password"
        )
        viewModelScope.launch {
            authenticationRepository.userLogin(email, password, object : AuthListner {
                override fun isSuccessful(status: Boolean) {
                    Log.d("UserAuthenticationViewModel", "#ak inside a loginflow $status")

                    Log.d("UserAuthenticationViewModel", "#ak inside a loginflow 1 $status")
                    if (status) {
                        _UserLoginStatus.value = status
                    } else {
                        _UserLoginStatus.value = false
                    }
                }

            })
        }
    }
    fun newsApiCall(){
        viewModelScope.launch {
            authenticationRepository.getNewsApiCall()
        }
    }
}