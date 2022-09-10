package com.example.sixthonkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sixthonkotlin.CocktailAdapter.CocktailViewHolder
import com.example.sixthonkotlin.db.CocktailModel
import com.squareup.picasso.Picasso


class CocktailAdapter(
    private var context: Context,
    private val listCocktail: List<CocktailModel.Drinks>,
    private var listener: OnItemClickListener
) : RecyclerView.Adapter<CocktailViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return CocktailViewHolder(v, listener)
    }

    override fun onBindViewHolder(holder: CocktailViewHolder, position: Int) {
        val drinksInfo: CocktailModel.Drinks = listCocktail[position]
        holder.bind(drinksInfo, context, position)
    }

    override fun getItemCount(): Int {
        return listCocktail.size
    }

    class CocktailViewHolder(itemView: View, private val listener: OnItemClickListener) :
        RecyclerView.ViewHolder(itemView) {
        private val cock: TextView = itemView.findViewById(R.id.item_name)
        private val root: View = itemView.findViewById(R.id.root)
        private val image: ImageView = itemView.findViewById(R.id.item_image)


        fun bind(cocktails: CocktailModel.Drinks, context: Context, position: Int) {
            Picasso.with(context)
                .load(cocktails.strDrinkThumb)
                .into(image)
            cock.text = "${position + 1}. " + cocktails.strDrink
            root.setOnClickListener { listener.onItemClick(cocktails.idDrink!!) }
        }
    }
}


