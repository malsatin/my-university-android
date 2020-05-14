package com.example.myuniversityclient.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.myuniversityclient.MainApplication
import com.example.myuniversityclient.R
import com.example.myuniversityclient.domain.ProfileViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Dagger DI
        (MainApplication.APPLICATION as MainApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileAdapter =
            ProfileAdapter(this)

        val tabLayout: TabLayout = view.findViewById(R.id.profile_tab)

        profileAdapter.addViewModel(viewModel)
        viewPager = view.findViewById(R.id.pager)
        viewPager.adapter = profileAdapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = profileAdapter.tabs[position]
        }.attach()
    }
}
