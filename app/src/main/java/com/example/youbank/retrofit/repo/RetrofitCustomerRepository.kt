package com.example.youbank.retrofit.repo

import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService

class RetrofitCustomerRepository {

    var client: CustomerService = ApiService.buildService(CustomerService::class.java)

    //var client2: CustomerService = ApiService.buildService(CustomerService::class.java)
    //var client3: CustomerService = RetrofitClient.retrofit.create(CustomerService::class.java)

    suspend fun getCustomerById(cId: Int) = client.getCustomerById(cId)

    //suspend fun getCustomerById2(id: Int): Customer {
    //    return client3.getCustomerById(id)
    //}
}