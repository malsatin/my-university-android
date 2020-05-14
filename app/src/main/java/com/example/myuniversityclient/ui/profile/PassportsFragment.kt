package com.example.myuniversityclient.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.EducationHistory
import com.example.myuniversityclient.data.models.profile.Passport
import com.example.myuniversityclient.data.models.profile.PassportData

class PassportsFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_passport, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

    }

    fun subscribeOnViewModel(data: LiveData<Result<PassportData?>>){
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<PassportData?>){
        println("PASS")

        var adapterPassports = CustomPassportAdapter(requireContext(), result.getOrNull()?.passports!!
        )
        var passports: ListView? = view?.findViewById(R.id.passports)
        passports?.addHeaderView(getHeader())
        passports?.adapter = adapterPassports
    }

    private fun getHeader(): View{
        val inflater : LayoutInflater = context?.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.item_passport, null, true)
        var seriesView: TextView = rowView.findViewById(R.id.series)
        var numberView: TextView = rowView.findViewById(R.id.number)
        var authCodeView: TextView = rowView.findViewById(R.id.authCode)

        seriesView.text ="Series"
        numberView.text = "Number"
        authCodeView.text = "AuthCode"

        return rowView

    }
}