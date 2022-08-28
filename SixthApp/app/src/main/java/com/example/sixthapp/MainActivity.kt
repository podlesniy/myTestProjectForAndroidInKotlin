package com.example.sixthapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sixthapp.databinding.ActivityMainBinding
import com.example.sixthapp.db.CocktailModel
import com.example.sixthapp.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(), OnItemClickListener {

    var adapter: CocktailAdapter? = null
    private var preferences: SharedPreferences? = null
    private var preferences1: SharedPreferences? = null
    var list: ArrayList<String> = ArrayList()
    var binding: ActivityMainBinding? = null
    var image: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
//        val dao = (application as App).db.getBookDao()

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        preferences = getSharedPreferences("TableFirst", Context.MODE_PRIVATE)
        preferences1 = getSharedPreferences("TableSecond", Context.MODE_PRIVATE)
        if (preferences!!.getString("0", null) != null) {
            for (i in 0 until preferences!!.getInt("size", 0)) {
                list.add(preferences!!.getString("$i", null)!!)
                image.add(preferences1!!.getString("$i", null)!!)
            }
            binding!!.list.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CocktailAdapter(this@MainActivity, list, image,this@MainActivity)
            binding!!.list.adapter = adapter
        } else
            getApi()
    }

    private fun getApi() {
        ApiService.getDataCocktail().enqueue(object : Callback<CocktailModel?> {
            override fun onResponse(
                call: Call<CocktailModel?>,
                response: Response<CocktailModel?>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    binding!!.list.layoutManager = LinearLayoutManager(this@MainActivity)
                    for (i in 0 until response.body()!!.drinks!!.size) {
                        list.add(response.body()!!.drinks!![i].strDrink!!)
                        image.add(response.body()!!.drinks!![i].strDrinkThumb!!)
                    }
                    adapter = CocktailAdapter(this@MainActivity, list, image,this@MainActivity)
                    binding!!.list.adapter = adapter
                    saveData(list, image)
                } else {
                    Toast.makeText(this@MainActivity, "Что-то произошло", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CocktailModel?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Нет ответа", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onItemClick(cocktail: String) {
        TODO("Not yet implemented")
    }

    fun saveData(list: ArrayList<String>, image: ArrayList<String>) {
        for (i in 0 until list.size) {
            preferences!!.edit().putString("$i", list[i]).apply()
            preferences1!!.edit().putString("$i", image[i]).apply()
        }
        preferences!!.edit().putInt("size", list.size).apply()
    }
}



//var buffer = StringBuffer("DataBase")
//private lateinit var binding: ActivityMainBinding
//var count = 0
//toGo(dao)
//
//        val disposable = dao.selectModern()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe({
//                if (it.isNotEmpty()) {
//                    buffer.append("\n $it")
//                    binding.text.text = buffer
//                }
//            }, {
//
//            })
//
//        binding.clearText.setOnClickListener{
//            dao.clearTable()
//            binding.text.text = "DataBase"
//            count = 0
//        }
//    }
//
//    private fun toGo(dao:BookDao) {
//        GlobalScope.launch {
//            while(count < 5) {
//                delay(1000L)
//                dao.insert(Book(count.toLong(), "author$count", "name$count", 2005 + count))
//                count++
//            }
////            val list = dao.selectAll()
////            withContext(Dispatchers.Main) {
////                Toast.makeText(applicationContext, list.toString(), Toast.LENGTH_SHORT).show()
////            }
//        }