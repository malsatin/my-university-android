package com.example.myuniversityclient.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.Contacts
import com.example.myuniversityclient.data.models.profile.GradeBook

class GradeBookFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_gradebook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

    }

    fun subscribeOnViewModel(data: LiveData<Result<GradeBook?>>){
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<GradeBook?>){
        val regAddress: TextView? = view?.findViewById(R.id.grade)
        regAddress?.text = result.getOrNull()?.grades!![0].mark
    }
}