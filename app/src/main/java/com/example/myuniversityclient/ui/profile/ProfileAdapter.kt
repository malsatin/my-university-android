package com.example.myuniversityclient.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myuniversityclient.domain.ProfileViewModel

class ProfileAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private lateinit var viewModel: ProfileViewModel
    val tabs = listOf("Info","Docs","Contact","Grades", "History")
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
        if (element.equals("Contact")) {
            fragment = ContactsFragment()
                .apply { subscribeOnViewModel(viewModel.getContacts()) }
        }else if(element.equals("Grades")){
            fragment = GradeBookFragment()
                .apply { subscribeOnViewModel(viewModel.getGradeBook()) }
        }else if(element.equals("Info")){
            fragment = PersonalInfoFragment()
                .apply { subscribeOnViewModel(viewModel.getPersonalInfo()) }
        }else if(element.equals("Docs")){
            fragment = PassportsFragment()
                .apply { subscribeOnViewModel(viewModel.getPassports()) }
        }else if(element.equals("History")){
            fragment = EducationHistoryFragment()
                .apply { subscribeOnViewModel(viewModel.getEducationHistory()) }
        }
        fragment.arguments = bundle
        return fragment
    }
}