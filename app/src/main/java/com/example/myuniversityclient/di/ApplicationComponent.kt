package com.example.myuniversityclient.di

import com.example.myuniversityclient.MainActivity
import com.example.myuniversityclient.ui.profile.ProfileFragment
import com.example.myuniversityclient.ui.CateringFragment
import com.example.myuniversityclient.ui.ElectivesListFragment
import com.example.myuniversityclient.ui.ITServicesFragment
import dagger.Component
import javax.inject.Singleton

// Definition of the Application graph
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class])
interface ApplicationComponent {
    fun inject(activity: MainActivity)
    fun inject(profileFragment: ProfileFragment)
    fun inject(cateringFragment: CateringFragment)
    fun inject(fragment: ITServicesFragment)
    fun inject(fragment: ElectivesListFragment)
}