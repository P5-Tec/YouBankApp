package com.example.youbank.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youbank.models.Customer

class SharedViewModel: ViewModel() {

    var c = MutableLiveData<Customer>()

    fun setCustomer(cus: Customer) {
        c.value = cus
    }

    fun getCustomer(): MutableLiveData<Customer> {
        return c
    }



}