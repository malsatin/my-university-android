package com.example.myuniversityclient.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.EducationHistory
import kotlinx.android.synthetic.main.fragment_education_history.view.*

class EducationHistoryFragment : Fragment() {
    private var educations = ArrayList<EducationHistory.EducationYear>()
    lateinit var educationAdapter: CustomEducationHistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true
        educationAdapter = CustomEducationHistoryAdapter(educations)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view: View = inflater.inflate(R.layout.fragment_education_history, container, false)
        view.educationRecyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            adapter = educationAdapter
            this.layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }
        return view
    }

    fun subscribeOnViewModel(data: LiveData<Result<EducationHistory?>>) {
        data.observe(this, Observer(::onEducationHistoryUpdate))
    }

    fun onEducationHistoryUpdate(result: Result<EducationHistory?>) {
        result.fold({
            educations.clear()
            educations.addAll(it?.educationYears!!)
            educationAdapter.notifyDataSetChanged()
        }, {})
    }
}
