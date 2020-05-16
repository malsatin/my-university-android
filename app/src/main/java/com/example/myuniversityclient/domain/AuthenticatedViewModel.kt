package com.example.myuniversityclient.domain

import androidx.lifecycle.ViewModel

abstract class AuthenticatedViewModel: ViewModel() {

    //  тут где то сторадж

    fun subscribeOnAuthToken(){
        //вызвать onAuthStateChanged подписавшись на сторадж
    }

    abstract fun onAuthStateChanged()
}