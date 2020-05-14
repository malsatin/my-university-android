package com.example.myuniversityclient.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.Passport

class CustomPassportAdapter(
    val context: Context,
    val passportDatas: List<Passport>
) : BaseAdapter() {


    val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    //var passportDatas: List<Passport> = java.util.ArrayList()


    //2
    override fun getItem(position: Int): Passport {
        return passportDatas[position]
    }

    //3
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return passportDatas.size
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View = inflater.inflate(R.layout.item_passport, null, true)

        var seriesView: TextView = rowView.findViewById(R.id.series)
        var numberView: TextView = rowView.findViewById(R.id.number)
        var authCodeView: TextView = rowView.findViewById(R.id.authCode)

        seriesView.text = passportDatas[position].passportSeries
        numberView.text = passportDatas[position].number
        authCodeView.text = passportDatas[position].authorityCode

        return rowView
    }
}