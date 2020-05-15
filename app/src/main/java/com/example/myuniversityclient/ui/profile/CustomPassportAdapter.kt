package com.example.myuniversityclient.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.R
import com.example.myuniversityclient.data.models.profile.Passport
import com.example.myuniversityclient.databinding.ItemPassportBinding

class CustomPassportAdapter(
    val passportDatas: List<Passport>
) : RecyclerView.Adapter<CustomPassportAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemPassportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val passport = passportDatas[adapterPosition]
            binding.doc.text = itemView.context.resources.getString(R.string.passport)

            binding.seriesAndNumber.text =
                String.format(itemView.context.resources.getString(R.string.passport_data_format),
                    passport.passportSeries,
                    passport.number)
            binding.authCode.text = String.format(itemView.context.resources.getString(R.string.auth_code_format),
                passport.authorityCode)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomPassportAdapter.ViewHolder {
        val binding = ItemPassportBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return passportDatas.size
    }

    override fun onBindViewHolder(holder: CustomPassportAdapter.ViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }
}