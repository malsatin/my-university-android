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
import com.example.myuniversityclient.data.models.profile.GradeBook
import kotlinx.android.synthetic.main.fragment_gradebook.view.*

class GradeBookFragment : Fragment() {
    private var grades = ArrayList<GradeBook.Mark>()
    lateinit var gradeBookAdapter: CustomGradeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.retainInstance = true
        gradeBookAdapter = CustomGradeAdapter(grades)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var view: View = inflater.inflate(R.layout.fragment_gradebook, container, false)
        view.gradeBookRecyclerView.apply {
            val layoutManager = LinearLayoutManager(context)
            adapter = gradeBookAdapter
            this.layoutManager = layoutManager
            addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))
        }
        return view
    }

    fun subscribeOnViewModel(data: LiveData<Result<GradeBook?>>) {
        data.observe(this, Observer(::onGradeBookUpdate))
    }

    fun onGradeBookUpdate(result: Result<GradeBook?>) {
        result.fold({
            grades.clear()
            grades.addAll(it?.grades!!)
            gradeBookAdapter.notifyDataSetChanged()
        }, {})
    }
}