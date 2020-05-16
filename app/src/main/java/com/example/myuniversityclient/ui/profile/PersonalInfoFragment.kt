package com.example.myuniversityclient.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.PersonalInfo
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat

class PersonalInfoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }

    fun subscribeOnViewModel(data: LiveData<Result<PersonalInfo?>>) {
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<PersonalInfo?>) {
        val fullName: TextInputLayout? = view?.findViewById(R.id.fullName)
        fullName?.editText?.text?.append(result.getOrNull()?.fullName)
        val birthDate: TextInputLayout? = view?.findViewById(R.id.birthDate)
        birthDate?.editText?.text?.append(
            SimpleDateFormat
                .getDateInstance()
                .format(result.getOrNull()!!.birthDate)
        )
        val sex: TextInputLayout? = view?.findViewById(R.id.sex)
        sex?.editText?.text?.append(result.getOrNull()?.sex)
        val citizenship: TextInputLayout? = view?.findViewById(R.id.citizenship)
        citizenship?.editText?.text?.append(result.getOrNull()?.citizenship)
        val snils: TextInputLayout? = view?.findViewById(R.id.snils)
        snils?.editText?.text?.append(result.getOrNull()?.snils)
        val inn: TextInputLayout? = view?.findViewById(R.id.inn)
        inn?.editText?.text?.append(result.getOrNull()?.inn)
        val registrationCertificate: TextInputLayout? =
            view?.findViewById(R.id.registrationCertificate)
        registrationCertificate?.editText?.text?.append(result.getOrNull()?.registrationCertificate)

    }
}