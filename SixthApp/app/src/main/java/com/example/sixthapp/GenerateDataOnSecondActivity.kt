package com.example.sixthapp

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.sixthapp.MainActivity.Companion.cocktail
import com.example.sixthapp.MainActivity.Companion.disposable
import com.example.sixthapp.databinding.ActivityMain2Binding
import com.example.sixthapp.db.CocktailDao2
import com.example.sixthapp.db.CocktailModelInfo
import com.example.sixthapp.network.ApiService
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class GenerateDataOnSecondActivity {

    fun getApi (
        dao: CocktailDao2,
        binding: ActivityMain2Binding,
        applicationContext: Context,
        supportActionBar: androidx.appcompat.app.ActionBar?,
    ) {
        if (binding.info.text == "") {
             disposable = ApiService.getInfoCocktail(cocktail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (binding.info.text == "") {
                        setInfo(it.drinks!![0], dao, binding, applicationContext, supportActionBar)
                    }
                }) {
                    if (binding.ingredient1.text == "") {
                        binding.inst.visibility = View.INVISIBLE
                        binding.ingr.visibility = View.INVISIBLE
                        Toast.makeText(
                            applicationContext,
                            "Данные не загружены",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    fun setInfo(
        drink: CocktailModelInfo.DrinksInfo,
        dao: CocktailDao2,
        binding: ActivityMain2Binding,
        applicationContext: Context,
        supportActionBar: androidx.appcompat.app.ActionBar?
    ) {
        supportActionBar!!.title = drink.strDrink
        Picasso.with(applicationContext)
            .load(drink.strDrinkThumb)
            .into(binding.imageFull)
        binding.info.text = drink.strInstructions

        val stringNull: List<String?> = mutableListOf(drink.strIngredient1,drink.strIngredient2,drink.strIngredient3,drink.strIngredient4,drink.strIngredient5,drink.strIngredient6,drink.strIngredient7,drink.strIngredient8,drink.strIngredient9,drink.strIngredient10,drink.strIngredient11,drink.strIngredient12,drink.strIngredient13,drink.strIngredient14,drink.strIngredient15)
        Collections.replaceAll(stringNull,null,"")
        dao.insert(
            CocktailModelInfo.DrinksInfo(
                drink.idDrink,
                drink.strDrink,
                drink.strDrinkThumb,
                drink.strInstructions,
                stringNull[0]!!,
                stringNull[1]!!,
                stringNull[2]!!,
                stringNull[3]!!,
                stringNull[4]!!,
                stringNull[5]!!,
                stringNull[6]!!,
                stringNull[7]!!,
                stringNull[8]!!,
                stringNull[9]!!,
                stringNull[10]!!,
                stringNull[11]!!,
                stringNull[12]!!,
                stringNull[13]!!,
                stringNull[14]!!,
            )
        )

        visible(binding.ingredient1, stringNull[0]!!)
        visible(binding.ingredient2, stringNull[1]!!)
        visible(binding.ingredient3, stringNull[2]!!)
        visible(binding.ingredient4, stringNull[3]!!)
        visible(binding.ingredient5, stringNull[4]!!)
        visible(binding.ingredient6, stringNull[5]!!)
        visible(binding.ingredient7, stringNull[6]!!)
        visible(binding.ingredient8, stringNull[7]!!)
        visible(binding.ingredient9, stringNull[8]!!)
        visible(binding.ingredient10, stringNull[9]!!)
        visible(binding.ingredient11, stringNull[10]!!)
        visible(binding.ingredient12, stringNull[11]!!)
        visible(binding.ingredient13, stringNull[12]!!)
        visible(binding.ingredient14, stringNull[13]!!)
        visible(binding.ingredient15, stringNull[14]!!)
    }

    private fun visible(ingredient: TextView, drink: String) {
        if (drink != "") {
            ingredient.visibility = View.VISIBLE
            ingredient.text = drink
        }
    }
}