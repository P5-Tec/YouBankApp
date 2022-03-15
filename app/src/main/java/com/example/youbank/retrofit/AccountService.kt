package com.example.youbank.retrofit

import com.example.youbank.models.Account
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountService {
    @GET("accounts")
    suspend fun listAccounts(): List<Account>

    @GET("accounts/{id}")
    suspend fun getAccountById(@Path("id") id: Int): Account
}