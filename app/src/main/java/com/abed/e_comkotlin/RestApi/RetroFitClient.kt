package com.abed.e_comkotlin.RestApi

import android.util.Base64
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetroFitClient {
    //private val AUTH = "Basic " + Base64.encodeToString("".toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL =
        "https://www.postman.com/collections/287eb3252ef66aac342a?fbclid=IwAR1yTKnyso_pOm9R6Dhk3hpMD7b2bemvgd_nBWUUq7xtIkDyWmzrBLuoEwU/"


    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor {chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                //.addHeader("Authorization", "")
                .method(original.method, original.body)
            val request = requestBuilder.build()
            chain.proceed(request)
        }.build()


    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        retrofit.create(Api::class.java)
    }
}