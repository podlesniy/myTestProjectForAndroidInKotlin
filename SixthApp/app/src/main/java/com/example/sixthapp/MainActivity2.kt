package com.example.sixthapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sixthapp.MainActivity.Companion.cocktail
import com.example.sixthapp.databinding.ActivityMain2Binding
import io.reactivex.disposables.Disposable

open class MainActivity2 : AppCompatActivity() {

    private var binding: ActivityMain2Binding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val dao = (application as App).db2.getCocktailDaoInfo()

        if (dao.selectAll().isNotEmpty()) {
            for (i in dao.selectAll()) {
                if (i.idDrink == cocktail ) {
                    GenerateDataOnSecondActivity().setInfo(i, dao, binding!!, this@MainActivity2)
                }
            }
        } else GenerateDataOnSecondActivity().getApi(dao, binding!!, this@MainActivity2)
        GenerateDataOnSecondActivity().getApi(dao, binding!!, this@MainActivity2)
    }

    companion object {
        @JvmField
        var disposable: Disposable? = null
    }

    override fun onStop() {
        super.onStop()
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}