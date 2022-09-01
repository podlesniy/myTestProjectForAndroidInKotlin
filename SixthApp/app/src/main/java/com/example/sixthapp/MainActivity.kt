package com.example.sixthapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import com.example.sixthapp.databinding.ActivityMainBinding
import com.example.sixthapp.db.CocktailModel
import io.reactivex.disposables.Disposable
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var listCocktail1: List<CocktailModel.Drinks> = ArrayList()
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.text1.movementMethod = ScrollingMovementMethod()
        val dao = (application as App).db.getCocktailDao()

        if(dao.selectAll().isNotEmpty()) {
            listCocktail1 = dao.selectAll()
            val generateDataOnFirstActivity = GenerateDataOnFirstActivity()
            generateDataOnFirstActivity.setAdapter(binding!!,
                this@MainActivity,
                this@MainActivity,
                listCocktail1)
        } else GenerateDataOnFirstActivity().getApi(dao, binding!!,this@MainActivity, this@MainActivity)
    }

    override fun onItemClick(drink: String) {
        cocktail = drink
        startActivity(Intent(this, MainActivity2::class.java))
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @JvmField
        var adapter: CocktailAdapter? = null
        var disposable: Disposable? = null
        var cocktail: String? = null
    }

    override fun onStop() {
        super.onStop()
        if (disposable != null) {
            disposable!!.dispose()
        }
    }
}