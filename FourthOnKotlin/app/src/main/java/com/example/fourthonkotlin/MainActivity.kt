package com.example.fourthonkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthonkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private var kolProduct = 4
    private var randomKol: Int = 0
    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val rand = Random(System.nanoTime())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(ArrayList())
        binding.kol.text = kolProduct.toString()
        binding.list.adapter = productAdapter

        GlobalScope.launch {
            while (kolProduct >= 0) {
                delay((500..5000).random(rand).toLong())
                if (kolProduct in (1..15)) {
                    randomKol = (1..7).random(rand)
                    kolProduct += randomKol
                    withContext(Dispatchers.Main) {
                        productAdapter.addToJournal(("Поставка: $randomKol товара(ов)"))
                        binding.kol.text = kolProduct.toString()
                    }
                }
            }
        }

        GlobalScope.launch {
            while (kolProduct >= 0) {
                delay((500..5000).random(rand).toLong())
                if (kolProduct in (1..15)) {
                    randomKol = (1..7).random(rand)
                    kolProduct -= randomKol
                    withContext(Dispatchers.Main) {
                        productAdapter.addToJournal(("Покупка: $randomKol товара(ов)"))
                        binding.kol.text = kolProduct.toString()
                    }
                }
            }
        }
    }
}

