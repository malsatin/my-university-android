package com.example.myuniversityclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myuniversityclient.R
import com.example.myuniversityclient.domain.LoginViewModel

/**
 * A fragment that display references UI, which provides an ability to request
 * a reference and shows references history.
 */
class ReferenceRequestsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reference_requests, container, false)
    }

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
