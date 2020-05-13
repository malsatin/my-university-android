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
import com.example.myuniversityclient.data.models.profile.GradeBook
import com.example.myuniversityclient.data.models.profile.PersonalInfo
import java.util.*

class PersonalInfoFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){

    }

    fun subscribeOnViewModel(data: LiveData<Result<PersonalInfo?>>){
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<PersonalInfo?>){
        val fullName: TextView? = view?.findViewById(R.id.fullName)
        fullName?.text = result.getOrNull()?.fullName
        val birthDate: TextView? = view?.findViewById(R.id.birthDate)
        birthDate?.text = result.getOrNull()?.birthDate.toString()
        val sex: TextView? = view?.findViewById(R.id.sex)
        sex?.text = result.getOrNull()?.sex
        val citizenship: TextView? = view?.findViewById(R.id.citizenship)
        citizenship?.text = result.getOrNull()?.citizenship
        val snils: TextView? = view?.findViewById(R.id.snils)
        snils?.text = result.getOrNull()?.snils
        val inn: TextView? = view?.findViewById(R.id.snils)
        inn?.text = result.getOrNull()?.inn
        val registrationCertificate: TextView? = view?.findViewById(R.id.registrationCertificate)
        registrationCertificate?.text = result.getOrNull()?.registrationCertificate
    }
}