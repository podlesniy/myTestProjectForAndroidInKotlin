package com.example.sixthonkotlin

import android.app.Application
import com.example.sixthonkotlin.db.CocktailDataBase
import com.example.sixthonkotlin.db.CocktailDataBaseInfo

class App : Application() {

    lateinit var db: CocktailDataBase
    lateinit var db2: CocktailDataBaseInfo

    override fun onCreate() {
        super.onCreate()
        db = CocktailDataBase.getInstance(this)
        db2 = CocktailDataBaseInfo.getInstance(this)
    }
}