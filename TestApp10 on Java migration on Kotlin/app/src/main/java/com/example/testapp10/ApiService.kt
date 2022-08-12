package com.example.testapp10

import com.example.testapp10.ApiService.PrivateApi
import com.example.testapp10.RequestModel
import com.example.testapp10.ApiService
import retrofit2.http.GET
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query

object ApiService {
    private const val API = "https://api.privatbank.ua/p24api/"
    private var privateApi: PrivateApi? = null
    fun getData(date: String?): Call<RequestModel> {
        return privateApi!!.getWeatherData("json", date)
    }

    interface PrivateApi {
        @GET("exchange_rates")
        fun getWeatherData(
            @Query("json") json: String?,
            @Query("date") date: String?
        ): Call<RequestModel>
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
        privateApi = retrofit.create<PrivateApi>(PrivateApi::class.java)
    }
}