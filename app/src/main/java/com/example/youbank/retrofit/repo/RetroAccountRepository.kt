package com.example.youbank.retrofit.repo

import com.example.youbank.retrofit.AccountService
import com.example.youbank.retrofit.ApiService

class RetroAccountRepository {
    var client: AccountService = ApiService.buildService(AccountService::class.java)

    suspend fun getAccounts() = client.listAccounts()
    suspend fun getAccountById(aId: Int) = client.getAccountById(aId)
}