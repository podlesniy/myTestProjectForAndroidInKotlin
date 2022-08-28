package com.example.fifthonkotlin

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object ApiService {

    private const val API = "https://cat-fact.herokuapp.com/facts/"
    private var catsApi: CatsApi? = null

//    fun getData(): Call<List<FactModel>> {
//        return catsApi!!.getFacts()
//    }

    fun getDataByType(type: String): Call<List<FactModel>> {
        return catsApi!!.getFactsByType(type, 100)
    }

    interface CatsApi {
//        @GET("random?animal_type=cat&amount=100")
//        fun getFacts(): Call<List<FactModel>>

        @GET("random")
        fun getFactsByType(@Query("animal_type") type: String, @Query("amount") amount: Int): Call<List<FactModel>>

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
        catsApi = retrofit.create<CatsApi>(CatsApi::class.java)
    }
}