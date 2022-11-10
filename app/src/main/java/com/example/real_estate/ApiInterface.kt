package com.example.real_estate

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiInterface {

    @GET("Products?page=1")
    fun getData()
            : Call<Test>

}
