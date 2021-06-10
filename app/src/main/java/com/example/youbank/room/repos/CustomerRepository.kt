package com.example.youbank.room.repos

import androidx.lifecycle.LiveData
import com.example.youbank.room.daos.CustomerDao
import com.example.youbank.room.models.RoomCustomer

class CustomerRepository(private val customerDao: CustomerDao) {

    val readCustomer: LiveData<RoomCustomer> = customerDao.getCustomer()

    fun addCustomer(c: RoomCustomer) {
        customerDao.addCustomer(c)
    }

}