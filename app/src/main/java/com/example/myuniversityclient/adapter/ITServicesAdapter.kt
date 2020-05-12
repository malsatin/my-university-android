package com.example.myuniversityclient.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.ITService
import kotlinx.android.synthetic.main.it_service_list_item.view.*

class ITServicesAdapter(private val itemList: List<ITService>) : RecyclerView.Adapter<ITServicesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ITServicesAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.it_service_list_item, parent,
            false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ITServicesAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind() {

            val service = itemList[adapterPosition]

            itemView.list_title.text = service.name
            itemView.list_description.text = service.description
        }
    }
}