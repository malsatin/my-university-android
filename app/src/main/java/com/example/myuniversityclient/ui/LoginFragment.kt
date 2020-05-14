package com.example.myuniversityclient.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.example.myuniversityclient.MainApplication
import com.example.myuniversityclient.R
import com.example.myuniversityclient.domain.LoginViewModel
import com.google.android.material.snackbar.Snackbar


class LoginFragment : Fragment() {

    lateinit var viewModel: LoginViewModel

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button


    override fun onAttach(context: Context) {
        (context.applicationContext as MainApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(LoginViewModel::class.java)
        }!!
        return inflater.inflate(R.layout.fragment_login, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.usernameText)
        passwordEditText = view.findViewById(R.id.passwordText)

        loginButton = view.findViewById(R.id.submit)
        loginButton.setOnClickListener {
            viewModel.authenticate(
                usernameEditText.text.toString(),
                passwordEditText.text.toString()
            )
        }

        val navController = findNavController()
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
//            viewModel.refuseAuthentication()
//            navController.popBackStack(R.id.action_ProfileSecondFragment_to_ProfileFragment, false)
//        }

        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
                else -> showErrorMessage()
            }
        })


    }

    private fun showErrorMessage() {
        val parentLayout: View = requireView().findViewById(R.id.submit)
        Snackbar.make(parentLayout, "Error auth", Snackbar.LENGTH_LONG)
            .setAction("CLOSE") { }
            .setActionTextColor(resources.getColor(android.R.color.holo_red_light))
            .show()
    }
}


