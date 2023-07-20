package com.example.myapplication.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private lateinit var retrofit: Retrofit
    private const val BASE_URL = "https://my-json-server.typicode.com/"
    fun apiClient(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okhttpClient: OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
            .build()
        val gson: Gson = GsonBuilder().setLenient().create()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(
                RxJava3CallAdapterFactory.create()
            )
            .client(okhttpClient)
            .build()
        return retrofit
    }
}