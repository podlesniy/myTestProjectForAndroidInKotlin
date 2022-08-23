package com.example.fifthonkotlin

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fifthonkotlin.ApiService.getDataByType
import com.example.fifthonkotlin.databinding.FragmentBlankBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.collections.ArrayList

class BlankFragment : Fragment() {

    lateinit var binding: FragmentBlankBinding
    lateinit var adapter: FactsAdapter
    val list: ArrayList<String>? = null
    private var preferences: SharedPreferences? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentBlankBinding.inflate(inflater)
        binding.textView.movementMethod = ScrollingMovementMethod()
        binding.list.layoutManager = LinearLayoutManager(BlankFragment().context)
        preferences = context?.getSharedPreferences("Table", Context.MODE_PRIVATE)

        for(i in 0..100) {
            list?.add(preferences?.getString("$i", null)!!)
        }
        if (list?.get(0) != null) {
            adapter = FactsAdapter(list)
            binding.list.adapter = adapter
        } else getCatsDate("cat")

        binding.ok.setOnClickListener(View.OnClickListener {
            when (binding.editText.text.toString()) {
                "cat" -> getCatsDate("cat")
                "dog" -> getCatsDate("dog")
                "horse" -> getCatsDate("horse")
                else -> Toast.makeText(context, "Введите какое либо из животных: cat, dog, horse", Toast.LENGTH_SHORT).show()
            }
        })
        return binding.root
    }

    private fun getCatsDate(type: String) {
        getDataByType(type).enqueue(object : Callback<List<FactModel>?> {
            override fun onResponse(call: Call<List<FactModel>?>, response: Response<List<FactModel>?>) {
                if (response.isSuccessful && response.body() != null) {
                    binding.list.layoutManager = LinearLayoutManager(BlankFragment().context)

                    for (i in 0 until response.body()!!.size) {
                        list!!.add("${i + 1}. " + response.body()!![i].text!!)
                    }
                    adapter = FactsAdapter(list!!)
                    binding.list.adapter = adapter
                    saveData(list)
                } else {
                    Toast.makeText(context, "Что-то произошло", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<FactModel>?>, t: Throwable) {
                Toast.makeText(context, "Нет ответа", Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun saveData(list: ArrayList<String>) {
        val editor = preferences?.edit()
        for (i in 0..100) {
            editor?.putString("$i", list[i])
        }
        editor?.apply()
    }
}