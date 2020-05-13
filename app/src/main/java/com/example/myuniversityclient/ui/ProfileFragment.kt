package com.example.myuniversityclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2

import com.example.myuniversityclient.R
import com.example.myuniversityclient.domain.ProfileViewModel
import javax.inject.Inject

/**
 * Profile screen fragment.
 * Displays detailed info about current user.
 */
class ProfileFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var profileAdapter: ProfileAdapter
    @Inject
    lateinit var viewModel: ProfileViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileAdapter = ProfileAdapter(this)
        profileAdapter.addViewModel(viewModel)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = profileAdapter
    }
}
