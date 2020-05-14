package com.example.myuniversityclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.CateringHistoryItem
import com.example.myuniversityclient.databinding.CateringHistoryItemBinding
import kotlinx.android.synthetic.main.catering_history_item.view.*
import java.text.NumberFormat
import java.util.*

class CateringHistoryAdapter(private val itemList: List<CateringHistoryItem>) : RecyclerView.Adapter<CateringHistoryAdapter.ViewHolder>() {
    interface ServiceClickListener {
        fun onClick(service: CateringHistoryItem)
    }

    inner class ViewHolder(private val binding: CateringHistoryItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val service = itemList[adapterPosition]

            binding.period.text = itemView.context.resources.getString(
                R.string.catering_history_item_period,
                service.start, service.end)
            binding.price.text = NumberFormat
                .getCurrencyInstance(Locale("ru", "RU"))
                .format(service.price)
            binding.items.text = service.included_items.joinToString()

            itemView.setOnClickListener {
                listener?.onClick(service)
            }
        }
    }

    var listener: ServiceClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CateringHistoryAdapter.ViewHolder {
        val binding = CateringHistoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CateringHistoryAdapter.ViewHolder, position: Int) {
        holder.bind()
    }
}


