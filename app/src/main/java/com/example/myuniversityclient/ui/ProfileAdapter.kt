package com.example.myuniversityclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myuniversityclient.data.models.profile.PersonalInfo
import com.example.myuniversityclient.domain.MainActivityViewModel
import com.example.myuniversityclient.domain.ProfileViewModel
import javax.inject.Inject

class ProfileAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private lateinit var viewModel: ProfileViewModel
    val tabs = listOf("PersonalInfo","Passports","Contacts","GradeBook", "EducationHistory")
    override fun getItemCount(): Int {
        return tabs.size
    }

    fun addViewModel(profileViewModel: ProfileViewModel){
        viewModel = profileViewModel
    }
    override fun createFragment(position: Int): Fragment {
        var element = tabs[position]
        var fragment = Fragment()
        var bundle = Bundle()
        if (element.equals("Contacts")) {
            fragment = ContactsFragment().apply { subscribeOnViewModel(viewModel.getContacts()) }
        }else if(element.equals("GradeBook")){
            fragment = GradeBookFragment().apply { subscribeOnViewModel(viewModel.getGradeBook()) }
        }else if(element.equals("PersonalInfo")){
            fragment = PersonalInfoFragment().apply { subscribeOnViewModel(viewModel.getPersonalInfo()) }
        }else if(element.equals("Passport")){
            fragment = PassportsFragment().apply { subscribeOnViewModel(viewModel.getPassports()) }
        }else if(element.equals("EducationHistory")){
            fragment = EducationHistoryFragment().apply { subscribeOnViewModel(viewModel.getEducationHistory()) }
        }
        fragment.arguments = bundle
        return fragment
    }
}