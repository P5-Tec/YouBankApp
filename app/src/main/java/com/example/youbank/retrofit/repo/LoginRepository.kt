package com.example.youbank.retrofit.repo

import com.example.youbank.models.Customer
import com.example.youbank.retrofit.LoginService
import com.example.youbank.retrofit.client.RetrofitClient
import retrofit2.Response

class LoginRepository {

    var client: LoginService = RetrofitClient.retrofit.create(LoginService::class.java)

    suspend fun login(c: Customer): Response<Customer>{
        return client.login(c)
    }
}