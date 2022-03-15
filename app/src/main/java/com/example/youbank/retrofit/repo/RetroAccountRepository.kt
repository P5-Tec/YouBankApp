package com.example.youbank.retrofit.repo

import com.example.youbank.retrofit.AccountService
import com.example.youbank.retrofit.ApiService

class RetroAccountRepository {
    var service: AccountService = ApiService.buildService(AccountService::class.java)

    suspend fun getAccounts() = service.listAccounts()
    suspend fun getAccountById(aId: Int) = service.getAccountById(aId)
}