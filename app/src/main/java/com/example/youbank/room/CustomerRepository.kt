package com.example.youbank.room

class CustomerRepository(private val customerDao: CustomerDao) {

    val readAllData: Customer = customerDao.getCustomer()

    fun addCustomer(c: Customer) {
        customerDao.addCustomer(c)
    }

}