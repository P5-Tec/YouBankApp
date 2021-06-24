package com.example.youbank.room.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Account
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.repos.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AccountViewModel(application: Application): AndroidViewModel(application) {

    val readAccounts: LiveData<Account>
    private val repository: AccountRepository

    init {
        val accountDao = CustomerDatabase.getDatabase(application, viewModelScope).accountDao()
        repository = AccountRepository(accountDao)
        readAccounts = repository.readAccounts
    }

    fun addAccount(a: Account) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAccounts(a)
        }
    }
}