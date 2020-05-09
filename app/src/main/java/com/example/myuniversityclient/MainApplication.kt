package com.example.myuniversityclient

import android.app.Application
import com.example.myuniversityclient.di.DaggerApplicationComponent

// appComponent lives in the Application class to share its lifecycle
class MainApplication: Application() {
    // Reference to the application graph that is used across the whole app
    val appComponent = DaggerApplicationComponent.create()

    override fun onCreate() {
        super.onCreate()
        APPLICATION = this
    }

    companion object {
        lateinit var APPLICATION: Application
    }
}