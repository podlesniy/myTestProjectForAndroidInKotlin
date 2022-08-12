package com.example.firstonkotlin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.firstonkotlin.databinding.FragmentBlankBinding

class BlankFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val binding = FragmentBlankBinding.inflate(inflater)
        calculate(binding.editText1,binding.editText2,binding.textField)
        calculate(binding.editText2,binding.editText1,binding.textField)
        return binding.root
    }

    private fun calculate(editText1: EditText, editText2: EditText, textView: TextView) {
        editText1.addTextChangedListener {
            val sum = editText1.text.toString().ifEmpty { "0" }.toInt() + (editText2.text.toString().ifEmpty { "0" }.toInt())
            textView.text = sum.toString()
        }
    }
}
