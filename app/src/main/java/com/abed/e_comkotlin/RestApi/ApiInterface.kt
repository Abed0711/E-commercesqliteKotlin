package com.abed.e_comkotlin.RestApi

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*


interface APIService {
    @Multipart
    @POST("register")
    suspend fun registerUser(@PartMap map: HashMap<String?, RequestBody?>): Response<ResponseBody>

    @Multipart
    @POST("login")
    suspend fun loginUser(@PartMap map: HashMap<String?, RequestBody?>): Response<ResponseBody>

}
