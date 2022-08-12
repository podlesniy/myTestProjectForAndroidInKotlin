package com.example.testapp10

import android.content.Context
import com.example.testapp10.RequestModel.ExchangeRate
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp10.CountryAdapter.CountryViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.example.testapp10.R
import android.widget.TextView
import java.util.*

class CountryAdapter (
    private val context: Context,
    private val countries: List<ExchangeRate>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<CountryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return CountryViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])

    }

    override fun getItemCount(): Int {
        return countries.size
    }

    class CountryViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val country: TextView = itemView.findViewById(R.id.item_name)
        private val root: View = itemView.findViewById(R.id.root)

        fun bind(countries: ExchangeRate?) {
            val formattedRate = String.format(
                Locale.US,
                "Rate of %s is %.2f",
                countries!!.currency,
                countries.saleRateNB
            )
            country.text = formattedRate
            root.setOnClickListener { listener.onItemClick(countries) }
        }
    }
}