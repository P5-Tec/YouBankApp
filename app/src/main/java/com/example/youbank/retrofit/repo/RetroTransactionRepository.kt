package com.example.youbank.retrofit.repo

import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.TransactionService

class RetroTransactionRepository {
    var client: TransactionService = ApiService.buildService(TransactionService::class.java)

    suspend fun getTransactions() = client.listTransactions()
}