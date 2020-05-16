package com.example.myuniversityclient.di

import com.example.myuniversityclient.MainActivity
import com.example.myuniversityclient.ui.CateringFragment
import com.example.myuniversityclient.ui.ElectivesListFragment
import com.example.myuniversityclient.ui.ITServicesFragment
import com.example.myuniversityclient.ui.profile.EducationHistoryFragment
import com.example.myuniversityclient.ui.profile.GradeBookFragment
import com.example.myuniversityclient.ui.profile.PassportsFragment
import com.example.myuniversityclient.ui.profile.ProfileFragment
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
    fun inject(fragment: EducationHistoryFragment)
    fun inject(fragment: GradeBookFragment)
    fun inject(fragment: PassportsFragment)
}