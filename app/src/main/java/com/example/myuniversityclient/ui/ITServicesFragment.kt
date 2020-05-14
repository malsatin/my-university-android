package com.example.myuniversityclient.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
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
import com.example.myuniversityclient.data.models.ITService
import com.example.myuniversityclient.data.models.ITServicesList
import com.example.myuniversityclient.domain.ITServicesFragmentViewModel
import kotlinx.android.synthetic.main.fragment_it_services.view.*
import javax.inject.Inject


/**
 * A fragment displaying a list of IT services available to the student.
 */
class ITServicesFragment : Fragment(),
    ServiceClickListener {

    @Inject lateinit var viewModel: ITServicesFragmentViewModel

    private var services = ArrayList<ITService>()
    private lateinit var servicesAdapter: ITServicesAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Dagger DI
        (MainApplication.APPLICATION as MainApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true

        servicesAdapter =
            ITServicesAdapter(services)
        servicesAdapter.listener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_it_services, container, false)

        view.recyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            adapter = servicesAdapter
            this.layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }

        activity?.let {
            viewModel.getITServicesList().observe(it, Observer(::onITServicesDidUpdate))
        }

        return view
    }

    private fun onITServicesDidUpdate(result: Result<ITServicesList?>) {
        result.fold({
            if (it != null) {
                services.clear()
                services.addAll(it.services)

                servicesAdapter.notifyDataSetChanged()

                val toast = Toast.makeText(context, R.string.success_itservices_update, Toast.LENGTH_SHORT)
                toast.show()


            }
        }, {
            val toast = Toast.makeText(context, R.string.error_itservices_update, Toast.LENGTH_SHORT)
            toast.show()
        })
    }

    override fun onClick(service: ITService) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(service.link))
        startActivity(browserIntent)
    }
}
