package com.example.youbank.room.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.repos.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application): AndroidViewModel(application) {

    val readCustomer: LiveData<Customer>
    private val customerRepo: CustomerRepository

    var isSettingsValid: Boolean = false

    init {
        val customerDao = CustomerDatabase.getDatabase(application, viewModelScope).customerDao()
        customerRepo = CustomerRepository(customerDao)

        readCustomer = customerRepo.readCustomer
    }

    fun setIsSettingsValid(b: Boolean) {
        isSettingsValid = b
    }

    fun getIsSettingsValid(): Boolean {
        return isSettingsValid
    }

    fun updateCustomerInRoom(c: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepo.updateCustomer(c)
        }
    }

    fun insertCustomerToRoomDB(c: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepo.insertCustomer(c)
        }
    }

    fun addCustomerToRoomDB(id: Int) {
        val service: CustomerService = ApiService.buildService(CustomerService::class.java)

        viewModelScope.launch(Dispatchers.IO) {
            val req = service.getCustomerById(id)
            customerRepo.insertCustomer(req)
        }
    }

    fun deleteCustomer(c: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            customerRepo.deleteCustomer(c)
        }
    }

}