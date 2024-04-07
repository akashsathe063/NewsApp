package com.example.newsapp.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentLoginBinding
import com.example.newsapp.viewmodels.UserAuthenticationViewModel
import com.example.newsapp.views.activities.MainActivity


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private  var userAuthenticationViewModel :UserAuthenticationViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        if (context?.applicationContext == null){
//            Log.d("LoginFragment","#ak inside a 1")
//        }else {
//          userAuthenticationViewModel = context?.let {
//              DaggerAppComponent.factory().create(it.applicationContext)
//                  .getUserAuthenticationViewModel()
//          }
//        }
        userAuthenticationViewModel = (activity as MainActivity).userAuthenticationViewModel
        moveLoginToRegistration()
        loginButtonOnClick()
    }

    /**
     * in navigation framwork
     * to redirect one destination to another we can use findNavController
     * it is a controller to controll the all navigation
     */
    private fun moveLoginToRegistration() {
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun loginButtonOnClick() {
        binding.signInBtn.setOnClickListener {
            userLogin()
        }
    }

    private fun userLogin() {
        Log.d("LoginFragment", "#ak inside a userLogin")
        val email = binding.email.text.toString()
        val password = binding.Password.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            Log.d("LoginFragment", "#ak inside a userLogin email = $email || password = $password")

            Log.d("LoginFragment", "#ak inside a LifecycleScope launch : ${userAuthenticationViewModel.toString()}")
            userAuthenticationViewModel?.userLogin(email, password)
            userAuthenticationViewModel?.userLoginStatus?.observe(viewLifecycleOwner) {
                Log.d("LoginFragment", "#ak inside a userLogin $it")
                if (it) {
                    findNavController().navigate(R.id.action_loginFragment_to_newsFragment)
//                    userAuthenticationViewModel.newsApiCall()
                }
            }
        }
    }
}
