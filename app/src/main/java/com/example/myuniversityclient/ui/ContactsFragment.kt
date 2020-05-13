package com.example.myuniversityclient.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.Contacts


class ContactsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

    }

    fun subscribeOnViewModel(data: LiveData<Result<Contacts?>>){
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<Contacts?>){
        val registrationAddress: TextView? = view?.findViewById(R.id.regAddress)
        registrationAddress?.text = result.getOrNull()?.registrationAddress

        val residenceAddress: TextView? = view?.findViewById(R.id.resAddress)
        residenceAddress?.text = result.getOrNull()?.residenceAddress

        var adapterEmails = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, result.getOrNull()?.emails!!)
        var emails: ListView? = view?.findViewById(R.id.emails)
        emails?.adapter = adapterEmails

        var adapterTelegrams = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, result.getOrNull()?.telegramsAliases!!)
        var telegram: ListView? = view?.findViewById(R.id.telegrams)
        telegram?.adapter = adapterTelegrams

        var adapterPhones = ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, result.getOrNull()?.phones!!)
        var phones: ListView? = view?.findViewById(R.id.phones)
        phones?.adapter = adapterPhones



        residenceAddress?.text = result.getOrNull()?.residenceAddress


    }

}