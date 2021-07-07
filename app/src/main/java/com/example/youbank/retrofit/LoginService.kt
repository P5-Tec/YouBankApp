package com.example.youbank.retrofit

import com.example.youbank.models.Customer
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("Login")
    suspend fun login(@Body c: Customer): Response<Customer>
}