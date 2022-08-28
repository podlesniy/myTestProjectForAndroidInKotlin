package com.example.sixthapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthapp.CocktailAdapter.CocktailViewHolder
import com.squareup.picasso.Picasso


class CocktailAdapter(
    private var context: Context,
    private val cocktails: ArrayList<String>,
    private val cocktailImage: ArrayList<String>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<CocktailViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return CocktailViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val drinks: String = cocktails[position]
        holder.bind(drinks, cocktailImage[position], context, position)
    }

    override fun getItemCount(): Int {
        return cocktails.size
    }

    class CocktailViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val cock: TextView = itemView.findViewById(R.id.item_name)
        private val root: View = itemView.findViewById(R.id.root)
        private val image: ImageView = itemView.findViewById(R.id.item_image)

        fun bind(cocktails: String, cocktailImage: String?, context: Context, position: Int) {
            Picasso.with(context)
                .load(cocktailImage)
                .into(image)
            cock.text = "${position + 1}. " + cocktails
            root.setOnClickListener { listener.onItemClick(cocktails) }
        }
    }
}


