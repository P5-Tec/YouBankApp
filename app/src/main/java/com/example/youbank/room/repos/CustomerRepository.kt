package com.example.youbank.room.repos

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.youbank.models.Customer
import com.example.youbank.models.CustomerWithAccounts
import com.example.youbank.room.daos.CustomerDao
import kotlinx.coroutines.flow.Flow

class CustomerRepository(private val customerDao: CustomerDao) {

    val readCustomer: LiveData<Customer> = customerDao.getCustomer()

    fun insertCustomer(c: Customer) {
        customerDao.insertCustomer(c)
    }

    suspend fun updateCustomer(c: Customer) {
        customerDao.updateCustomer(c)
    }

    suspend fun deleteCustomer(c: Customer) {
        customerDao.deleteCustomer(c)
    }

    @WorkerThread
    fun getAllInfo(id: Int): Flow<List<CustomerWithAccounts>> {
        return customerDao.getCustomerWithAccountsID(id)
    }
}