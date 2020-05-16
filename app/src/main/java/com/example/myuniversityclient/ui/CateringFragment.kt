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
import com.example.myuniversityclient.data.models.CateringHistoryItem
import com.example.myuniversityclient.data.models.CateringHistoryItemsList
import com.example.myuniversityclient.domain.CateringFragmentViewModel
import kotlinx.android.synthetic.main.fragment_catering.view.*
import javax.inject.Inject


/**
 * A fragment displaying a catering history
 */
class CateringFragment : Fragment(), CateringHistoryAdapter.ServiceClickListener, Redirectable {

    @Inject
    lateinit var viewModel: CateringFragmentViewModel

    private var history = ArrayList<CateringHistoryItem>()
    lateinit var historyAdapter: CateringHistoryAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Dagger DI
        (MainApplication.APPLICATION as MainApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true

        historyAdapter =
            CateringHistoryAdapter(history)
        historyAdapter.listener = this
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_catering, container, false)

        view.recyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
            this.layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }

        activity?.let {
            viewModel.cateringHistoryList.observe(it, Observer(::onHistoryDidUpdate))
        }

        return view
    }

    private fun onHistoryDidUpdate(result: Result<CateringHistoryItemsList?>) {
        result.fold({
            if(it!=null) {
                history.clear()
                history.addAll(it.history)

                historyAdapter.notifyDataSetChanged()

                val toast = Toast.makeText(
                    activity?.applicationContext,
                    R.string.success_load_catering_history,
                    Toast.LENGTH_LONG
                )
                toast.show()
            }else{
                redirectToLogin(requireView(), R.id.nav_login)
            }
        }, {
            val toast = Toast.makeText(activity?.applicationContext, R.string.error_load_catering_history, Toast.LENGTH_LONG)
            toast.show()
        })
    }

    override fun onClick(service: CateringHistoryItem) {
        val toast = Toast.makeText(
            activity?.applicationContext,
            service.included_items.joinToString(),
            Toast.LENGTH_LONG
        )
        toast.show()
    }
}
