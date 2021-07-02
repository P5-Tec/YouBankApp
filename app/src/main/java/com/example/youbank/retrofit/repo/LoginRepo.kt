package com.example.youbank.retrofit.repo

import com.example.youbank.models.Customer
import com.example.youbank.retrofit.LoginService
import com.example.youbank.retrofit.client.RetrofitClient

class LoginRepo {

    var client: LoginService = RetrofitClient.retrofit.create(LoginService::class.java)

    suspend fun getLogin(co: Customer) = client.getLogin(co)
}