package com.example.sixthapp

import android.app.Application
import com.example.sixthapp.db.BookDataBase

class App : Application() {

    lateinit var db: BookDataBase

    override fun onCreate() {
        super.onCreate()
        db = BookDataBase.getInstance(this)
    }
}