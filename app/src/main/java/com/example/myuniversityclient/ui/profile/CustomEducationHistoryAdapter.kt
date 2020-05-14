package com.example.myuniversityclient.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.EducationHistory


class CustomEducationHistoryAdapter(
    val context: Context,
    val objects: List<EducationHistory.EducationYear>

)
    : BaseAdapter() {

     var academicYears: ArrayList<String> = java.util.ArrayList()
     var speciality: ArrayList<String> = java.util.ArrayList()
     var course: ArrayList<String> = java.util.ArrayList()
    val inflater : LayoutInflater = context.getSystemService( Context.LAYOUT_INFLATER_SERVICE ) as LayoutInflater


    override fun getCount(): Int {
        return objects.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View = inflater.inflate(R.layout.item_education_year, null, true)

        var academicYearView: TextView = rowView.findViewById(R.id.academicYear)
        var specialityView: TextView = rowView.findViewById(R.id.speciality)
        var courseView: TextView = rowView.findViewById(R.id.course)

        academicYearView.text = objects[position].academicYear
        specialityView.text = objects[position].speciality
        courseView.text = objects[position].course

        return rowView
    }

    override fun getItem(position: Int): EducationHistory.EducationYear {
        return objects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}