package com.example.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.model.Article
import com.example.newsapp.repository.AuthenticationRepository
import com.example.newsapp.utils.AuthListner
import kotlinx.coroutines.launch
import javax.inject.Inject

class UserAuthenticationViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) :
    ViewModel() {
    private var _UserRegisterStatus = MutableLiveData<Boolean>()
    val userRegisterStatus = _UserRegisterStatus as LiveData<Boolean>
    private var _UserLoginStatus = MutableLiveData<Boolean>()
    val userLoginStatus = _UserLoginStatus as LiveData<Boolean>
    private var _NewsData = MutableLiveData<ArrayList<Article>>()
    val newsData = _NewsData as LiveData<ArrayList<Article>>
    lateinit var _NewsDatFromLocalDataBase : LiveData<ArrayList<Article>>
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

    fun newsApiCall() {
        viewModelScope.launch {
            _NewsData.value = authenticationRepository.getNewsApiCall()
        }
    }

    fun getNewsDataFromLocatDataBase():LiveData<ArrayList<Article>>{
        viewModelScope.launch {
            _NewsDatFromLocalDataBase = authenticationRepository.getNewsFromLOcalDataBAse() as LiveData<ArrayList<Article>>
        }
        return  _NewsDatFromLocalDataBase
    }
}