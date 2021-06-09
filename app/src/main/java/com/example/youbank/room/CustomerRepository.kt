package com.example.youbank.room

import androidx.lifecycle.LiveData

class CustomerRepository(private val customerDao: CustomerDao) {

    val readCustomer: LiveData<RoomCustomer> = customerDao.getCustomer()

    fun addCustomer(c: RoomCustomer) {
        customerDao.addCustomer(c)
    }

}