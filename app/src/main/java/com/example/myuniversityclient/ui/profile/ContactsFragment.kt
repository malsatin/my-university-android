package com.example.myuniversityclient.ui.profile

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
import com.example.myuniversityclient.data.models.profile.Contacts
import com.example.myuniversityclient.utils.ListViewUtils.Companion.justifyListViewHeightBasedOnChildren
import com.google.android.material.textfield.TextInputLayout


class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    fun subscribeOnViewModel(data: LiveData<Result<Contacts?>>) {
        data.observe(this, Observer(::onContactDidUpdate))
    }

    fun onContactDidUpdate(result: Result<Contacts?>) {
        val registrationAddress: TextInputLayout? = view?.findViewById(R.id.regAddress)
        registrationAddress?.editText?.text?.append(result.getOrNull()?.registrationAddress)

        val residenceAddress: TextInputLayout? = view?.findViewById(R.id.resAddress)
        residenceAddress?.editText?.text?.append(result.getOrNull()?.residenceAddress)

        var adapterEmails = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            result.getOrNull()?.emails!!
        )
        var emails: ListView? = view?.findViewById(R.id.emails)
        emails?.adapter = adapterEmails
        emails?.addHeaderView(TextView(context).apply { text = "Emails" })
        justifyListViewHeightBasedOnChildren(emails!!)

        var adapterTelegrams = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            result.getOrNull()?.telegramsAliases!!
        )
        var telegram: ListView? = view?.findViewById(R.id.telegrams)
        telegram?.adapter = adapterTelegrams
        telegram?.addHeaderView(TextView(context).apply { text = "Telegram" })
        justifyListViewHeightBasedOnChildren(telegram!!)


        var adapterPhones = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            result.getOrNull()?.phones!!
        )
        var phones: ListView? = view?.findViewById(R.id.phones)
        phones?.adapter = adapterPhones
        phones?.addHeaderView(TextView(context).apply { text = "Phones" })
        justifyListViewHeightBasedOnChildren(phones!!)
    }
}