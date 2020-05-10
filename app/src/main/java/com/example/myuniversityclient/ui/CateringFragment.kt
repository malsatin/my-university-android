package com.example.myuniversityclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myuniversityclient.R

/**
 * A fragment that displays catering UI: history, current plan and (possibly)
 * an ability to switch to a new plan.
 */
class CateringFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_catering, container, false)
    }
}
