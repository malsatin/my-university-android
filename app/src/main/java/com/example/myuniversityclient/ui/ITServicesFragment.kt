package com.example.myuniversityclient.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myuniversityclient.MainActivity
import com.example.myuniversityclient.MainApplication

import com.example.myuniversityclient.R
import com.example.myuniversityclient.adapter.ITServicesAdapter
import com.example.myuniversityclient.data.models.ITService
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.domain.ITServicesFragmentViewModel
import com.example.myuniversityclient.domain.MainActivityViewModel
import com.example.myuniversityclient.ui.models.ITServicesListModel
import kotlinx.android.synthetic.main.fragment_it_services.view.*
import javax.inject.Inject

/**
 * A fragment displaying a list of IT services available to the student.
 */
class ITServicesFragment : Fragment() {

    @Inject lateinit var viewModel: ITServicesFragmentViewModel

    private var services = ArrayList<ITService>()
    lateinit var servicesAdapter: ITServicesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Dagger DI
        (MainApplication.APPLICATION as MainApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true

        servicesAdapter = ITServicesAdapter(services)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_it_services, container, false)

        view.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = servicesAdapter
        }

        activity?.let {
            viewModel.getITServicesList().observe(it, Observer(::onITServicesDidUpdate))
        }

        return view
    }

    private fun onITServicesDidUpdate(result: Result<ITServicesList?>) {
//        adapter = result.servi
        result.fold({
            if (it != null) {
                services.clear()
                services.addAll(it.services)

                servicesAdapter.notifyDataSetChanged()
            }
        }, {

        })
    }
}
