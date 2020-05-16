package com.example.myuniversityclient.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.EducationHistory
import com.example.myuniversityclient.databinding.ItemEducationYearBinding


class CustomEducationHistoryAdapter(
    val educations: List<EducationHistory.EducationYear>

) : RecyclerView.Adapter<CustomEducationHistoryAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: ItemEducationYearBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val education = educations[adapterPosition]
            binding.academicYear.text = education.academicYear
            binding.course.text = String.format(itemView.context.resources.getString(R.string.course_format),
                education.course)
            binding.speciality.text = education.speciality
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomEducationHistoryAdapter.ViewHolder {
        val binding = ItemEducationYearBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return educations.size
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onBindViewHolder(holder: CustomEducationHistoryAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
}