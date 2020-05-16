package com.example.myuniversityclient.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.CateringHistoryItem
import com.example.myuniversityclient.databinding.CateringHistoryItemBinding
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

class CateringHistoryAdapter(private val itemList: List<CateringHistoryItem>) :
    RecyclerView.Adapter<CateringHistoryAdapter.ViewHolder>() {
    interface ServiceClickListener {
        fun onClick(service: CateringHistoryItem)
    }

    inner class ViewHolder(private val binding: CateringHistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val service = itemList[adapterPosition]

            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
            val startString = formatter.format(service.start)
            val endString = formatter.format(service.end)
            binding.period.text = itemView.context.resources.getString(
                R.string.catering_history_item_period,
                startString,
                endString
            )

            binding.price.text = service.price
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
    ): ViewHolder {
        val binding = CateringHistoryItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }
}
