package com.example.youbank.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application): AndroidViewModel(application) {

    val readCustomer: LiveData<RoomCustomer>
    private val repository: CustomerRepository

    init {
        val customerDao = CustomerDatabase.getDatabase(application).customerDao()
        repository = CustomerRepository(customerDao)
        readCustomer = repository.readCustomer

    }

    fun addCustomer(c: RoomCustomer) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(c)
        }
    }
}