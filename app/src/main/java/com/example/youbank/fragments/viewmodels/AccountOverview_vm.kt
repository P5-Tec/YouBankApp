package com.example.youbank.fragments.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AccountOverview_vm : ViewModel() {

    private var accBalance =  MutableLiveData<Int>()
    private var accNumber = MutableLiveData<String>()

    init {
        accNumber.value = "7540 2645 2637 2374"
        Log.i("Account Viewmodel", "Account Viewmodel Created")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("Account Viewmodel", "Account Viewmodel Destroyed")
    }

    fun getAccountBalance(): MutableLiveData<Int> {
        setAccountBalance()
        return accBalance
    }

    fun setAccountBalance() {
        if (accBalance.value == null){
            accBalance.value = 100
        }else{accBalance.value = accBalance.value?.plus(131)}
    }

    fun getAccountNumber(): LiveData<String> {
        return accNumber
    }
}