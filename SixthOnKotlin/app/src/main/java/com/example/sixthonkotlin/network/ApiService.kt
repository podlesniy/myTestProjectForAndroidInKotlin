package com.example.sixthonkotlin.network

import com.example.sixthonkotlin.db.CocktailModel
import com.example.sixthonkotlin.db.CocktailModelInfo
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Query
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

object ApiService {

    private const val API = "https://www.thecocktaildb.com/api/json/v1/1/"
    private var cocktailApi: CocktailApi? = null

    interface CocktailApi {
        @GET("filter.php")
        fun getCocktail(
            @Query("a") type: String?
        ): Observable<CocktailModel>

        @GET("lookup.php")
        fun getCocktailInfo (
            @Query("i") id: String?
        ): Observable<CocktailModelInfo>
    }

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(API)
            .client(client)
            .build()
        cocktailApi = retrofit.create(CocktailApi::class.java)
    }

    fun getDataCocktail(): Observable<CocktailModel> {
        return cocktailApi!!.getCocktail("Alcoholic")
    }

    fun getInfoCocktail(id: String?): Observable<CocktailModelInfo> {
        return cocktailApi!!.getCocktailInfo(id)
    }
}