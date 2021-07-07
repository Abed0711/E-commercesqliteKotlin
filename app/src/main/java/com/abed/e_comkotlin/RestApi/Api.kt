package com.abed.e_comkotlin.RestApi

import com.abed.e_comkotlin.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {
    @FormUrlEncoded
    @POST("sandbox.emdexapi.com/api/v1/register")
    fun Registration(
        @Field("email") email : String,
        @Field("password") password : String,
        @Field("c_password") c_password : String,
        @Field("name") name : String
        ):Call<DefaultResponse>

}