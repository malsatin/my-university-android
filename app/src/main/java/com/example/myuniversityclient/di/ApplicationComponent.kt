package com.example.myuniversityclient.di

import com.example.myuniversityclient.MainActivity
import com.example.myuniversityclient.ui.CateringFragment
import com.example.myuniversityclient.ui.ITServicesFragment
import dagger.Component

// Definition of the Application graph
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(cateringFragment: CateringFragment)

}