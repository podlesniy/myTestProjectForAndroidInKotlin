package com.example.fourthonkotlin

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductAdapter(private var addDelProduct: ArrayList<String>) : RecyclerView.Adapter<ProductAdapter.UserViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun addToJournal(product: String) {
        addDelProduct.add(product)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.products.text = addDelProduct[position];
    }

    override fun getItemCount()= addDelProduct.size

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val products: TextView = itemView.findViewById(R.id.item_name)
    }
}