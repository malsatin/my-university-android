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


class ContactsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_contacts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun subscribeOnViewModel(data: LiveData<Result<Contacts?>>) {
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<Contacts?>) {
        val registrationAddress: TextView? = view?.findViewById(R.id.regAddress)
        registrationAddress?.text =
            String.format("%s: %s", "Registration", result.getOrNull()?.registrationAddress)

        val residenceAddress: TextView? = view?.findViewById(R.id.resAddress)
        residenceAddress?.text =
            String.format("%s: %s", "Residence", result.getOrNull()?.residenceAddress)

        var adapterEmails = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            result.getOrNull()?.emails!!
        )
        var emails: ListView? = view?.findViewById(R.id.emails)
        emails?.adapter = adapterEmails
        emails?.isClickable = false
        //emails?.isEnabled = false
        emails?.addHeaderView(TextView(context).apply { text = "Emails" })
        justifyListViewHeightBasedOnChildren(emails!!)

        var adapterTelegrams = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            result.getOrNull()?.telegramsAliases!!
        )
        var telegram: ListView? = view?.findViewById(R.id.telegrams)
        telegram?.adapter = adapterTelegrams
        //telegram?.isEnabled = false
        telegram?.isClickable = false
        telegram?.addHeaderView(TextView(context).apply { text = "Telegram" })
        justifyListViewHeightBasedOnChildren(telegram!!)


        var adapterPhones = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_list_item_1,
            result.getOrNull()?.phones!!
        )
        var phones: ListView? = view?.findViewById(R.id.phones)
        phones?.adapter = adapterPhones
        phones?.isClickable = false
        justifyListViewHeightBasedOnChildren(phones!!)
        //phones?.isEnabled = false
        phones?.addHeaderView(TextView(context).apply { text = "Phones" })



        residenceAddress?.text = result.getOrNull()?.residenceAddress


    }

    fun justifyListViewHeightBasedOnChildren(listView: ListView) {
        val adapter = listView.adapter ?: return
        val vg: ViewGroup = listView
        var totalHeight = 0
        for (i in 0 until adapter.count) {
            val listItem = adapter.getView(i, null, vg)
            listItem.measure(0, 0)
            totalHeight += listItem.measuredHeight
        }
        val par = listView.layoutParams
        par.height = totalHeight + listView.dividerHeight * (adapter.count - 1)
        listView.layoutParams = par
        listView.requestLayout()
    }

}