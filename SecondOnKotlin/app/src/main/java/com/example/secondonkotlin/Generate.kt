package com.example.secondonkotlin

import android.view.View
import android.widget.AdapterView
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView

class Generate {
    fun calculate(editText: EditText, spinner1: Spinner, spinner2: Spinner, textView: TextView) {
        if (editText.text.isNotEmpty()) {
            when (spinner1.selectedItemPosition) {
                0 -> when (spinner2.selectedItemPosition) {
                    0 -> textView.text = editText.text.toString().ifEmpty { "0" }
                    1 -> textView.text = String.format("%.2f", editText.text.toString().toDouble() * 1000.00)
                    2 -> textView.text = String.format("%.3f", editText.text.toString().toDouble() / 1.609)
                }
                1 -> when (spinner2.selectedItemPosition) {
                    0 -> textView.text = String.format("%.3f", editText.text.toString().toDouble() / 1000.00)
                    1 -> textView.text = editText.text.toString().ifEmpty { "0" }
                    2 -> textView.text = String.format("%.4f", editText.text.toString().toDouble() / 1609.00)
                }
                2 -> when (spinner2.selectedItemPosition) {
                    0 -> textView.text = String.format("%.3f", editText.text.toString().toDouble() * 1.609)
                    1 -> textView.text = String.format("%.2f", editText.text.toString().toDouble() * 1609.00)
                    2 -> textView.text = editText.text.toString().ifEmpty { "0" }
                }
            }
        } else textView.text = "0"
    }

    fun spinnerAction(editText: EditText, spinner1: Spinner, spinner2: Spinner, textView: TextView) {
        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                calculate(editText, spinner1, spinner2, textView)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
}