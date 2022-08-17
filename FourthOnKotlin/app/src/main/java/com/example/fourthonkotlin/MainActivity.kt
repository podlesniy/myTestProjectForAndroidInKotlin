package com.example.fourthonkotlin

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fourthonkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: ProductAdapter
    private val rand = Random(System.nanoTime())
    private val startKolProduct = (1..7).random(rand)
    private var randomKol: Int = 0
    private var kolProduct: Int = startKolProduct

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.list.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(ArrayList())
        binding.list.adapter = productAdapter
        binding.kol.text = kolProduct.toString()
        binding.kol0.text = startKolProduct.toString()
        binding.kol1.text = (startKolProduct * 4).toString()
        binding.textView.movementMethod = ScrollingMovementMethod()

        GlobalScope.launch {
            while (kolProduct >= 0) {
                byuAndSupply(1)
            }
        }

        GlobalScope.launch {
            while (kolProduct >= 0) {
                byuAndSupply(2)
            }
        }
    }

    private fun byuAndSupply (num: Int) {
        runBlocking {
            delay((500..5000).random(rand).toLong())
            if (kolProduct in (1..(startKolProduct * 4))) {
                randomKol = (1..7).random(rand)
                withContext(Dispatchers.Main) {
                    if (num == 1) {
                        productAdapter.addToJournal(("Поставка: $randomKol товара(ов)"))
                        kolProduct += randomKol
                    } else {
                        productAdapter.addToJournal(("Покупка: $randomKol товара(ов)"))
                        kolProduct -= randomKol
                    }
                    binding.kol.text = kolProduct.toString()
                }
            } else if (kolProduct > (startKolProduct * 4)) {
                withContext(Dispatchers.Main) {
                    binding.kol.text = "Нехватает места"
                    cancel()
                }
            } else if (kolProduct <= 0) {
                withContext(Dispatchers.Main) {
                    binding.kol.text = "Закончился товар"
                    cancel()
                }
            }
        }
    }
}