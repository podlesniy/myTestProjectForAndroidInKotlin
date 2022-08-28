package com.example.sixthapp.network

import com.example.sixthapp.db.CocktailModel
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

object ApiService {

    private const val API = "https://www.thecocktaildb.com/api/json/v1/1/"
    private var cocktailApi: CocktailApi? = null

    interface CocktailApi {
        @GET("filter.php")
        fun getCocktail(
            @Query("a") type: String?
        ): Call<CocktailModel>

        @GET("lookup.php")
        fun getCocktailInfo (
            @Query("i") id: Long?
        ): Call<CocktailModel>
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API)
            .client(client)
            .build()
        cocktailApi = retrofit.create(CocktailApi::class.java)
    }

    fun getDataCocktail(): Call<CocktailModel> {
        return cocktailApi!!.getCocktail("Alcoholic")
    }

    fun getInfoCocktail(id: Long?): Call<CocktailModel> {
        return cocktailApi!!.getCocktailInfo(id)
    }
}