package com.example.testapp10

import com.example.testapp10.ApiService.getData
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.widget.EditText
import android.os.Bundle
import android.widget.Toast
import com.example.testapp10.RequestModel.ExchangeRate
import android.content.Intent
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), OnItemClickListener {

    var countryAdapter: CountryAdapter? = null

    companion object {
        var exchangeRate = RequestModel().ExchangeRate()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText: EditText = findViewById(R.id.enter_date)
        val button: Button = findViewById(R.id.ok)
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")
        val date = simpleDateFormat.format(Date(System.currentTimeMillis()))
        val simpleDateFormat1 = SimpleDateFormat("yyyy")
        val year = simpleDateFormat1.format(Date(System.currentTimeMillis()))
        val yearInt = year.toInt()
        changeDate(date)
        button.setOnClickListener(View.OnClickListener {
            if (editText.text.toString() != "" && editText.text
                    .toString().length == 10 && editText.text.toString()
                    .matches(Regex("^[0-9.]+")) && editText.text.toString()
                    .indexOf(".") == 2 && editText.text.toString().lastIndexOf(".") == 5) {
                if (editText.text.toString().substring(6).toInt() > yearInt) {
                    Toast.makeText(this@MainActivity, "Сначало доживи до " + editText.text.toString().substring(6), Toast.LENGTH_SHORT).show()
                } else if (editText.text.toString().substring(0, 2).toInt() == 0 || editText.text.toString().substring(0, 2).toInt() > 31) {
                    Toast.makeText(this@MainActivity, editText.text.toString().substring(0, 2) + " - день? Серъёзно?", Toast.LENGTH_SHORT).show()
                } else if (editText.text.toString().substring(3, 5).toInt() == 0 || editText.text.toString().substring(3, 5).toInt() > 12) {
                    Toast.makeText(this@MainActivity, editText.text.toString().substring(3, 5) + " - месяц? Серъёзно?", Toast.LENGTH_SHORT).show()
                } else {
                    changeDate(editText.text.toString())
                }
            } else {
                Toast.makeText(
                    this@MainActivity,
                    "Введите дату в формате 'dd.mm.yyyy'",
                    Toast.LENGTH_SHORT
                ).show()
            }
            editText.setText("")
        })
    }

    override fun onItemClick(country: ExchangeRate) {
        exchangeRate = country
        startActivity(Intent(this, MainActivity2::class.java))
    }

    private fun changeDate(date: String?) {
        val recyclerView: RecyclerView = findViewById(R.id.list)
        getData(date)
            .enqueue(object : Callback<RequestModel?> {
                override fun onResponse(
                    call: Call<RequestModel?>,
                    response: Response<RequestModel?>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                        val list = ArrayList<ExchangeRate>()
                        for (i in 1 until response.body()!!.exchangeRate!!.size) {
                            list.add(response.body()!!.exchangeRate!![i])
                        }
                        countryAdapter = CountryAdapter(this@MainActivity, list, this@MainActivity)
                        recyclerView.adapter = countryAdapter
                    } else {
                        Toast.makeText(this@MainActivity, R.string.error, Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<RequestModel?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, R.string.error, Toast.LENGTH_SHORT).show()
                }
            })
    }
}