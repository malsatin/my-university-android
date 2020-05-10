package com.example.myuniversityclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myuniversityclient.R

/**
 * A fragment that displays a list of available internships with (possibly)
 * an ability to enroll for an internship, and read about an internship in detail.
 */
class InternshipsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_internships, container, false)
    }
}
