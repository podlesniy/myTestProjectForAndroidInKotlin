package com.example.sixthonkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [CocktailModel.Drinks::class], version = 1, exportSchema = false)
abstract class CocktailDataBase : RoomDatabase() {

    abstract fun getCocktailDao(): CocktailDao

    companion object {
        fun getInstance(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CocktailDataBase::class.java,
            "Cocktail"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}
    @Database(entities = [CocktailModelInfo.DrinksInfo::class], version = 1, exportSchema = false)
    abstract class CocktailDataBaseInfo : RoomDatabase() {

        abstract fun getCocktailDaoInfo(): CocktailDao2

        companion object {
            fun getInstance(context: Context) = Room.databaseBuilder(
                context.applicationContext,
                CocktailDataBaseInfo::class.java,
                "CocktailInfo"
            ).fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
        }
}