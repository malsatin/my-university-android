package com.example.myuniversityclient.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myuniversityclient.R
import com.example.myuniversityclient.domain.LoginViewModel

abstract class AuthenticatedFragment: Fragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        var loginViewModel: LoginViewModel = activity?.run {
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        }!!
        loginViewModel.authenticationState.observe(
            viewLifecycleOwner,
            Observer { authenticationState ->
                when (authenticationState) {
                    LoginViewModel.AuthenticationState.UNAUTHENTICATED -> navController.navigate(R.id.nav_login)
                }
            })
    }
}