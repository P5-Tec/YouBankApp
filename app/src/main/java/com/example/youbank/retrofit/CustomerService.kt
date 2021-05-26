package com.example.youbank.retrofit

import com.example.youbank.models.Customer
import retrofit2.Call
import retrofit2.http.*

interface CustomerService {
    @GET("Customers")
    fun getAllCustomers(): Call<List<Customer>>

    @GET("Customers/{id}")
    fun getCustomerById(@Path("id") id: Int): Call<Customer>

    @POST("Customers")
    fun addNewCustomer(@Body c: Customer): Call<Void>

    @PUT("Customers/{id}")
    fun updateCustomer(@Body c: Customer, @Path("id") id: Int): Call<Void>

    @DELETE("Customers/{id}")
    fun deleteCustomer(@Path("id") id: Int): Call<Void>
}