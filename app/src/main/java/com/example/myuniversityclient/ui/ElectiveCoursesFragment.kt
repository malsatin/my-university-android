package com.example.myuniversityclient.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.myuniversityclient.R

/**
 * A fragment that displays elective courses UI: history of elective courses,
 * and a list of available courses, with (possibly) an ability to enroll for a course and
 * read about a course in detail.
 */
class ElectiveCoursesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_elective_courses, container, false)
    }
}
