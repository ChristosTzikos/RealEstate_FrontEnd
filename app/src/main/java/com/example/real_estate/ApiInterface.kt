package com.example.real_estate

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("Search/")
    fun getData(
        @Query("Region") Region: String,
        @Query("Sale") Sale: String,
        @Query("minPrice") minPrice: Int,
        @Query("maxPrice") maxPrice: Int,
        @Query("sqMin") minArea: Int,
        @Query("sqMax") maxArea: Int

    )
            : Call<Test>

}
