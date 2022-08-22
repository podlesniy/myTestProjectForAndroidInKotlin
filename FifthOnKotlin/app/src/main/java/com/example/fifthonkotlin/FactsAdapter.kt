package com.example.fifthonkotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class FactsAdapter(private val facts: List<String>) : RecyclerView.Adapter<FactsAdapter.FactsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return FactsViewHolder(v)
    }

    override fun onBindViewHolder(holder: FactsViewHolder, position: Int) {
        holder.facts.text = facts[position]
    }

    override fun getItemCount() = facts.size

    class FactsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facts: TextView = itemView.findViewById(R.id.item_name)
    }
}