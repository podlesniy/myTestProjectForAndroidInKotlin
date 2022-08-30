package com.example.sixthapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

class CocktailModel {
    val drinks: List<Drinks>? = null

    @Entity
    data class Drinks(
        @PrimaryKey
        val idDrink: String,
        val strDrink: String,
        val strDrinkThumb: String,
    )
}

class CocktailModelInfo {
    val drinks: List<DrinksInfo>? = null

    @Entity
    data class DrinksInfo(
        @PrimaryKey
        val idDrink: String,
        val strDrink: String,
        val strDrinkThumb: String,
        val strInstructions: String,
        val strIngredient1: String,
        val strIngredient2: String,
        val strIngredient3: String,
        val strIngredient4: String,
        val strIngredient5: String,
        val strIngredient6: String,
        val strIngredient7: String,
        val strIngredient8: String,
        val strIngredient9: String,
        val strIngredient10: String,
        val strIngredient11: String,
        val strIngredient12: String,
        val strIngredient13: String,
        val strIngredient14: String,
        val strIngredient15: String

    )
}



