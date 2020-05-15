package com.example.myuniversityclient.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myuniversityclient.domain.ProfileViewModel

class ProfileAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    private lateinit var viewModel: ProfileViewModel
    val tabs = listOf("Info", "Docs", "Links", "Grades", "History")
    override fun getItemCount(): Int {
        return tabs.size
    }

    fun addViewModel(profileViewModel: ProfileViewModel) {
        viewModel = profileViewModel
    }

    override fun createFragment(position: Int): Fragment {
        val element = tabs[position]
        var fragment = Fragment()
        val bundle = Bundle()

        when(element){
            "Links" -> fragment = ContactsFragment()
                .apply { subscribeOnViewModel(viewModel.getContacts())}
            "Grades" ->  fragment = GradeBookFragment()
                .apply { subscribeOnViewModel(viewModel.getGradeBook())}
            "Info" -> fragment = PersonalInfoFragment()
                .apply { subscribeOnViewModel(viewModel.getPersonalInfo()) }
            "Docs" -> fragment = PassportsFragment()
                .apply { subscribeOnViewModel(viewModel.getPassports()) }
            "History" -> fragment = EducationHistoryFragment()
                .apply { subscribeOnViewModel(viewModel.getEducationHistory()) }
        }
        fragment.arguments = bundle
        return fragment
    }
}