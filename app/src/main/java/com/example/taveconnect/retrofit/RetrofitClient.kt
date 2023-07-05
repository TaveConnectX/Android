package com.example.taveconnect.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitClient {
    private const val BASE_URL = "https://cconnect.backlogs.dev"

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    var gson = GsonBuilder().setLenient().create()

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(okHttpClient)
        .build()





    fun getInstance() : Retrofit {
        return client
    }





    /*
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }



    val gameService: GameService by lazy {
        retrofit.create(GameService::class.java)
    }



    private var instance: Retrofit? = null
    private var gson = GsonBuilder().setLenient().create()

    fun getInstance(): Retrofit {
        if (instance == null) {
            instance = Retrofit.Builder()
                .baseUrl("http://cconnect.backlogs.dev/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }
        return instance!!
    } */
}