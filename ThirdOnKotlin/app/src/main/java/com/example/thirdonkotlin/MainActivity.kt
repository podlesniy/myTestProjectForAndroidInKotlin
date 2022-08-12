package com.example.thirdonkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.example.thirdonkotlin.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.addTextChangedListener {
            if (it!!.isNotEmpty() && it.toString().toInt() <= 99999) {
                binding.button.visibility = View.VISIBLE
                binding.text.text = it
            } else if (it.isNotEmpty() && it.toString().toInt() > 99999){
                Toast.makeText(this, "Введите число до 100000", Toast.LENGTH_SHORT).show()
                binding.button.visibility = View.INVISIBLE
                binding.editText.text.clear()
                binding.text.text = "0"
            } else {
                binding.button.visibility = View.INVISIBLE
                binding.editText.text.clear()
                binding.text.text = "0"
            }
        }

        binding.button.setOnClickListener {
            val job = GlobalScope.launch {
                for ((n, i) in (binding.editText.text.toString().toInt() downTo 0).withIndex()) {
                    delay(1000L)
                    withContext(Dispatchers.Main) {
                        binding.editText.visibility = View.INVISIBLE
                        binding.button.visibility = View.INVISIBLE
                        binding.buttonStop.visibility = View.VISIBLE
                        binding.text1.visibility = View.VISIBLE
                        binding.text2.visibility = View.VISIBLE
                        binding.text2.text = "$n секунд"
                        binding.text.text = "$i"
                    }
                }
                withContext(Dispatchers.Main) {
                    visible(binding.buttonStop,binding.editText,binding.text1,binding.text2)
                }
            }
            binding.buttonStop.setOnClickListener {
                visible(binding.buttonStop,binding.editText,binding.text1,binding.text2)
                job.cancel()
            }
        }
    }

    private fun visible(button: Button, editText: EditText, text1: TextView, text2: TextView) {
        button.visibility = View.INVISIBLE
        editText.visibility = View.VISIBLE
        text1.visibility = View.INVISIBLE
        text2.visibility = View.INVISIBLE
        editText.text.clear()
    }
}