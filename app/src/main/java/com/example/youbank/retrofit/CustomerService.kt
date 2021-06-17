package com.example.youbank.retrofit

import com.example.youbank.models.Customer
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CustomerService {

    @GET("Customers")
    fun getAllCustomers(): Call<List<Customer>>

    @GET("Customers/{id}")
    suspend fun getCustomerById(@Path("id") id: Int): Customer

    @POST("Customers")
    fun addNewCustomer(@Body c: Customer): Call<Void>

    @PUT("Customers/{id}")
    fun updateCustomer(@Body c: Customer, @Path("id") id: Int): Call<Void>

    @DELETE("Customers/{id}")
    fun deleteCustomer(@Path("id") id: Int): Call<Void>

}