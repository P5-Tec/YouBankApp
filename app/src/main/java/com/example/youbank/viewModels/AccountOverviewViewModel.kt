package com.example.youbank.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.youbank.models.Account

class AccountOverviewViewModel: ViewModel() {

    private var accBalance = MutableLiveData<Int>()
    private var accNumber = MutableLiveData<String>()
    private val ac = Account()

    init {
        setAccountNumber()
        //Log.i("test", ac.accountNumber.toString())
        //accNumber.value = "7540 2645 2637 2374"
        //Log.i("Account Viewmodel", "Account Viewmodel Created")
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
        if (accBalance.value == null) {
            accBalance.value = 100
        }
        else {
            accBalance.value = accBalance.value?.plus(131)
        }
    }

    fun getAccountNumber(): MutableLiveData<String> {
        setAccountNumber()
        return accNumber
    }

    fun setAccountNumber() {
        if (ac.accountNumber == null) {
            accNumber.value = "0000 0000 0000 0000"
        }
        else {
            accNumber.value = ac.accountNumber.toString()
        }
    }
}