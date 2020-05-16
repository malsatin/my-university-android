package com.example.myuniversityclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.databinding.TitleSubtitleItemBinding
import com.example.myuniversityclient.ui.models.ElectiveItemModel

class ElectivesRecyclerViewAdapter() :
    RecyclerView.Adapter<ElectivesRecyclerViewAdapter.ViewHolder>() {
    inner class ViewHolder(private val binding: TitleSubtitleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ElectiveItemModel) {
            binding.title.text = item.title
            binding.subtitle.text = itemView.resources
                .getString(R.string.elective_item_subtitle, item.type, item.date)
        }
    }

    private var values: List<ElectiveItemModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TitleSubtitleItemBinding.inflate(inflater)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = values.size

    fun updateValues(values: List<ElectiveItemModel>) {
        this.values = values
        notifyDataSetChanged()
    }
}
