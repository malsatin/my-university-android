package com.example.myuniversityclient.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.Passport
import com.example.myuniversityclient.data.models.profile.PassportData
import kotlinx.android.synthetic.main.fragment_passport.view.*

class PassportsFragment : Fragment() {

    lateinit var passportAdapter: CustomPassportAdapter
    private var passports = ArrayList<Passport>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true
        passportAdapter = CustomPassportAdapter(passports)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view: View = inflater.inflate(R.layout.fragment_passport, container, false)
        view.passportRecyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            adapter = passportAdapter
            this.layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }
        return view
    }

    fun subscribeOnViewModel(data: LiveData<Result<PassportData?>>) {
        data.observe(this, Observer(::onPassportUpdate))
    }

    fun onPassportUpdate(result: Result<PassportData?>) {
        result.fold({
            passports.clear()
            passports.addAll(it?.passports!!)
            passportAdapter.notifyDataSetChanged()
        }, {})
    }
}