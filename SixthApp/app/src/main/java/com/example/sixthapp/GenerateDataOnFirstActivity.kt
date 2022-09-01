package com.example.sixthapp

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sixthapp.databinding.ActivityMainBinding
import com.example.sixthapp.db.CocktailDao
import com.example.sixthapp.db.CocktailModel
import com.example.sixthapp.network.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import com.example.sixthapp.MainActivity.Companion.adapter
import com.example.sixthapp.MainActivity.Companion.disposable

class GenerateDataOnFirstActivity {

    var listCocktail: ArrayList<CocktailModel.Drinks> = ArrayList()

    fun getApi(
        dao: CocktailDao,
        binding: ActivityMainBinding,
        applicationContext: Context,
        listener: OnItemClickListener
    ) {
        disposable = ApiService
            .getDataCocktail()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                for (i in it.drinks!!) {
                    listCocktail.add(i)
                    dao.insert(CocktailModel.Drinks(i.idDrink,i.strDrink,i.strDrinkThumb))
                }
                setAdapter(binding,applicationContext,listener,listCocktail)
            }) {
                Toast.makeText(applicationContext, "Ошибка Api-запроса", Toast.LENGTH_SHORT).show()
            }
//                                  ~~~~~~~~~~~~~~~~~RetroFit Call~~~~~~~~~~~~~~~~~~
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

    fun setAdapter(
        binding: ActivityMainBinding,
        applicationContext: Context,
        listener: OnItemClickListener,
        listCocktail: List<CocktailModel.Drinks>
    ) {
        binding.list.layoutManager = LinearLayoutManager(applicationContext)
        adapter = CocktailAdapter(applicationContext, listCocktail, listener)
        binding.list.adapter = adapter
    }
}