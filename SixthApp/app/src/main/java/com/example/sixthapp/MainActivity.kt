package com.example.sixthapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sixthapp.databinding.ActivityMainBinding
import com.example.sixthapp.db.CocktailDao
import com.example.sixthapp.db.CocktailModel
import com.example.sixthapp.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), OnItemClickListener {

    private var disposable: Disposable? = null
    var adapter: CocktailAdapter? = null
    var listCocktail: ArrayList<CocktailModel.Drinks> = ArrayList()
    var listCocktail1: List<CocktailModel.Drinks> = ArrayList()
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.text1.movementMethod = ScrollingMovementMethod()

        val dao = (application as App).db.getCocktailDao()
        if(dao.selectAll().isNotEmpty()) {
            listCocktail1 = dao.selectAll()
            binding!!.list.layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = CocktailAdapter(this@MainActivity, listCocktail1, this@MainActivity)
            binding!!.list.adapter = adapter
        } else getApi(dao)
    }
    private fun getApi(dao: CocktailDao) {
        disposable = ApiService
            .getDataCocktail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (i in it.drinks!!) {
                    listCocktail.add(i)
                    dao.insert(CocktailModel.Drinks(i.idDrink,i.strDrink,i.strDrinkThumb))
                }
                binding!!.list.layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = CocktailAdapter(this@MainActivity, listCocktail, this@MainActivity)
                binding!!.list.adapter = adapter
            }) {
                Toast.makeText(this@MainActivity, "Ошибка Api-запроса", Toast.LENGTH_SHORT).show()
            }

//                                  ~~~~~~~~~~~~~~~~~RetroFit~~~~~~~~~~~~~~~~~~
//
//        ApiService.getDataCocktail().enqueue(object : Callback<CocktailModel?> {
//            override fun onResponse(
//                call: Call<CocktailModel?>,
//                response: Response<CocktailModel?>
//            ) {
//                if (response.isSuccessful && response.body() != null) {
//                    binding!!.list.layoutManager = LinearLayoutManager(this@MainActivity)
//                    for (i in 0 until response.body()!!.drinks!!.size) {
//                        list.add(response.body()!!.drinks!![i].strDrink!!)
//                        image.add(response.body()!!.drinks!![i].strDrinkThumb!!)
//                    }
//                    adapter = CocktailAdapter(this@MainActivity, list, image,this@MainActivity)
//                    binding!!.list.adapter = adapter
//                    saveData(list, image)
//                } else {
//                    Toast.makeText(this@MainActivity, "Что-то произошло", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<CocktailModel?>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Нет ответа", Toast.LENGTH_SHORT).show()
//            }
//        })
    }

    override fun onItemClick(drink: String) {
        cocktail = drink
        startActivity(Intent(this, MainActivity2::class.java))
    }

    override fun onStop() {
        super.onStop()
        if (disposable != null) {
            disposable!!.dispose()
        }
    }

    companion object {
        @JvmField
        var cocktail: String? = null
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
//            val list = dao.selectAll()
//            withContext(Dispatchers.Main) {
//                Toast.makeText(applicationContext, list.toString(), Toast.LENGTH_SHORT).show()
//            }
//        }