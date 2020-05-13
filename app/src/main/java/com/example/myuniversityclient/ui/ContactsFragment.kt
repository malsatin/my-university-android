package com.example.myuniversityclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.Contacts
import com.example.myuniversityclient.data.models.profile.GradeBook

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

    }

}