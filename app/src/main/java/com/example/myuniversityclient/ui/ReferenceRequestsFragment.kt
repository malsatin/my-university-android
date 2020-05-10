package com.example.myuniversityclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myuniversityclient.R

/**
 * A fragment that display references UI, which provides an ability to request
 * a reference and shows references history.
 */
class ReferenceRequestsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reference_requests, container, false)
    }
}
