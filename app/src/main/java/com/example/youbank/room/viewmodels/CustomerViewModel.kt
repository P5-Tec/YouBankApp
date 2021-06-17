package com.example.youbank.room.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Account
import com.example.youbank.models.Card
import com.example.youbank.models.Customer
import com.example.youbank.retrofit.ApiService
import com.example.youbank.retrofit.CustomerService
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.repos.AccountRepository
import com.example.youbank.room.repos.CardRepository
import com.example.youbank.room.repos.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel(application: Application): AndroidViewModel(application) {

    val readCustomer: LiveData<Customer>
    val readAccount: LiveData<Account>
    val readCard: LiveData<List<Card>>

    private val customerRepo: CustomerRepository
    private val accountRepo: AccountRepository
    private val cardRepo: CardRepository

    var isSettingsValid: Boolean = false

    //private var cus: Customer = Customer()
    //var a = listOf<Account>()
    //var cards = listOf<Card>()

    init {

        val customerDao = CustomerDatabase.getDatabase(application).customerDao()
        customerRepo = CustomerRepository(customerDao)
        readCustomer = customerRepo.readCustomer

        val accountDao = CustomerDatabase.getDatabase(application).accountDao()
        accountRepo = AccountRepository(accountDao)
        readAccount = accountRepo.readAccounts

        val cardDao = CustomerDatabase.getDatabase(application).cardDao()
        cardRepo = CardRepository(cardDao)
        readCard = cardRepo.readCards
    }

    fun setIsSettingsValid(b: Boolean){
        isSettingsValid = b
    }

    fun getIsSettingsValid(): Boolean {
        return isSettingsValid
    }

    fun updateCustomerInRoom(c: Customer) {
        viewModelScope.launch(Dispatchers.IO) {

            Log.d("cus viewmodel", c.address)
            customerRepo.updateCustomer(c)
        }
    }

    fun addCustomerToRoomDB(id: Int) {
        val service: CustomerService = ApiService.buildService(CustomerService::class.java)

        viewModelScope.launch(Dispatchers.IO) {
            val req = service.getCustomerById(id)
            customerRepo.insertCustomer(req)
        }
    }


}