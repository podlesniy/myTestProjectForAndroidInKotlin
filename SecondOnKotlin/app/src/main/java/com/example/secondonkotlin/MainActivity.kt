package com.example.secondonkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.secondonkotlin.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editText.addTextChangedListener { Generate().calculate(binding.editText, binding.spinner1, binding.spinner2, binding.textView) }

        Generate().spinnerAction(binding.editText, binding.spinner1, binding.spinner2, binding.textView)
        Generate().spinnerAction(binding.editText, binding.spinner2, binding.spinner1, binding.textView)

    }
}