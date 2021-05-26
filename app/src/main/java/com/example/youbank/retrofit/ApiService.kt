package com.example.youbank.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val URL = "http://api.saxproduction.dk/api/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> buildService(serviceType: Class<T>?): T {
        return retrofit.create(serviceType)
    }
}