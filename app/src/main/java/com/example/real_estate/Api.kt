package com.example.real_estate

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface Api {

    @FormUrlEncoded
    @POST ("/User/Signup")
    fun createUser(
        @Field("email") email: String,
        @Field("password") password:  String
    ): Call<User>

    @FormUrlEncoded
    @POST ("/User/Login")
    fun loginUser(
        @Field("email") email: String,
        @Field("password") password:  String
    ): Call<UserLogin>



}