package com.example.youbank.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.youbank.models.Account
import com.example.youbank.room.CustomerDatabase
import com.example.youbank.room.repos.AccountRepository

class AccountOverviewViewModel(application: Application): AndroidViewModel(application) {

    val readAccount: LiveData<Account>
    private val accountRepo: AccountRepository

    init {
        val accountDao = CustomerDatabase.getDatabase(application, viewModelScope).accountDao()
        accountRepo = AccountRepository(accountDao)

        readAccount = accountRepo.readAccounts
    }

}