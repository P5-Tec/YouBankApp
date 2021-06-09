package com.example.youbank.retrofit.repo

import android.util.Log
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.LoginService
import com.example.youbank.retrofit.client.RetrofitClient
import retrofit2.Call

class LoginRepo() {
    var client: LoginService = RetrofitClient.retrofit.create(LoginService::class.java)
    suspend fun getLogin(co: Customer) = client.getLogin(co)
}