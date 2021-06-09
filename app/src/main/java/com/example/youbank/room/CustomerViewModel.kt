package com.example.youbank.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: Customer
    private val repository: CustomerRepository

    init {
        val customerDao = CustomerDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(customerDao)
        readAllData = repository.readAllData

    }

    fun addCustomer(c: Customer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(c)
        }
    }
}