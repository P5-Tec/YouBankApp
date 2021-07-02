package com.example.youbank.retrofit

import com.example.youbank.models.Customer
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("Login")
    suspend fun getLogin(@Body c: Customer): Customer
}