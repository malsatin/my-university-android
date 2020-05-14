package com.example.myuniversityclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.data.models.ITService
import com.example.myuniversityclient.databinding.TitleSubtitleItemBinding


interface ServiceClickListener {
    fun onClick(service: ITService)
}

class ITServicesAdapter(private val itemList: List<ITService>) : RecyclerView.Adapter<ITServicesAdapter.ViewHolder>() {
    inner class ViewHolder(
        private val binding: TitleSubtitleItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val service = itemList[adapterPosition]

            binding.title.text = service.name
            binding.subtitle.text = service.description

            itemView.setOnClickListener {
                listener?.onClick(service)
            }
        }
    }

    var listener: ServiceClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = TitleSubtitleItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}


