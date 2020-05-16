package com.example.myuniversityclient.ui

import android.view.View
import androidx.navigation.Navigation.findNavController

interface Redirectable {
    fun redirectToLogin(curView: View, redirectViewId: Int){
        findNavController(curView).navigate(redirectViewId)
    }
}