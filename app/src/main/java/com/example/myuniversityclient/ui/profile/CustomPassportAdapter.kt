package com.example.myuniversityclient.ui.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myuniversityclient.data.models.profile.Passport
import com.example.myuniversityclient.databinding.ItemPassportBinding

class CustomPassportAdapter(
    val passportDatas: List<Passport>
) : RecyclerView.Adapter<CustomPassportAdapter.ViewHolder>()  {

//    val inflater: LayoutInflater =
//        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }


    inner class ViewHolder(private val binding: ItemPassportBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            val passport = passportDatas[adapterPosition]

//            val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)
//            val startString = formatter.format(service.start)
//            val endString = formatter.format(service.end)
            binding.doc.text = "Passport"

            binding.seriesAndNumber.text = String.format("Series: %s Number: %s", passport.passportSeries, passport.number)
            binding.authCode.text = String.format("Auth code: %s", passport.authorityCode)
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
}