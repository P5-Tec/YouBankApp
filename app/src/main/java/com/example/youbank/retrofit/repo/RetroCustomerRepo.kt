package com.example.youbank.retrofit.repo

import com.example.youbank.retrofit.AccountService
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import kotlinx.coroutines.flow.Flow

class RetroCustomerRepo {
    var client: CustomerService = ApiService.buildService(CustomerService::class.java)

    //suspend fun getCustomers() = client.listCustomers()
    suspend fun getCustomerById(cId: Int) = client.getCustomerById(cId)
}