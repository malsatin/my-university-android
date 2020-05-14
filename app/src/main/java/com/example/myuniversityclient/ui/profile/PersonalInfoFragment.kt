package com.example.myuniversityclient.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.PersonalInfo
import java.text.SimpleDateFormat

class PersonalInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_personal_info, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun subscribeOnViewModel(data: LiveData<Result<PersonalInfo?>>) {
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<PersonalInfo?>) {
        val fullName: TextView? = view?.findViewById(R.id.fullName)
        fullName?.text = String.format("%s: %s", "Full Name", result.getOrNull()?.fullName)
        val birthDate: TextView? = view?.findViewById(R.id.birthDate)
        birthDate?.text = String.format(
            "%s: %s", "Birth date", SimpleDateFormat
                .getDateInstance()
                .format(result.getOrNull()!!.birthDate)
        )
        val sex: TextView? = view?.findViewById(R.id.sex)
        sex?.text = String.format("%s: %s", "Sex", result.getOrNull()?.sex)
        val citizenship: TextView? = view?.findViewById(R.id.citizenship)
        citizenship?.text = String.format("%s: %s", "Citizenship", result.getOrNull()?.citizenship)
        val snils: TextView? = view?.findViewById(R.id.snils)
        snils?.text = String.format("%s: %s", "SNILS", result.getOrNull()?.snils)
        val inn: TextView? = view?.findViewById(R.id.inn)
        inn?.text = String.format("%s: %s", "INN", result.getOrNull()?.inn)
        val registrationCertificate: TextView? = view?.findViewById(R.id.registrationCertificate)
        registrationCertificate?.text =
            String.format("%s: %s", "RegCertificate", result.getOrNull()?.registrationCertificate)
    }
}