package com.example.myuniversityclient.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.data.models.profile.GradeBook
import com.example.myuniversityclient.databinding.ItemMarkBinding

class CustomGradeAdapter(
    val grades: List<GradeBook.Mark>
) : RecyclerView.Adapter<CustomGradeAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemMarkBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val grade = grades[adapterPosition]
            binding.discipline.text = grade.discipline
            binding.mark.text = grade.mark
            binding.teacher.text = grade.teacher
        }
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomGradeAdapter.ViewHolder {
        val binding = ItemMarkBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return grades.size
    }

    override fun onBindViewHolder(holder: CustomGradeAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

}