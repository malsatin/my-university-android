package com.example.myuniversityclient.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.EducationHistory

class EducationHistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_education_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun subscribeOnViewModel(data: LiveData<Result<EducationHistory?>>) {
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<EducationHistory?>) {
        var adapterEducations = CustomEducationHistoryAdapter(
            requireContext(),
            result.getOrNull()?.educationYears!!
        )
        var educations: ListView? = view?.findViewById(R.id.educations)
        educations?.addHeaderView(getHeader())
        educations?.adapter = adapterEducations
    }

    private fun getHeader(): View {
        val inflater: LayoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.item_education_year, null, true)
        var academicYearView: TextView = rowView.findViewById(R.id.academicYear)
        var specialityView: TextView = rowView.findViewById(R.id.speciality)
        var courseView: TextView = rowView.findViewById(R.id.course)

        academicYearView.text = "Year"
        specialityView.text = "Speciality"
        courseView.text = "Course"

        return rowView

    }
}