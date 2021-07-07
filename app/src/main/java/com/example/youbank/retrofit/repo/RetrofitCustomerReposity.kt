package com.example.youbank.retrofit.repo

import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService

class RetroCustomerRepo {
    var client: CustomerService = ApiService.buildService(CustomerService::class.java)

    suspend fun getCustomerById(cId: Int) = client.getCustomerById(cId)
}