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
import com.example.myuniversityclient.data.models.profile.GradeBook

class GradeBookFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_gradebook, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    fun subscribeOnViewModel(data: LiveData<Result<GradeBook?>>) {
        data.observe(this, Observer(::update))
    }

    fun update(result: Result<GradeBook?>) {
        var adapterGrades = CustomGradeAdapter(
            requireContext(),
            result.getOrNull()?.grades!!
        )
        var grades: ListView? = view?.findViewById(R.id.marks)
        grades?.addHeaderView(getHeader())
        grades?.adapter = adapterGrades

    }

    private fun getHeader(): View {
        val inflater: LayoutInflater =
            context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val rowView: View = inflater.inflate(R.layout.item_mark, null, true)
        var disciplineView: TextView = rowView.findViewById(R.id.discipline)
        var teacherView: TextView = rowView.findViewById(R.id.teacher)
        var markView: TextView = rowView.findViewById(R.id.mark)

        disciplineView.text = "Discipline"
        teacherView.text = "Teacher"
        markView.text = "Mark"

        return rowView

    }
}