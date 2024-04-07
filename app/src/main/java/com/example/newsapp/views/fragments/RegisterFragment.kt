package com.example.newsapp.views.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.newsapp.databinding.FragmentRegisterBinding
import com.example.newsapp.di.DaggerAppComponent
import com.example.newsapp.viewmodels.UserAuthenticationViewModel

class RegisterFragment : Fragment() {
    lateinit var binding: FragmentRegisterBinding


    private var authenticationViewModel: UserAuthenticationViewModel? =
        context?.let { DaggerAppComponent.factory().create(it.applicationContext).getUserAuthenticationViewModel() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrButtonClick()
        moveRegistrationToLogin()
    }

    fun registrationUser() {
        val email = binding.email.text.toString()
        val password = binding.Password.text.toString()
        val confirmPasssword = binding.confirmPassword.text.toString()
        val name = binding.userName.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty() && confirmPasssword.isNotEmpty() && name.isNotEmpty()) {
            Log.d(
                "RegistrationFragment",
                "#ak iside a name = $name || email = $email || password = $password"
            )
            if (password == confirmPasssword) {
                authenticationViewModel?.userRegistration(name, email, password)
                authenticationViewModel?.userRegisterStatus?.observe(viewLifecycleOwner, Observer {
                    if (it) {
                        popFragmentRegistration()
                    }
                })
            }
        }
    }


    private fun moveRegistrationToLogin() {
        binding.signIn.setOnClickListener {
            popFragmentRegistration()
        }
    }

    private fun registrButtonClick() {
        binding.registerBtn.setOnClickListener {
            registrationUser()
        }
    }

    private fun popFragmentRegistration() {
        findNavController().popBackStack()
    }
}