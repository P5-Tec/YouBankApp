package com.example.youbank.retrofit

import com.example.youbank.models.Customer
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerService {

    @GET("Customers")
    suspend fun listCustomers(): List<Customer>

    @GET("Customers/{id}")
    suspend fun getCustomerById(@Path("id") id: Int): Customer

    @POST("Customers")
    fun addNewCustomer(@Body c: Customer): Call<Void>

    @PUT("Customers/{id}")
    fun updateCustomer(@Body c: Customer, @Path("id") id: Int): Call<Void>

    @DELETE("Customers/{id}")
    fun deleteCustomer(@Path("id") id: Int): Call<Void>

}