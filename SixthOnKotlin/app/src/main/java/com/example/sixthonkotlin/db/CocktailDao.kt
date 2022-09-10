package com.example.sixthonkotlin.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cocktail: CocktailModel.Drinks)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg cocktails: CocktailModel.Drinks)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cocktails: List<CocktailModel.Drinks>)

    @Query("SELECT * FROM Drinks")
    fun selectAll(): List<CocktailModel.Drinks>

    @Query("DELETE FROM Drinks")
    fun clearTable()
}

@Dao
interface CocktailDao2 {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cocktail: CocktailModelInfo.DrinksInfo)

    @Query("SELECT * FROM DrinksInfo")
    fun selectAll(): List<CocktailModelInfo.DrinksInfo>

    @Query("SELECT * FROM DrinksInfo WHERE idDrink = :id")
    fun selectCocktailWithId(id: String): CocktailModelInfo.DrinksInfo
}