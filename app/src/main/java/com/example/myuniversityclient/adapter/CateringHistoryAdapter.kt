package com.example.myuniversityclient.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.CateringHistoryItem
import kotlinx.android.synthetic.main.catering_history_item.view.*
import java.text.NumberFormat
import java.util.*


interface ServiceClickListener {
    fun onClick(service: CateringHistoryItem)
}

class CateringHistoryAdapter(private val itemList: List<CateringHistoryItem>) : RecyclerView.Adapter<CateringHistoryAdapter.ViewHolder>() {

    var listener: ServiceClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CateringHistoryAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.catering_history_item, parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: CateringHistoryAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {

            val service = itemList[adapterPosition]

            itemView.period.text = itemView.context.resources.getString(
                R.string.catering_history_item_period,
                service.start, service.end)
            itemView.price.text = NumberFormat.getCurrencyInstance(Locale("ru", "RU")).format(service.price)
            itemView.items.text = service.included_items.joinToString()

            itemView.setOnClickListener {
                listener?.onClick(service)
            }
        }
    }
}


