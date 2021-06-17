package com.example.youbank.room.repos

import androidx.lifecycle.LiveData
import com.example.youbank.models.Customer
import com.example.youbank.room.daos.CustomerDao

class CustomerRepository(private val customerDao: CustomerDao) {

    val readCustomer: LiveData<Customer> = customerDao.getCustomer()

    fun insertCustomer(c: Customer) {
        customerDao.insertCustomer(c)
    }

    suspend fun updateCustomer(c: Customer) {
        customerDao.updateCustomer(c)
    }
}