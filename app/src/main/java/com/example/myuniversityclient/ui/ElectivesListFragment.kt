package com.example.myuniversityclient.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myuniversityclient.MainApplication
import com.example.myuniversityclient.R
import com.example.myuniversityclient.databinding.FragmentElectivesListBinding
import com.example.myuniversityclient.domain.ElectivesListFragmentViewModel
import javax.inject.Inject

/**
 * A fragment representing a list of Items.
 * Activities containing this fragment MUST implement the
 * [ElectivesListFragment.OnListFragmentInteractionListener] interface.
 */
class ElectivesListFragment : Fragment() {

    private lateinit var binding: FragmentElectivesListBinding
    private val adapter = ElectivesRecyclerViewAdapter()
    @Inject
    lateinit var viewModel: ElectivesListFragmentViewModel

    override fun onAttach(context: Context) {
        (context.applicationContext as MainApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentElectivesListBinding.inflate(inflater)

        setupMainViews()
        subscribeForViewModel()

        return binding.root
    }

    private fun setupMainViews() {
        val recyclerView = binding.list

        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
    }

    private fun subscribeForViewModel() {
        viewModel.electives.observe(viewLifecycleOwner, Observer { result ->
            result.fold({
                adapter.updateValues(it)
            }, {
                val toast = Toast.makeText(
                    context,
                    R.string.error_electives_update,
                    Toast.LENGTH_SHORT
                )
                toast.show()
            })
        })
    }
}
