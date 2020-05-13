package com.example.myuniversityclient.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myuniversityclient.R

import com.example.myuniversityclient.ui.dummy.DummyContent
import com.example.myuniversityclient.ui.dummy.DummyContent.DummyItem

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ElectivesListFragment.OnListFragmentInteractionListener] interface.
 */
class ElectivesListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_electives_list, container, false)

        setupRecyclerView(view)

        return view
    }

    private fun setupRecyclerView(view: View) {
        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = ElectivesRecyclerViewAdapter(DummyContent.ITEMS)
            }
        }
    }
}
