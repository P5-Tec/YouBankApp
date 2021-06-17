package com.example.youbank.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youbank.models.Customer
import org.mindrot.jbcrypt.BCrypt

class SharedViewModel: ViewModel() {

    var c = MutableLiveData<Customer>()
    var password = String()

    fun setCustomer(cus: Customer) {
        c.value = cus
    }

    fun getCustomer(): MutableLiveData<Customer> {
        return c
    }

    fun hashPassword(passwordHash: String) {
        password = BCrypt.hashpw(passwordHash, BCrypt.gensalt(7))
    }

    fun setPassword(){
        c.value?.password = password
    }

    fun getPasswordHash(): String {
        return password
    }
}