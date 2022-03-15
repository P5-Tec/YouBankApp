package com.example.youbank.retrofit

import com.example.youbank.models.Transaction
import retrofit2.http.GET
import retrofit2.http.Path

interface TransactionService {
    @GET("transactions")
    suspend fun listTransactions(): List<Transaction>

    @GET("transactions/{id}")
    suspend fun getTransactionById(@Path("id") id: Int): Transaction
}