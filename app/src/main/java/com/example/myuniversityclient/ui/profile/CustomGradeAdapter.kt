package com.example.myuniversityclient.ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.GradeBook

class CustomGradeAdapter(
    val context: Context,
    val objects: List<GradeBook.Mark>
) : BaseAdapter() {

    val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    override fun getCount(): Int {
        return objects.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView: View = inflater.inflate(R.layout.item_mark, null, true)

        var disciplineView: TextView = rowView.findViewById(R.id.discipline)
        var teacherView: TextView = rowView.findViewById(R.id.teacher)
        var markView: TextView = rowView.findViewById(R.id.mark)

        disciplineView.text = objects[position].discipline
        teacherView.text = objects[position].teacher
        markView.text = objects[position].mark

        return rowView
    }

    override fun getItem(position: Int): GradeBook.Mark {
        return objects[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

}